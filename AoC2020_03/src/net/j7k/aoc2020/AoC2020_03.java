package net.j7k.aoc2020;

import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import java.util.stream.Collectors;

public class AoC2020_03 {

	static boolean[][] loadMap(List<String> lines)
	{
		boolean[][] result = new boolean[lines.size()][];
		
		int y=0;
		for (String line : lines)
		{
			int x=0;
			result[y] = new boolean[line.length()];
			for (char c : line.toCharArray())
			{
				result[y][x++] = (c == '#');
			}
			++y;
		}	
		return result;
	}
		
	
	static int treeInPath(boolean[][] map, int xStep, int yStep)
	{
		int result = 0;
		int x = 0;
		int y = 0;
		
		while (y < map.length)
		{
			result = (map[y][x]) ? ++result : result;
			x = (x + xStep) % map[y].length;
			y += yStep;
		}
		return result;
	}
	

	public static void main(String[] args) {
		try
		{
			Path datafile= Path.of(args[0]);
			List<String> data = Files.lines(datafile).collect(Collectors.toList());
			boolean[][] map = loadMap(data);
			
			System.out.println("Trees in path: " + treeInPath(map, 3, 1));
			
			long stack = treeInPath(map, 1, 1) 
					* treeInPath(map, 3, 1) 
					* treeInPath(map, 5, 1) 
					* treeInPath(map, 7, 1);	
			BigInteger partTwoValue = BigInteger.valueOf(stack).multiply(BigInteger.valueOf(treeInPath(map, 1, 2)));
			System.out.println("Trees in 5 paths: " + partTwoValue);
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
