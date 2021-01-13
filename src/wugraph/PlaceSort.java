package wugraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class PlaceSort 
{
	public static ArrayList<Place> places;

	public static Place[] Sort(Place[] areas) throws Exception
	{	
		for(int i1 = 0; i1< areas.length -1; i1++) {
	         for (int j = i1+1; j<areas.length; j++) {
	            if(areas[i1].getPlace().compareTo(areas[j].getPlace())>0) {
	               Place temp = areas[i1];
	               areas[i1] = areas[j];
	               areas[j] = temp;
	            }
	         }
	      }
		
	return areas;
	}
	
	public static void print(Place areas [])
	{
		int count = 0;
		for(int i = 0; i < areas.length; i++){
			System.out.println(areas[i].getPlace());
			count++;
		}
	}
}
