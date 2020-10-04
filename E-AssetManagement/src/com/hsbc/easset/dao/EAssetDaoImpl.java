package com.hsbc.easset.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.PropertyResourceBundle;

import com.hsbc.easset.exceptions.DBConnCreationException;
import com.hsbc.easset.helpers.DBHelper;
import com.hsbc.easset.models.Asset;
import com.hsbc.easset.models.User;

import jdk.nashorn.internal.ir.debug.JSONWriter;

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

	public boolean addUser(User user) throws DBConnCreationException {
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
			throw new DBConnCreationException("Connection Error Occurred");
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
	public boolean validateLogin(User user) throws DBConnCreationException{
		// TODO Auto-generated method stub

	//	System.out.println("entered dao");
		boolean status=false;
				try {
					conn=DBHelper.getConnection();
					pre=conn.prepareStatement(resourceBundle.getString("loginQuery"));
					pre.setString(1, user.getUsername());
					pre.setString(2,user.getPassword());
					resultSet=pre.executeQuery();
					resultSet.next();
					if(resultSet.getInt(1)>0)
					{
						status=true;
					}


				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new DBConnCreationException("Connection Error Occurred");
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
		public boolean addAsset(Asset asset) throws DBConnCreationException {
			// TODO Auto-generated method stub
			boolean status=false;
			try
			{
			conn=DBHelper.getConnection();
			pre=conn.prepareStatement(resourceBundle.getString("addasset"));
			uniqueId=new Random().nextInt(10000)+1;
				pre.setInt(1,uniqueId);
				pre.setString(2, asset.getName());
				pre.setString(3, asset.getAssetType());
				pre.setString(4, asset.getDescription());
				pre.setDate(5, Date.valueOf(asset.getDateAdded()));
				pre.setBoolean(4, asset.isAvailable());
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
			throw new DBConnCreationException("Connection Error Occurred");
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
		public boolean isAdmin(User user) throws DBConnCreationException {
			// TODO Auto-generated method stub
			boolean status=false;
			try {
				conn=DBHelper.getConnection();
				pre=conn.prepareStatement(resourceBundle.getString("roleQuery"));
				pre.setString(1, user.getUsername());
				resultSet=pre.executeQuery();
				resultSet.next();
				if(resultSet.getString(1).equals(1)) //if we formerly store admin role as 1 and burrower as 0 in db
				{
					status=true;
				}


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBConnCreationException("Connection Error Occurred");
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
	public List<Asset> showAvailableAssets() throws SQLException {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
				List<Asset> assetList=new ArrayList<Asset>();
				//assetList=null;
				Asset asset=null;
				try {
					conn=DBHelper.getConnection();
					stmt=conn.createStatement();
					resultSet=stmt.executeQuery(resourceBundle.getString("getAssets"));
					
					while(resultSet.next())
					{

						asset=new Asset();
						asset.setName(resultSet.getString(2));
						asset.setAssetType(resultSet.getString(3));
						asset.setAssetType(resultSet.getString(4));
						asset.setDateAdded(LocalDate.parse(resultSet.getDate(5).toString()));
						asset.setAvailable(true);
						assetList.add(asset);	
								
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					//System.out.println(e.getErrorCode());
					throw new SQLException("Connection Error Occurred");
				}
				finally
				{
					conn.close();
				}
				

				System.out.println("returned ");
				return assetList;

	}

//	@Override
//	public boolean issueAvailableAssets(List<Asset> assetList) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean returnAssets(List<Asset> assetList) {
//		// TODO Auto-generated method stub
//		return false;
//	}
	public boolean existsCategory(String categoryName) throws SQLException {
		// TODO Auto-generated method stub
		boolean status=false;
		try {
			conn=DBHelper.getConnection();
			pre=conn.prepareStatement(resourceBundle.getString("categoryQuery"));
			pre.setString(1, categoryName);
			resultSet=pre.executeQuery();
			resultSet.next();
			if(resultSet.getInt(1)>0)
			{
				status=true;
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException("Connection Error Occurred");
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
	public boolean addCategory(String categoryName, int lendingPeriod, int lateFees)
			throws SQLException {
		// TODO Auto-generated method stub
		boolean status=false;
		try
		{
		conn=DBHelper.getConnection();
		pre=conn.prepareStatement(resourceBundle.getString("addcategory"));
			pre.setString(1, categoryName);
			pre.setInt(2, lendingPeriod);
			pre.setInt(3, lateFees);
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
		throw new SQLException("Connection Error Occurred");
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
