package net.j7k.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AoC2020_02 {

	static boolean isValidPwd1(String input)
	{
		boolean result = false;
		
		String pattern = "([0-9]+)-([0-9]+)\\s([a-z]):\\s([a-z]+)";
		Matcher m =  Pattern.compile(pattern).matcher(input);
		
		if (m.find())
		{
			int min = Integer.parseInt(m.group(1));
			int max = Integer.parseInt(m.group(2));
			char c  = m.group(3).charAt(0);
			String pwd = m.group(4);
			long nbOccurences = pwd.chars().filter( x -> x == c).count();
			
			result = ( (min <= nbOccurences) && (nbOccurences <= max) );
		}
		else 
			throw new IllegalArgumentException(input);
		
		return result;
	}
	
	static boolean isValidPwd2(String input)
	{
		boolean result = false;
		
		String pattern = "([0-9]+)-([0-9]+)\\s([a-z]):\\s([a-z]+)";
		Matcher m =  Pattern.compile(pattern).matcher(input);
		
		if (m.find())
		{
			int min = Integer.parseInt(m.group(1));
			int max = Integer.parseInt(m.group(2));
			char c  = m.group(3).charAt(0);
			String pwd = m.group(4);
			
			result = (pwd.charAt(--min) == c) ^ (pwd.charAt(--max) == c); // indexes start at 1 in input file.
		}
		else 
			throw new IllegalArgumentException(input);
		
		return result;
	}

	public static void main(String[] args) {
		try
		{
			Path datafile= Path.of(args[0]);
			
			long countValidPwd1 = Files.lines(datafile).filter(AoC2020_02::isValidPwd1).count();
			long countValidPwd2 = Files.lines(datafile).filter(AoC2020_02::isValidPwd2).count();
			
			System.out.println("Valid 1st method: " + countValidPwd1);
			System.out.println("Valid 2nd method: " + countValidPwd2);			
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
