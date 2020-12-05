package net.j7k.aoc2020;

public class BoardingPass {
	
	int row;
	int seat;
	int id;
	
	public int getID()
	{
		return id;
	}
	
	static public int computeSeatID(int row, int seat)
	{
		return row * 8 + seat;
	}
	
	private int decodeRow(String input)
	{	
		String binaryString = input.codePoints()
				.map(c -> (c == 'B') ? '1' : '0')
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		
		return Integer.parseInt(binaryString, 2);
	}
	
	private int decodeCol(String input)
	{
		String binaryString = input.codePoints()
				.map(c -> (c == 'R') ? '1' : '0')
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		
		return Integer.parseInt(binaryString, 2);
	}
	
	public BoardingPass(String input)
	{	
		if (! input.matches("(F|B){7}(L|R){3}") )
			throw new IllegalArgumentException(input);

		row = decodeRow(input.substring(0, 7));
		seat = decodeCol(input.substring(7,10));
		id = computeSeatID(row, seat);
	}
}
