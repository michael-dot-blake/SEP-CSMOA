package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Job;

/**
 * @author Yun Wang
 * 
 *
 */

public class HistoricalJobFactory {

    public static void main(String[] args) throws IOException {

    	String path = "/JobFiles/testHisData.csv";
        File file = new File(path);
        File[] array = file.listFiles();
        StringBuilder data = new StringBuilder();
        int count  = 0;
        int fileCount = 1;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i].getName() + "\n");
            InputStreamReader reader = new InputStreamReader(new FileInputStream(path + array[i].getName()));
            BufferedReader br = new BufferedReader(reader);
            int lines = 0;
            int number = 2;
            String line = "";
            line = br.readLine();
            List <Job> jobList = new ArrayList<Job>();
            while (line != null && line != "") {
                lines++;
                line = br.readLine();
                if(lines >= number & line != null){
                    String[] jobString = line.split(",");
                    Job job = new Job();
                    job.setOrderNum(jobString[0]);
                    job.setOrderType(jobString[1]);
                    job.setOrderDescription(jobString[2]);
                    job.setIssuceCode(jobString[3]);
                    job.setIssuedescription(jobString[4]);
                    job.setMainActType(jobString[5]);
                    job.setMainActDescription(jobString[6]);
                    job.setOrderCreateDate(jobString[7]);
                    job.setOrderCreateTime(jobString[8]);
                    job.setJobPriority(jobString[9]);
                    job.setSuburb(jobString[10]);
                    job.setStreet(jobString[11]);
                    int houseNum1 = Integer.valueOf(jobString[12]);
                    job.setHouseNum1(houseNum1);
                    job.setHouseNum2(jobString[13]);
                    int postCode = Integer.valueOf(jobString[14]);
                    job.setPostcode(postCode);
                    int fitterDistrict = Integer.valueOf(jobString[15]);
                    job.setFitterDistrict(fitterDistrict);
                    int workTime = Integer.valueOf(jobString[16]);
                    job.setWorkTime(workTime);
                    jobList.add(job);
                	
                	}  
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
