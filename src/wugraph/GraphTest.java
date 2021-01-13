package wugraph;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GraphTest 
{
	Place areas[];
	Graph graph;
	int Beg,End;
	
	@Before
	public void setUp() throws Exception 
	{
		// Read data set files
		areas = Connected.getAreas();
		
		// Sort areas by place names
		PlaceSort.Sort(areas);
		
		// Create undirected graph
		graph = Connected.AddConnEdge();
		
		// Binary search for start and end places in areas array
		Beg = BinarySearch.BinarySearch(areas, "APOLLO THEATER");
		End = BinarySearch.BinarySearch(areas, "SMORGASBURG");
	}

	@After
	public void tearDown() throws Exception 
	{
		areas = null;
		graph = null;
		Beg = End = 0;
	}

	@Test
	public void testDijPath() 
	{
		// Call dijkstra to find path from start to end place
		ArrayList<Place> path = graph.dijPath(areas[Beg], areas[End]);
		String resultPath = new String();
		
		int expected = 4;
		int result = path.size();
		assertTrue(expected == result);
		
		for (Place p : path)
			resultPath+=p.getPlace() + " ";
			
		assertEquals("APOLLO THEATER CHELSEA MARKET  BROOKFIELD PLACE SMORGASBURG ", resultPath);
	}

}
