package fr.j7k.aoc2020;

public class Instruction {
	
	static final String NOP = "nop";
	static final String ACC = "acc";
	static final String JMP = "jmp";
	
	public String opcode;
	public int operand;
	
	public Instruction(String input)
	{
		opcode = input.substring(0,3);
		operand = Integer.parseInt(input.substring(4));	
	}

}
