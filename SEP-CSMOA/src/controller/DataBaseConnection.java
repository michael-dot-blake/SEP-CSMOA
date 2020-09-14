package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Job;

/**
 * @author Rakibul Hasan Arif
 * 
 * A class to create connection with database 
 * 
 */
public class DataBaseConnection {

	ArrayList<Job> JobList = new ArrayList<>();	
	ArrayList<String> JobListPart = new ArrayList<>();	
	Map<String, Integer> hm = new HashMap<String, Integer>(); 
	private Connection connect() {
        // SQLite connection string
		String url = "jdbc:sqlite:C:\\Users\\61469\\Desktop\\Project\\SEP-CSMOA-dev\\db\\fieldworkdatabase.db";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
	
	public void selectAll(){
        String sql = "SELECT * from MainDataSet";
        
        try (Connection conn = this.connect();
        		Statement stmt  = conn.createStatement();
                ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	
//            	Job jd=new Job(rs.getString("ORDER_NUMBER"),rs.getString("SUBURB"));
//           	JobList.add(jd);
           	JobListPart.add(rs.getString("SUBURB"));
            	
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        JobListPart.remove(0);
        System.out.println(JobListPart.get(0));
        
    }
	
	public void countMost()
	{
        
        for (String i : JobListPart) { 
            Integer j = hm.get(i); 
            hm.put(i, (j == null) ? 1 : j + 1); 
        } 
  
        Map<String, Integer> hm1 = sortByValue(hm); 
        
        // print the sorted hashmap 
        for (Map.Entry<String, Integer> en : hm1.entrySet()) { 
            System.out.println("Key = " + en.getKey() +  
                          ", Value = " + en.getValue()); 
        } 

	}
	

    public static HashMap<String, Integer> sortByValue(Map<String, Integer> hm2) 
    { 
        // Create a list from elements of HashMap 
        List<Map.Entry<String, Integer> > list = 
               new LinkedList<Map.Entry<String, Integer> >(hm2.entrySet()); 
  
        // Sort the list 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue()); 
            } 
        }); 
          
        // put data from sorted list to hashmap  
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    } 
    
    public void createJOB()
    {
    	
    }
	
	
}
