package net.j7k.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AoC2020_10 {
	
	static long dp(int index, List<Integer> adapters, Map<Integer, Long> cache)
	{
		if (!cache.containsKey(index))
		{
			if (index == 0)
				cache.put(index, 1L);
			
			else
			{
				int ancestorIdx = index - 1;
				int joltage = adapters.get(index);
				long total=0;
				while( (ancestorIdx >= 0) && (joltage - adapters.get(ancestorIdx) <= 3)  )
				{
					total += dp(ancestorIdx, adapters, cache);
					--ancestorIdx;
				}
				cache.put(index, total);
			}
		}
		return cache.get(index);
	}
	
	static long nbAdapterPaths(List<Integer> adapters)
	{
		return dp(adapters.size()-1,adapters, new HashMap<Integer, Long>());
	}
	
	
	public static void main(String[] args) {
		try
		{
			Path datafile= Path.of(args[0]);
			List<Integer> adapters = Files.lines(datafile).map(Integer::parseInt).collect(Collectors.toList());
			
			int max = adapters.stream().max(Integer::compare).get();
			adapters.add(0, 0);
			adapters.add(max + 3);
			adapters.sort(Integer::compare);
			
					
			long oneJDiff = IntStream.range(1, adapters.size())
					.map(i -> adapters.get(i) - adapters.get(i-1))
					.filter(x -> x == 1)
					.count();
			
			long threeJDiff = IntStream.range(1, adapters.size())
					.map(i -> adapters.get(i) - adapters.get(i-1))
					.filter(x -> x == 3)
					.count();
			
			long answer = oneJDiff * threeJDiff;
			
			System.out.println("Ans: " + answer);
			System.out.println("Ans2: " + nbAdapterPaths(adapters));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}	
	
}
