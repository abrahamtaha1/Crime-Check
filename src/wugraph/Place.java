package wugraph;

public class Place {
	private String label;
	private double lat;
	private double lon;
	private double crimeWeight;
	
	public Place(String l, double lat, double lon) {
		this.label = l.toUpperCase();
		this.lat = lat;
		this.lon = lon;
	}
	
	public void setWeight(double rate) {
		this.crimeWeight = rate;
	}

	public double getWeight() {
		return crimeWeight;
	}
	
	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}
	
	public String getPlace() {
		return this.label;
	}
	
	public double getRate() {
		return this.crimeWeight;
	}
	
	@Override
	public boolean equals(Object O) {
		if (O == this) return true;
		
		if (!(O instanceof Place)) return false;
		
		Place compareable = (Place) O;
		return this.getPlace().equals(compareable.getPlace()) && this.getRate() == compareable.getRate();
	}
	
	@Override
	public int hashCode() {
		return this.getPlace().hashCode();
	}
}
