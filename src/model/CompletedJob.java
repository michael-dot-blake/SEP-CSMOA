package model;

/**
 * @author Michael Blake
 * 
 * A class to create CompletedJob objects which are
 * records of jobs which have been assigned and completed
 */

public class CompletedJob extends Job {
	
	
	public String toString() {
		return "\nJob Details" +"\nJob ID: " + this.getOrderNum() +  "\nFitterDistrict: " + getFitterDistrict();
	}

	
	
}
