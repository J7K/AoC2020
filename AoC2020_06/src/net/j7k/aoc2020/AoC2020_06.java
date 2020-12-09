package net.j7k.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AoC2020_06 {
	 
	public static Set<Integer> unionOfUniqueAnswers(String groupAns)
	{
		return groupAns.replaceAll("\\W", "")
				.chars()
				.boxed()
				.collect(Collectors.toSet());
	}
	
	public static Set<Integer> isecOfUniqueAnswers(String groupAns)
	{
		String[] answers = groupAns.split("\n");
		List<Set<Integer>> uniqueAns = Arrays.stream(answers)
				.map(ans -> ans.chars()
						.boxed()
						.collect(Collectors.toSet()))
				.collect(Collectors.toList());
		
		return uniqueAns.stream()
				.skip(1)
				.collect(()->new HashSet<>(uniqueAns.get(0)), Set::retainAll, Set::retainAll);
	}
		
	public static void main(String[] args) {
		try
		{
			Path datafile= Path.of(args[0]);
			String data = Files.readString(datafile);
			
			String[] answerGroups = data.split("(\\r?\\n){2,}");
			
			long ansSum = Arrays.stream(answerGroups)
					.map(AoC2020_06::unionOfUniqueAnswers)
					.mapToInt(union -> union.size())
					.sum();		
			System.out.println("Sum of group answers: " + ansSum);
			
			long ansSum2 = Arrays.stream(answerGroups)
					.map(AoC2020_06::isecOfUniqueAnswers)
					.mapToInt(i -> i.size())
					.sum();
			System.out.println("Sum of group answers: " + ansSum2);
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
