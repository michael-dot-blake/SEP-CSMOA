package controller;


/**
 * @author Rakibul Hasan Arif
 * 
 * A class to create CompletedJob objects which are
 * records of jobs which have been assigned and completed
 */

public class RandomJobFactory {

	public static void main(String[] args) {
		
		DataBaseConnection rj=new DataBaseConnection();
		rj.selectAll();
		rj.countMost();
		
		
		
	}
	
	

	


}
