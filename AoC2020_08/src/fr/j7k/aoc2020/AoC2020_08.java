package fr.j7k.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AoC2020_08 {
	
	static List<Instruction> instructions;
	static Set<Integer> decodedInstrIndexes;
	
	static int instPtr;
	static int acc;
	static boolean repeat;
	static boolean exit;
	
	static void init(List<Instruction> insts)
	{
		instructions = insts;
		decodedInstrIndexes = new HashSet<>();
		instPtr=0;
		acc=0;
		repeat = false;
		exit = false;
	}

	static void run()
	{
		while(!repeat && !exit)
			execute();
	}
	
	static void execute()
	{
		if (instPtr == instructions.size())
		{
			exit = true;
			return;
		}
		if (instPtr > instructions.size())
		{
			throw new RuntimeException("SegFault");
		}
		
		repeat = decodedInstrIndexes.contains(instPtr);
		decodedInstrIndexes.add(instPtr);
		
		Instruction inst = instructions.get(instPtr);		
		if (inst.opcode.equals(Instruction.JMP))
			instPtr += inst.operand;
		else if (inst.opcode.equals(Instruction.ACC))
		{
			acc += inst.operand;
			++instPtr;
		}
		else if (inst.opcode.equals(Instruction.NOP))
		{
			++instPtr;
		}
	}
	

	public static boolean swapNopsAndJmps(List<Instruction> code)
	{
		boolean fixed = false;
		
		for (int i=0; (i < code.size()) && !fixed; ++i)
		{
			if (code.get(i).opcode.equals(Instruction.ACC))
				continue;

			if (code.get(i).opcode.equals(Instruction.JMP))			
			{
				code.get(i).opcode = Instruction.NOP;
				init(code);
				run();
				code.get(i).opcode = Instruction.JMP;
				fixed = exit;
			}
			if (code.get(i).opcode.equals(Instruction.NOP))			
			{
				code.get(i).opcode = Instruction.JMP;
				init(code);
				run();
				code.get(i).opcode = Instruction.NOP;
				fixed = exit;
			}
		}
		
		return fixed;
	}
	
	
	public static void main(String[] args) {
		try
		{
			Path datafile= Path.of(args[0]);
			List<Instruction> code = Files.lines(datafile).map(Instruction::new).collect(Collectors.toList());
			
			init(code);
						
			int cache=0;
			while(!repeat && !exit)
			{
				cache = acc;
				execute();
			}
			System.out.println("Acc: " + cache);
			if( swapNopsAndJmps(code))
				System.out.println("Acc: " + acc);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
