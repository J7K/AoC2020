package net.j7k.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AoC2020_05 {
	
	 private static List<Integer> allIDs() {
		 return IntStream.range(0, 128)
	                .boxed()
	                .map(row -> IntStream.range(0, 8)
	                        .boxed()
	                        .map(seat -> BoardingPass.computeSeatID(row, seat))
	                        .collect(Collectors.toList()))
	                .flatMap(List::stream)
	                .sorted()
	                .collect(Collectors.toList());
	 }
	 
	public static void main(String[] args) {
		try
		{
			Path datafile= Path.of(args[0]);
			int max = Files.lines(datafile)
					.map(BoardingPass::new)
					.mapToInt(BoardingPass::getID)
					.max()
					.getAsInt();
			
			System.out.println("Max ID " + max);
			
			List<Integer> occupiedIDs = Files.lines(datafile)
					.map(BoardingPass::new)
					.mapToInt(BoardingPass::getID)
					.boxed()
					.collect(Collectors.toList());
			
			List<Integer> allSeatsIDs = allIDs();
			allSeatsIDs.removeAll(occupiedIDs);
		
			Predicate<Integer> hasEmptyNeighbor = seatID -> ( allSeatsIDs.contains(seatID-1) || allSeatsIDs.contains(seatID+1) );
			allSeatsIDs.removeIf(hasEmptyNeighbor);
			allSeatsIDs.forEach(System.out::println);
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
