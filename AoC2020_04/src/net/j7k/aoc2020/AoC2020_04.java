package net.j7k.aoc2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class AoC2020_04 {
	
	static final String BYR_RULE = "[0-9]{4}";
	static final String IYR_RULE = "[0-9]{4}";
	static final String EYR_RULE = "[0-9]{4}";
	static final String HGT_RULE = "[0-9]+(in|cm)";
	static final String HCL_RULE = "#([0-9a-f]){6}";
	static final String ECL_RULE = "(amb|blu|brn|gry|grn|hzl|oth)";
	static final String PID_RULE = "[0-9]{9}";



	static boolean isValidPassport(String input)
	{
		String[] items = {"byr","iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
		boolean found = true;
	    
		for (String item : items) {
	        if (!input.contains(item)) {
	            found = false;
	            break;
	        }
	    }
	    return found;
	}

	static boolean isValidPpty(String pptyString)
	{
		boolean result = false;
		
		String[] pptyPair = pptyString.split(":");	
		String key = pptyPair[0];
		String value = pptyPair[1];
		
		if ( key.equals("byr") && (value.matches(BYR_RULE)) )
		{
			int year = Integer.parseInt(value);
			result = (1920 <= year) && (year <= 2002);
		}
		else if ( key.equals("iyr") && (value.matches(IYR_RULE)) )
		{
			int year = Integer.parseInt(value);
			result = (2010 <= year) && (year <= 2020);
		}
		else if ( key.equals("eyr") && (value.matches(EYR_RULE)) )
		{
			int year = Integer.parseInt(value);
			result = (2020 <= year) && (year <= 2030);
		}
		else if ( key.equals("hgt") && (value.matches(HGT_RULE)) )
		{
			int height = Integer.parseInt(value.substring(0, value.length() - 2));
			String unit = value.substring(value.length() - 2, value.length());
			
			if (unit.equals("in"))
				result = ( (59 <= height) && (height <= 76) );
			else
				result = ( (150 <= height) && (height <= 193) );				
		}
		else if ( key.equals("hcl") && (value.matches(HCL_RULE)) )
		{
			result = true;
		}
		else if ( key.equals("ecl") && (value.matches(ECL_RULE)) )
		{
			result = true;
		}
		else if ( key.equals("pid") && value.matches(PID_RULE) )
		{
			result = true;
		}		
		else if ( key.equals("cid") )
		{
			result = true;
		}
		return result;
	}
	
	static boolean isValidPassport2(String input)
	{
		boolean result = true;
		String[] ppties = input.split("\\s\\n?");
		
		for (String ppty : ppties)
		{
			result = result && isValidPpty(ppty);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		try
		{
			Path datafile= Path.of(args[0]);
			String data = Files.readString(datafile);
			
			String[] passports = data.split("(\\r?\\n){2,}");
			System.out.println("Found " + passports.length + " pass");
			
			long validPpt1 = Arrays.stream(passports).filter(AoC2020_04::isValidPassport).count();	
			System.out.println("NB valid ppt: " + validPpt1);
			
			long validPpt2 = Arrays.stream(passports)
					.filter(AoC2020_04::isValidPassport)
					.filter(AoC2020_04::isValidPassport2)
					.count();	
			System.out.println("NB valid ppt: " + validPpt2);
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
