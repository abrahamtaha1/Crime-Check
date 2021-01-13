package wugraph;

import java.util.*;
import java.util.Map.Entry;

import wugraph.Place;
import wugraph.Connected;

public class Graph {
	private Map<Place, ArrayList<Place>> AdjList;
	
	public Graph() {
		this.AdjList = new HashMap<Place, ArrayList<Place>>();
	}
	
	public Map<Place, ArrayList<Place>> getAdjList() {
		return this.AdjList;
	}
		
	public void addPlace(Place place) {
		this.AdjList.putIfAbsent(place, new ArrayList<Place>());
	}
	
	public void addRoad(Place a, Place b) {
		this.getAdjList().get(a).add(b);
		this.getAdjList().get(b).add(a);
	}
	
	public ArrayList<Place> dijPath(Place source, Place goal) {
		Map<Place, Double> distances = new HashMap<Place, Double>();
		Map<Place, Place> pathMap = new HashMap<Place, Place>();
		
		Set<Place> keySet = this.getAdjList().keySet();
		Set<Place> places = new HashSet<Place>();
		for (Place place : keySet) {
			places.add(place);
		}
		
		distances.putIfAbsent(source, 0.0);
		for (Place place : places) {
			distances.putIfAbsent(place, Double.POSITIVE_INFINITY);
			pathMap.putIfAbsent(place, null);
		}
		
		while (!(places.isEmpty())) {
			Place minPlace = null;
			for (Place place : places) {
				if (minPlace == null || distances.get(place) < distances.get(minPlace)) {
					minPlace = place;
				}
			}
			places.remove(minPlace);
			if (minPlace == goal) break;
			
			for (Place v : this.getAdjList().get(minPlace)) {
				double edgeNum = distances.get(minPlace) + (minPlace.getWeight() + v.getWeight())/2;
				if (edgeNum < distances.get(v)) {
					distances.replace(v, edgeNum);
					pathMap.replace(v, minPlace);
				}
			}
		}
		
		if (pathMap.get(goal) == null) {
			return null;
		}

		
		ArrayList<Place> path = new ArrayList<Place>();
		Place pathFinder = goal;
		if (pathMap.get(pathFinder) != null || pathFinder == source) {
			while(pathMap.get(pathFinder) != null) {
				path.add(pathFinder);
				pathFinder = pathMap.get(pathFinder);
			}
		}
		path.add(source);
		Collections.reverse(path);
		return path;

	}

}
