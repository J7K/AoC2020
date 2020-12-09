package net.j7k.aoc2020;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

public class AoC2020_07 {

	public static SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> bagGraph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
	
	public static void decodeLine(String input)
	{
		String pattern = "(.+?)\\sbags\\scontain\\s((.+))\\.";
		
		Matcher m =  Pattern.compile(pattern).matcher(input);
		if (m.find())
		{
			String destination = m.group(1);
			bagGraph.addVertex(destination);
			
			String orgs = m.group(2);
			String p2 = "(\\d+)\\s(.+?)\\sbags?";
			m = Pattern.compile(p2).matcher(orgs);
			while(m.find())
			{
				String origin = m.group(2);
				double weight = Double.parseDouble(m.group(1));
				bagGraph.addVertex(origin);
				DefaultWeightedEdge edge = bagGraph.addEdge(origin,destination);
				bagGraph.setEdgeWeight(edge, weight);
			}
		}
		else 
			throw new IllegalArgumentException(input);
	}
	
	public static int countBags(String color)
	{
		int result = 0;
		for (DefaultWeightedEdge edge : bagGraph.incomingEdgesOf(color))
		{
			int weight = (int)bagGraph.getEdgeWeight(edge);
			result += (weight + weight * countBags(bagGraph.getEdgeSource(edge)));
		}		
		return result;
	}
		
	public static void main(String[] args) {
		try
		{
			Path datafile= Path.of(args[0]);
			Files.lines(datafile).forEach(AoC2020_07::decodeLine);
			
			Iterator<String> iterator = new BreadthFirstIterator<>(bagGraph, "shiny gold");
	        int ancestorCount = 0;
	        iterator.next(); // Skip input node
			while (iterator.hasNext()) {
	           iterator.next();
	           ancestorCount++;
	        }	
	        System.out.println("Nb ancestors: " + ancestorCount);
	        System.out.println("Nb bags: " + countBags("shiny gold"));	        
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
