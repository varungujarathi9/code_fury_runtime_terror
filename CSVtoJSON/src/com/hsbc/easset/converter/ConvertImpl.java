package com.hsbc.easset.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
 
import com.fasterxml.jackson.databind.ObjectMapper;
 
public class ConvertImpl {
  
    public static void main(String[] args) {
      // Step 1: Read CSV File into Java List Objects
    	List<User> Users = readCsvFile("E:/Study/SpringToolWorkSpace/HSBC/User.csv");
      
      // Step 2: Convert Java List Objects to JSON File
      convertJavaObject2JsonFile(Users, "E:/Study/SpringToolWorkSpace/HSBC/Users.json");
    }
 
    /**
     * 
     * Read CSV File into Memory
     * @param filePath
     * @return
     */
    private static List<User> readCsvFile(String filePath) {
    BufferedReader fileReader = null;
    CSVParser csvParser = null;
 
    List<User> Users = new ArrayList<User>();
    
    try {
      fileReader = new BufferedReader(new FileReader(filePath));
      csvParser = new CSVParser(fileReader,
          CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
 
      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
      
      for (CSVRecord csvRecord : csvRecords) {
        User User = new User(
        		 csvRecord.get("UNIQUEID"),
 	            csvRecord.get("NAME"),
 	            csvRecord.get("TELEPHONENUMBER"),
 	            csvRecord.get("ROLE"),
 	            csvRecord.get("EMAILID"),
 	            csvRecord.get("USERNAME"),
 	            csvRecord.get("PASSWORD")  
 	            );
        
        Users.add(User);
        System.out.println();
      }
 
    } catch (Exception e) {
      System.out.println("Reading CSV Error!");
      e.printStackTrace();
    } finally {
      try {
        fileReader.close();
        csvParser.close();
      } catch (IOException e) {
        System.out.println("Closing fileReader/csvParser Error!");
        e.printStackTrace();
      }
    }
    
    return Users;
  }
 
    /**
     * Convert Java Object to JSON File
     * @param Users
     * @param pathFile
     */
    private static void convertJavaObject2JsonFile(List<User> Users, String pathFile) {
        ObjectMapper mapper = new ObjectMapper();
 
        File file = new File(pathFile);
        try {
            // Serialize Java object info JSON file.
            mapper.writeValue(file, Users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

