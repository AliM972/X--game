// Data.java --> This class represents the records that will be stored in the HashDictionary
public class Data { 
	// Declaring instance variables to be used throughout the class
	private String config;
	private int score;
	
	public Data(String config, int score) { // Constructor takes in a configuration and score, which make up a  record
		this.config = config;	// Instance variables initialized
		this.score = score;
	}
	
	public String getConfiguration() {	// Getter method to return a record's configuration
		return this.config;
	}
	
	public int getScore() {	// Setter method to return a record's score
		return this.score;
	}
}