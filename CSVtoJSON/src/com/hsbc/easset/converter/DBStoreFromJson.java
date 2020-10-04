package com.hsbc.easset.converter;
import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

import com.hsbc.easset.helpers.DBHelper;



//Storing Data from json to DB
public class DBStoreFromJson {
	
	private static Connection conn;
	private static PreparedStatement pre;
	private ResultSet resultSet;
	private Statement stmt;
	private static boolean status;
	private static ResourceBundle resourceBundle;
	private static int uniqueId;
	
	static
	{
		resourceBundle=ResourceBundle.getBundle("com/hsbc/easset/resources/db");
	}
	public  DBStoreFromJson()
	{		
		resourceBundle=ResourceBundle.getBundle("com/hsbc/easset/resources/db");
	}
	public static boolean addUser() {
		// TODO Auto-generated method stub
		JSONParser parser = new JSONParser();
			try
			{
			Object obj;
				obj = parser.parse(new FileReader("E:/Study/SpringToolWorkSpace/HSBC/Users.json"));           //location of json file
			
			conn=DBHelper.getConnection();
			pre=conn.prepareStatement(resourceBundle.getString("adduser"));
			JSONArray jsonObject = (JSONArray)obj;
			
			//uniqueId++;
			int i =0;
			int j =50;
			Iterator iterator = ((List) jsonObject).iterator();
	         while (iterator.hasNext()) {
	        	 JSONObject name = (JSONObject)jsonObject.get(i);
				pre.setInt(1,j);
				pre.setString(2, name.get("name").toString());
				pre.setString(3, name.get("telNo").toString());
				pre.setString(4, name.get("role").toString());
				pre.setString(5, name.get("mailId").toString());
				pre.setString(6, name.get("userName").toString());
				pre.setString(7, name.get("password").toString());
				pre.setDate(8, Date.valueOf("2020-03-09"));
				pre.executeUpdate();
				j++;
				iterator.next();
	         }
				conn.commit();
				status=true;
			} catch (FileNotFoundException|SQLException e) {
				// TODO Auto-generated catch block
				
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//throw new DBConnCreationException("Connection Error Occurred");
		
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return status;

	}

	
	 public static void main(String[] args) {
	      
	     addUser();
	         
	         
	   }

}



