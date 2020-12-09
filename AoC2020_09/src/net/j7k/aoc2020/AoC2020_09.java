package net.j7k.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class AoC2020_09 {

	
	public static void main(String[] args) {
		try
		{
			Path datafile= Path.of(args[0]);
			List<Long> code = Files.lines(datafile)
					.map(Long::parseLong)
					.collect(Collectors.toList());
			
			EncryptedData cypher = new EncryptedData(code);
			
			System.out.println((cypher.isValid()) ? "Valid" : "First Invalid: " + cypher.firstInvalid);
			List<Long> continuousSet = cypher.findContiguousSet(cypher.firstInvalid);
			System.out.println(continuousSet.stream().min(Long::compare).get() 
					+ continuousSet.stream().max(Long::compare).get());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
