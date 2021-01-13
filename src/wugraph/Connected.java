package wugraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Connected 
{
	
	public static Graph graph;
	
	public static Place [] getAreas() throws IOException
	{
		Place areas [] = new Place [53];
		ArrayList<Crime>[] crimes = new ArrayList [53];
	
		for (int i = 0; i < 53; i++) 
			crimes[i] = new ArrayList<Crime>();
		
		FileReader f = new FileReader("Connections.csv");

		
		BufferedReader br = new BufferedReader(f);
		br.readLine();

		int i = 0;
		String line = "";
		while((line = br.readLine()) != null) 
		{
			String[] row = line.split(",");
			areas[i] = new Place(row[0], Double.parseDouble(row[1]), Double.parseDouble(row[2]));
			i++;
			if (i == 53) break;
		}
			
		f = new FileReader("Dataset (NYC).csv");
		br = new BufferedReader(f);
		br.readLine();
			
		while((line = br.readLine()) != null) 
		{
			//split based on commas
			String[] row = line.split(",");
						
			double latitude = Double.parseDouble(row[4]);
			double longitude = Double.parseDouble(row[5]);
						
			for (int j = 0; j < areas.length; j++) 
				if (Math.abs(latitude - areas[j].getLat()) <= 0.045 && Math.abs(longitude - areas[j].getLon()) <= 0.045)
					crimes[j].add(new Crime(row[3], row[2], Integer.parseInt(row[0].substring(6, 10))));
		}
		
		br.close();
		
		for (int j = 0; j < areas.length; j++) 
		{
			double total = 0;
			for (int k = 0; k < crimes[j].size(); k++)
				total += crimes[j].get(k).getScore();
			
			areas[j].setWeight(total);
		}
		return areas;
	}
	
	public static Graph AddConnEdge() throws Exception
	{
		
		//make places arraylist and add places to graph
		Graph graph = new Graph();
		
		//Add vertices
		Place[] places = getAreas();
		
		for(int i = 0; i < places.length; i++)
			graph.addPlace(places[i]);
		
		for(int i = 0; i < places.length - 1; i++)
			if((places[i].getLon() - places[i+1].getLon()) <= 0.11 && (places[i].getLat() - places[i+1].getLat()) <= 0.11)
				graph.addRoad(places[i], places[i + 1]);
		
		return graph;
	}
	
	
	public static void printLinkedList(Graph graph) 
	{
		Map<Place, ArrayList<Place>> rep = graph.getAdjList();
		Set<Place> repSet = rep.keySet();
		for (Place i : repSet) {
			System.out.print(i.getPlace() + ": ");
			for (Place j : rep.get(i))
				System.out.print(j.getPlace() + " - ");
			System.out.println();
		}
	}
}
