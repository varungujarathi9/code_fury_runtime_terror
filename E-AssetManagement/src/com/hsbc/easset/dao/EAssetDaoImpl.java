package com.hsbc.easset.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

import com.hsbc.easset.helpers.DBHelper;
import com.hsbc.easset.models.User;

public class EAssetDaoImpl implements EAssetDao{

	private Connection conn;
	private PreparedStatement pre;
	private ResultSet resultSet;
	private Statement stmt;
	private boolean status;
	private ResourceBundle resourceBundle;
	private static int uniqueId;
	
	public  EAssetDaoImpl()
	{
			
		resourceBundle=ResourceBundle.getBundle("com/hsbc/easset/resources/db");
	}

	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		
			try
			{
			conn=DBHelper.getConnection();
			pre=conn.prepareStatement(resourceBundle.getString("adduser"));
			uniqueId=new Random().nextInt(10000)+1;	
				pre.setInt(1,uniqueId);
				pre.setString(2, user.getName());
				pre.setLong(3, user.getTelphoneNumber());
				pre.setString(4, user.getRole().toString());
				pre.setString(5, user.getEmailId());
				pre.setString(6, user.getUsername());
				pre.setString(7, user.getPassword());
				pre.setDate(8, Date.valueOf("2020-03-09"));	
				pre.executeUpdate();
				conn.commit();
				status=true;
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error Code"+e.getErrorCode());
			System.out.println(e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//throw new DBConnCreationException("Connection Error Occurred");
		}
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

	@Override
	public boolean validateLogin(User user) {
		// TODO Auto-generated method stub
		
	//	System.out.println("entered dao");
		boolean status=false;
		
		
		try {
			conn=DBHelper.getConnection();
			pre=conn.prepareStatement(resourceBundle.getString("loginQuery"));
			pre.setString(1, user.getUsername());
            pre.setString(2, user.getPassword());
            resultSet=pre.executeQuery();
            resultSet.next();
            if(resultSet.getInt(1)>0)
            {
            	status=true;
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	
	

	
	
	
	

}
