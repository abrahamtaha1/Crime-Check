package wugraph;

public class BinarySearch
{
	/*
	 * given a sorted array of places
	 * given a place
	 * return index of that place (search for a place in places)
	 */
	public static int BinarySearch(Place[] P, String place)
	{
		String[] places = new String[53];
		place = place.toUpperCase();
		
		for (int i = 0; i < places.length; i++)
			places[i] = P[i].getPlace();
		
		int low = 0;
		int high = places.length - 1;
		
		while (low <= high)
		{
			int mid = low + (high - low) / 2;
			
			int res = place.compareTo(places[mid]);
			
			if (res > 0) low = mid + 1;
			else if (res < 0) high = mid - 1;
			else return mid;
		}
		System.out.println("failed search");
		return -1;
	}
}
