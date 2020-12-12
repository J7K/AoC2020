package net.j7k.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class AoC2020_11 {
	
	public static void main(String[] args) {
		try
		{			
			WaitingRoom wr = new WaitingRoom(Files.lines(Path.of(args[0])).collect(Collectors.toList()));
			while(wr.seatingRound());
			System.out.println("Ans1: " + wr.countOccupied());			
			
			WaitingRoom2 wr2 = new WaitingRoom2(Files.lines(Path.of(args[0])).collect(Collectors.toList()));
			while(wr2.seatingRound());
			System.out.println("Ans2: " + wr2.countOccupied());						
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}		
}

