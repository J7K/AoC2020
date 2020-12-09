package net.j7k.aoc2020;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class EncryptedData {

	public static final int PREAMBLE_SIZE = 25;
	
	private boolean valid;
	private List<Long> code;
	public Long firstInvalid;
	
	public boolean isValid()
	{
		return valid;
	}
		
	static List<Long> decodePreamble(List<Long> tape)
	{	
		return tape.stream().limit(PREAMBLE_SIZE)
				.flatMap(x -> tape.stream().limit(PREAMBLE_SIZE).skip(1).map(y -> x + y))
				.collect(Collectors.toList());
	}
	
	boolean validate()
	{
		valid = true;
		List<Long> tape = new LinkedList<Long>(code);
		
		while ( (tape.size() > PREAMBLE_SIZE) && valid)
		{
			List<Long> allValid = decodePreamble(tape);
			valid = allValid.contains(tape.get(PREAMBLE_SIZE));
			if(!valid)
				firstInvalid = tape.get(PREAMBLE_SIZE);			
			tape.remove(0);
		}
		
		return valid;
	}
	
	List<Long> 	findContiguousSet(Long value)
	{
		List<Long> tape = new LinkedList<Long>(code);
		while(tape.remove(value));
		boolean found=false;
		List<Long> set = new LinkedList<Long>();
		while(!tape.isEmpty() && !found)
		{
			long acc = 0;
			set.clear();
			for (int i=0; (i < tape.size()) && acc < value; ++i)
			{
				Long current = tape.get(i);
				acc += current;
				set.add(current);
				found = (acc == value);
			}
			tape.remove(0);
		}
		return set;
	}
	
	EncryptedData(List<Long> code)
	{
		assert(code.size() > PREAMBLE_SIZE);
		this.code = new ArrayList<>(code);
		validate();
	}
}
