package net.j7k.aoc2020;

import java.awt.Point;
import java.util.List;
import java.util.OptionalInt;

public class WaitingRoom2 extends WaitingRoom 
{
	public WaitingRoom2(List<String> rows)
	{
		super(rows);
	}


	@Override
	protected OptionalInt neighbor(int col, int row, Point direction)
	{
		int nextCol = col + direction.x;
		int nextRow = row + direction.y;
		OptionalInt result = OptionalInt.empty();
		
		boolean found = false;
		
		while( !found && idxBoundingBox().contains(nextCol, nextRow) )
		{
			if (floorPlan[nextRow][nextCol] != FLOOR)
			{
				result = OptionalInt.of(floorPlan[nextRow][nextCol]);
				found = true;
			}
			nextCol += direction.x;
			nextRow += direction.y;
		}	
		return result;
	}
	
	@Override
	protected boolean canFlipOccupied(int col, int row)
	{
		long occupiedNeighborsCount = neighbors(col, row).stream()
				.filter(neighbor -> neighbor == USED_SEAT)
				.count();
		return occupiedNeighborsCount >= 5;
	}
	
}
