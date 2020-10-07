package com.hsbc.easset.dao;
import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.PropertyResourceBundle;

import com.hsbc.easset.helpers.DBHelper;
import com.hsbc.easset.models.Asset;
import com.hsbc.easset.models.User;

import jdk.nashorn.internal.ir.debug.JSONWriter;

public class EAssetDaoImpl implements EAssetDao{

	private Connection conn;
	private PreparedStatement pre;
	private ResultSet resultSet;
	private ResultSet resultSet1;
	private ResultSet resultSet2;
	private ResultSet resultSet3;
	private Statement stmt;
	private boolean status;
	private ResourceBundle resourceBundle;
	private static int uniqueId;

	public  EAssetDaoImpl()
	{
		
		resourceBundle=ResourceBundle.getBundle("com/hsbc/easset/resources/db");
	}

	public boolean addUser(User user) throws SQLException,SQLIntegrityConstraintViolationException {
		// TODO Auto-generated method stub

			try
			{
			conn=DBHelper.getConnection();
			pre=conn.prepareStatement(resourceBundle.getString("adduser"));
		//	uniqueId=new Random().nextInt(10000)+1;
		//		pre.setInt(1,uniqueId);
				pre.setString(2, user.getName());
				pre.setLong(3, user.getTelphoneNumber());
				pre.setString(4, user.getRole().toString());
				pre.setString(5, user.getEmailId());
				pre.setString(6, user.getUsername());
				pre.setString(7, user.getPassword());
				pre.setDate(8, null);
				pre.executeUpdate();
				conn.commit();
				status=true;

		} catch(SQLIntegrityConstraintViolationException e)
			{
				System.out.println(e.getMessage());
				try {
				conn.rollback();
				} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new SQLIntegrityConstraintViolationException("Email Id or Username already exists.......");
			}
			catch (SQLException e) {
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

	@Override
	public boolean validateLogin(User user) throws SQLException{
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
						//update last login
						try
						{
							pre=conn.prepareStatement(resourceBundle.getString("addlastlogin"));
							pre.setDate(1, Date.valueOf(LocalDate.now()));
							pre.setString(2, user.getUsername());
							pre.executeUpdate();
							conn.commit();
							status=true;
						}
						catch (SQLException e) {
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
		public boolean addAsset(Asset asset) throws SQLException {
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
				pre.setDate(5, Date.valueOf(LocalDate.now()));
				//pre.setBoolean(6, true);
				pre.setString(6, "1");
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

	@Override
		public boolean isAdmin(User user) throws SQLException {
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
	public List<Asset> showAvailableAssets(int UserId) throws SQLException {	
		// TODO Auto-generated method stub
				List<Asset> assetList=new ArrayList<Asset>();
				LocalDate actualReturnDate,expectedReturnDate;
				int lendingPeriod,lateFees,banPeriod;
				String assetType=null;
				int assetId=0;
				PreparedStatement pre1,pre2,pre3;
				//assetList=null;
				Asset asset=null;
				try {
					conn=DBHelper.getConnection();
					stmt=conn.createStatement();
					pre1=conn.prepareStatement(resourceBundle.getString("getFromIssueLog"));
					pre1.setInt(1, UserId);
					resultSet1=pre1.executeQuery();
					while(resultSet1.next())
					{
						
						assetId=resultSet1.getInt(1);
						expectedReturnDate=resultSet1.getDate(2).toLocalDate();
						actualReturnDate=resultSet1.getDate(3).toLocalDate();
								
					}
					
					pre2=conn.prepareStatement(resourceBundle.getString("getAssetType"));
					pre2.setInt(1, assetId);
					resultSet2=pre2.executeQuery();
					while(resultSet2.next())
					{
						assetType=resultSet2.getString(1);
								
					}
					
					pre3=conn.prepareStatement(resourceBundle.getString("getFromAssetManagement"));
					pre3.setString(1, assetType);
					resultSet3=pre3.executeQuery();
					while(resultSet3.next())
					{
						lendingPeriod=resultSet3.getInt(1);
						lateFees=resultSet3.getInt(2);
						banPeriod=resultSet3.getInt(3);
								
					}
					
					
					//if()
					
					
					 // i)if expected date <todays date:
//		                  if(actual_return_date==null)
//		                      then "ban";
//		                  else 
//		                      ban_period=(select ban period from ast_mgmt where assetid=?)
//		                      if((ban_period+actualdate)<todays_date)
//		                          then "not ban"
//		                      else
//		                          "ban"
						  
					resultSet2=stmt.executeQuery(resourceBundle.getString("getAssetType"));
					resultSet3=stmt.executeQuery(resourceBundle.getString("getFromAssetManagemen"));
					
					
					
					
					
					resultSet=stmt.executeQuery(resourceBundle.getString("getAssets"));
					
					while(resultSet.next())
					{

						asset=new Asset();
						asset.setName(resultSet.getString(2));
						asset.setAssetType(resultSet.getString(3));
						asset.setDescription(resultSet.getString(4));
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

	@Override
	public int addImportUser(String filepath) throws SQLException {
		// TODO Auto-generated method stub
		int result=0;
	//	System.out.println(filepath);
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(filepath));
			conn=DBHelper.getConnection();
			pre=conn.prepareStatement(resourceBundle.getString("adduser"));
			JSONArray jsonObject = (JSONArray)obj;
			int i =0;
		     //to count how many users were successfully imported//
			  int UniqueID =1000;             //unique id///
			Iterator iterator = ((List) jsonObject).iterator();
	         while (iterator.hasNext()) {
	      
	        	 JSONObject name = (JSONObject)jsonObject.get(i);
	        	// LinkedHashMap<String, String> names = new LinkedHashMap<String, String>(name); 
	        	// System.out.println(names);
	        	    pre.setInt(1,UniqueID);  
					pre.setString(2, name.get("Name").toString());     ///this is been hardcoded..think of how to avoid hardcoding///
					pre.setString(3, name.get("Role").toString());
					pre.setString(4, name.get("Telephone").toString());
					pre.setString(5, name.get("Email").toString());
					pre.setString(6, name.get("UserName").toString());
					pre.setString(7, name.get("Password").toString());
					pre.setDate(8, Date.valueOf("2020-03-09"));
					pre.executeUpdate();
				UniqueID++;
					i++;
					iterator.next();
	         }
	         conn.commit();
	    //    System.out.println("data added");
	            result=i;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//location of json file
	
	finally
	{
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		  return result;
	}

	@Override
	public boolean borrowAssets(List<Asset> assetList) {
		// TODO Auto-generated method stub
	
//		2)borrow
//    a)check if is banned
//    b)show only available items
//    c)check if he already has the item
//    d)burrow item
//    e)set asset isavailble=false
//    f)item log entry
		
		Asset asset=null;
		try {
			conn=DBHelper.getConnection();
			stmt=conn.createStatement();
			resultSet=stmt.executeQuery(resourceBundle.getString("borrowAssets")); //make changes in db properties
			
			
//			 CREATE TABLE ISSUELOG (
//						ASSET_ID INT NOT NULL,
//						USER_ID INT NOT NULL,
//						ISSUE_DATE DATE NOT NULL,
//						EXPECTED_RETURN_DATE DATE NOT NULL,
//						ACTUAL_RETURN_DATE DATE,
//						ADMIN_ALERT VARCHAR(1) NOT NULL,
//						FOREIGN KEY(ASSET_ID) REFERENCES ASSETS(ASSET_ID),
//						FOREIGN KEY(USER_ID) REFERENCES USERS(USER_ID)
//		
			
		
//			
		
		);	
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
		
		
		
		
		
		
		
		return false;
	}

	







}
