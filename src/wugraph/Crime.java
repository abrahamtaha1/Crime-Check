package wugraph;

public class Crime {

	private int year;
	private String name;
	private String type;
	private int score;
	
	public Crime(String name, String type, int year) {
		
		this.name = name;
		this.type = type;
		this.year = year;
		
		int penalty = 0;
		
		if(year == 2017) {
			
			penalty = 2;
		}
		
		if(year == 2018) {
			
			penalty = 1;
		}
		
		
		if (type.equals("VIOLATION")) {
			
			this.score = 3 - penalty;
			
		}
			
		else if (type.equals("MISDEMEANOR")) {
			
			this.score = 6 - penalty;
		}

		else if (type.equals("FELONY")) {
			
			this.score = 12 - penalty;
		}
		
	}
	
	public int getScore() {
		
		return this.score;
	}
	
	public String getType() {
		
		return this.type;
	}
	
	public String getName() {
		
		return this.name;
	}
	
	public String toString() {
		
		return this.name;
	}
	
	public int getYear() {
		
		return this.year;
		
	}
	
}
