package net.j7k.aoc2020;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class WaitingRoom {

	public static final int FREE_SEAT = 'L';
	public static final int USED_SEAT = '#';
	public static final int FLOOR = '.';	
	
	public static final ArrayList<Point> NEIGHBOR_DIRECTIONS = new ArrayList<>(
			Arrays.asList(
					new Point(1,0),
					new Point(1,1),
					new Point(0,1),
					new Point(-1,1),
					new Point(-1,0),
					new Point(-1,-1),
					new Point(0,-1),
					new Point(1,-1)
					)
			);

	protected int[][] floorPlan;

	protected Rectangle idxBoundingBox()
	{
		return new Rectangle(0, 0, floorPlan[0].length, floorPlan.length);
	}

	protected OptionalInt neighbor(int col, int row, Point direction)
	{
		int nextCol = col + direction.x;
		int nextRow = row + direction.y;
		
		OptionalInt result = OptionalInt.empty();		
		if (idxBoundingBox().contains(nextCol, nextRow))
				result = OptionalInt.of(floorPlan[nextRow][nextCol]);

		return result;
	}
	
	protected List<Integer> neighbors(int col, int row)
	{
		return NEIGHBOR_DIRECTIONS.stream()
				.map(direction -> neighbor(col, row, direction))
				.filter(OptionalInt::isPresent)
				.mapToInt(OptionalInt::getAsInt)
				.boxed()
				.collect(Collectors.toList());
	}	

	protected boolean canFlipOccupied(int col, int row)
	{
		long occupiedNeighborsCount = neighbors(col, row).stream()
				.filter(neighbor -> neighbor == USED_SEAT)
				.count();
		return occupiedNeighborsCount >= 4;
	}

	protected boolean canFlipFree(int col, int row)
	{
		List<Integer> neighborsList = neighbors(col, row);
		return neighborsList.size() == neighborsList.stream()
				.filter(neighbor -> neighbor != USED_SEAT)
				.count();	
	}

	public WaitingRoom(List<String> rows)
	{
		floorPlan = rows.stream()
				.map(line -> line.chars().map(x->x).toArray())
				.toArray(int[][]::new);
	}
	
	public long countOccupied()
	{
		return Arrays.stream(floorPlan)
				.mapToLong(row -> Arrays.stream(row)
						.filter(seat -> seat == USED_SEAT)
						.count())
				.sum();
	}
	
	public boolean seatingRound()
	{
		int[][] newPlan = Arrays.stream(floorPlan).map(int[]::clone).toArray(int[][]::new);
		
		boolean modified = false;
		for (int row=0; row < floorPlan.length; ++row)
		{
			for (int col=0; col < floorPlan[row].length; ++col)
			{
				if ( (floorPlan[row][col] == USED_SEAT) && canFlipOccupied(col, row) )
				{
					newPlan[row][col] = FREE_SEAT;
					modified = true;
				}
				if ( (floorPlan[row][col] == FREE_SEAT) && canFlipFree(col, row) )
				{
					newPlan[row][col] = USED_SEAT;
					modified = true;
				}
			}
		}
		
		floorPlan = newPlan;
		return modified;
	}	
}
