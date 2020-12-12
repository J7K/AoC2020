package net.j7k.aoc2020;

public class Order
{
	public static final char NORTH = 'N';
	public static final char EAST = 'E';
	public static final char SOUTH = 'S';
	public static final char WEST = 'W';
	public static final char LEFT = 'L';
	public static final char RIGHT = 'R';
	public static final char FORWARD = 'F';
	
	
	public char inst;
	public int operand;
	
	public Order(String input)
	{
		inst = input.charAt(0);
		operand = Integer.parseInt(input.substring(1));
	}
	
	public Order(char inst, int operand)
	{
		this.inst = inst;
		this.operand = operand;
	}
}