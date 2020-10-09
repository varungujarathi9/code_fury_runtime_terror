package com.hsbc.easset.dao;
import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.PropertyResourceBundle;

//import com.hsbc.banking.models.Bank;
import com.hsbc.easset.helpers.DBHelper;
import com.hsbc.easset.models.Asset;
import com.hsbc.easset.models.RoleType;
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

	public boolean addUser(User user) throws SQLException,SQLIntegrityConstraintViolationException {
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
							pre.setDate(1, Date.valueOf(LocalDateTime.now().toString()));
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
			System.out.println("Establishing connection");
			conn=DBHelper.getConnection();
			System.out.println("Established connection");
			System.out.println(conn);
			System.out.println("Making pre statement");
			System.out.println(resourceBundle.getString("addasset"));
			pre=conn.prepareStatement(resourceBundle.getString("addasset"));
			System.out.println("Made prepared statement"+pre);

			uniqueId=new Random().nextInt(10000)+1;
				pre.setInt(1,uniqueId);
				pre.setString(2, asset.getName());
				pre.setString(3, asset.getAssetType());
				pre.setString(4, asset.getDescription());
				pre.setDate(5, Date.valueOf(LocalDate.now()));
				pre.setBoolean(6, true);
				pre.setBoolean(7, false);
				System.out.println("pre.executeUpdate();");

				pre.executeUpdate();
				System.out.println("conn.commit();\r\n");

				conn.commit();
				status=true;
				System.out.println("Added Asset Dao");

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
		public boolean isAdmin(String userName) throws SQLException {
			// TODO Auto-generated method stub
			boolean status=false;
			try {
				conn=DBHelper.getConnection();
				pre=conn.prepareStatement(resourceBundle.getString("roleQuery"));
				pre.setString(1, userName);
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
	public User getUserInfo(User user) throws SQLException {
		// TODO Auto-generated method stub
		User userObj=null;
		try {
			conn=DBHelper.getConnection();
			pre=conn.prepareStatement(resourceBundle.getString("userInfoQuery"));
			pre.setString(1,user.getUsername());
			resultSet=pre.executeQuery();
			resultSet.next();
			userObj=new User();
			user.setName(resultSet.getString(2));
			user.setTelphoneNumber(resultSet.getLong(3));
			user.setRole(RoleType.valueOf(resultSet.getString(4)));
			user.setEmailId(resultSet.getString(5));
			user.setLastLogin(LocalDateTime.ofInstant(resultSet.getDate(8).toInstant(), ZoneId.systemDefault()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getErrorCode());
			
			throw new SQLException("Connection Error Occurred");
		}
		finally
		{
			conn.close();
		}
		
		return userObj;
	}

	@Override
	public boolean returnAssets(List<String> assetList) {
		// TODO Auto-generated method stub
		
		boolean status = false;
		//Asset asset = null
		try {
			conn=DBHelper.getConnection();
			pre=conn.prepareStatement(resourceBundle.getString("returnAssets"));
			
			for(String str: assetList)
			{
				
				pre.setInt(1, Integer.parseInt(str));
				pre.setBoolean(6, true);
				
			}
			pre = conn.prepareStatement(resourceBundle.getString("issuelogUpdate"));
			for(String str: assetList)
			{
				pre.setInt(1, Integer.parseInt(str));
				//pre.setInt(2, x);
				pre.setDate(5, Date.valueOf(LocalDate.now()));
			}
			status = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}

	







}
