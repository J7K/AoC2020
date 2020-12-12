package net.j7k.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;


public class AoC2020_12 {

	public static void main(String[] args) {
		try
		{
			Path datafile= Path.of(args[0]);

			Ship ship = new Ship();	
			Files.lines(datafile).map(Order::new).forEach(order -> ship.execute(order));
			System.out.println("Ans1: " + ship.L1DistToOrigin());
			
			Ship2 ship2 = new Ship2(10, -1);
			Files.lines(datafile).map(Order::new).forEach(order -> ship2.execute(order));
			System.out.println("Ans2: " + ship2.L1DistToOrigin());					
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}	
}
