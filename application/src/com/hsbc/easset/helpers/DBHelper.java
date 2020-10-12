package com.hsbc.easset.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBHelper {
	
	
	private static ResourceBundle resourceBundle;

	public static Connection getConnection() throws SQLException
	{
		Connection conn=null;
		resourceBundle=ResourceBundle.getBundle("com/hsbc/easset/resources/db");
		System.out.println("Got resource bundle DB Helper");
		String url=resourceBundle.getString("url");
		
		String userName=resourceBundle.getString("username");
		String password=resourceBundle.getString("password");
		String driver=resourceBundle.getString("driver");
		System.out.println("url "+url+" passowrd"+password+" userName "+userName+" driver "+driver);
		try {
			System.out.println("In try block DB Helper");
			Class.forName(driver);
			conn=DriverManager.getConnection(url,userName,password);
			System.out.println("conn=DriverManager.getConnection(url,userName,password);"+conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("DB Helper ERROR:"+e.getMessage());
		}
		System.out.println("returning conn="+conn);
		return conn;
		
	}

}
