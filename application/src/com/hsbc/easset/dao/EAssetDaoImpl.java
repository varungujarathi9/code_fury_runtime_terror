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
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.PropertyResourceBundle;

//import com.hsbc.banking.models.Bank;
import com.hsbc.easset.helpers.DBHelper;
import com.hsbc.easset.models.Asset;
import com.hsbc.easset.models.Borrower;
import com.hsbc.easset.models.RoleType;
import com.hsbc.easset.models.User;

import jdk.nashorn.internal.ir.debug.JSONWriter;

public class EAssetDaoImpl implements EAssetDao{

	private Connection conn, conn2;
	private PreparedStatement pre, preC;
	private ResultSet resultSet, resultSetC;
	private Statement stmt;
	private boolean status;
	private ResourceBundle resourceBundle;
	private static int uniqueId;
	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private PreparedStatement pre1,pre2, pre3;
	private ResultSet resultSet1,resultSet2, resultSet3;

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
			pre.setString(1, user.getName());
			pre.setLong(2, user.getTelphoneNumber());
			pre.setString(3, user.getEmailId());
			pre.setString(4, user.getUsername());
			pre.setString(5, hashPassword(user.getPassword()));
			pre.setDate(6, null);
			pre.setString(7, user.getRole().toString());
			pre.executeUpdate();
			conn.commit();
			status=true;

		}
		catch(SQLIntegrityConstraintViolationException e)
		{
			try {
				conn.rollback();
			}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new SQLIntegrityConstraintViolationException("Email ID or Username already exists!!! Please check");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error Code"+e.getErrorCode());
			e.printStackTrace();
			try {
				conn.rollback();
			}
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new SQLException("Connection Error Occurred in DAO");
		}

		finally
		{
			try {
				conn.close();
			}
			catch (SQLException e) {
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
			pre.setString(2, hashPassword(user.getPassword()));
			resultSet=pre.executeQuery();
			resultSet.next();
			if(resultSet.getInt(1)>0)
			{
				//update last login
				try
				{
					pre=conn.prepareStatement(resourceBundle.getString("addlastlogin"));
					//					pre.setDate(1, Date.valueOf(LocalDateTime.now().format(dateTimeFormatter).toString()));
					pre.setDate(1, null);
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
					}
					catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					throw new SQLException("Connection Error Occurred");
				}
			}


		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException("Connection Error Occurred");
		}
		finally
		{
			try {
				conn.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return status;
	}
	
	//Password encryption
	private static String hashPassword(String plainTextPassword)
	{
		String encodedPassword = Base64.getEncoder().encodeToString(plainTextPassword.getBytes());
//		System.out.println("..............."+encodedPassword);
		return encodedPassword;
	}
		
	//checking for correct password while login
//	private static void checkPass(String plainPassword, String hashedPassword) {
//		if (BCrypt.checkpw(plainPassword, hashedPassword))
//			System.out.println("The password matches.");
//		else
//			System.out.println("The password does not match.");
//	}

	@Override
	public boolean addAsset(Asset asset) throws SQLException {
		// TODO Auto-generated method stub
		boolean status=false;
		try
		{
			conn=DBHelper.getConnection();
			pre=conn.prepareStatement(resourceBundle.getString("addasset"));
			//uniqueId=new Random().nextInt(10000)+1;
			//pre.setInt(1,uniqueId);
			pre.setString(1, asset.getName());
			pre.setString(2, asset.getAssetType());
			pre.setString(3, asset.getDescription());
			pre.setDate(4, Date.valueOf(LocalDate.now()));
			//pre.setBoolean(6, true);
			pre.setString(5, "1");
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
	public boolean isAdmin(String userName) throws SQLException {
		// TODO Auto-generated method stub
		boolean status=false;
		try {
			conn=DBHelper.getConnection();
			pre=conn.prepareStatement(resourceBundle.getString("roleQuery"));
			pre.setString(1, userName);
			resultSet=pre.executeQuery();
			resultSet.next();
			System.out.println(resultSet.getString(1));
			if(resultSet.getString(1).equals("ADMIN")) //if we formerly store admin role as 1 and burrower as 0 in db
			{
				status=true;
			}
			else {
				status = false;
			}


		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException("Connection Error Occurred");
		}
		finally
		{
			try {
				conn.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return status;
	}

			public boolean existsCategory(String categoryName) throws SQLException {

				boolean status=false;
				try {
					conn=DBHelper.getConnection();
					pre=conn.prepareStatement(resourceBundle.getString("categoryQuery"));
					pre.setString(1, categoryName);
					resultSet=pre.executeQuery();
					resultSet.next();
					//System.out.println("********************************************"+resultSet.getInt(1));
					if(resultSet.getInt(1)>0)
					{
						status=true;
					}


				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new SQLException("Connection Error Occurred");
				}
				finally
				{
					try {
						conn.close();
					}
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				return status;
			}

			@Override
			public boolean addCategory(String categoryName, int lendingPeriod, int lateFees, int banPeriod) throws SQLException {
				// TODO Auto-generated method stub
				boolean status=false;
				try
				{
					conn=DBHelper.getConnection();
					pre=conn.prepareStatement(resourceBundle.getString("addcategory"));
					pre.setString(1, categoryName);
					pre.setInt(2, lendingPeriod);
					pre.setInt(3, lateFees);
					pre.setInt(4, banPeriod);
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
					}
					catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					throw new SQLException("Connection Error Occurred");
				}
				finally
				{
					try {
						conn.close();
					}
					catch (SQLException e) {
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
				String password; 
				String BasicBase64format;
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
						//pre.setInt(1,UniqueID);
						pre.setString(1, name.get("Name").toString());     ///this is been hardcoded..think of how to avoid hardcoding///
						pre.setString(2, name.get("Telephone").toString());
						pre.setString(3, name.get("Email").toString());
						pre.setString(4, name.get("UserName").toString());
						password=name.get("Password").toString();
						BasicBase64format= Base64.getEncoder().encodeToString(password.getBytes()); 
						pre.setString(5, BasicBase64format);
						pre.setDate(6, null);
						pre.setString(7, name.get("Role").toString());
						pre.executeUpdate();
						//UniqueID++;
						i++;
						iterator.next();
					}
					conn.commit();
					//    System.out.println("data added");
					result=i;
				}
				catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (ParseException e) {
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
					}
					catch (SQLException e) {
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
					userObj.setUniqueId(resultSet.getInt(1));
					userObj.setName(resultSet.getString(2));
					userObj.setTelphoneNumber(resultSet.getLong(3));
					userObj.setEmailId(resultSet.getString(4));
					userObj.setUsername(resultSet.getString(5));
					userObj.setPassword(resultSet.getString(6));
					userObj.setLastLogin(null);
					userObj.setRole(RoleType.valueOf(resultSet.getString(8)));
				}
				catch (SQLException e) {
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
			public boolean returnAssets(String assetId, String userId) {
				// TODO Auto-generated method stub

				boolean status = false;
				//Asset asset = null
				try {
					conn=DBHelper.getConnection();
					pre=conn.prepareStatement(resourceBundle.getString("returnAssets"));

					//					for(String assetId: assetIdList)
					//					{

						pre.setString(1, "1");
						pre.setInt(2, Integer.parseInt(assetId));
						pre.executeUpdate();

						//					}
						pre = conn.prepareStatement(resourceBundle.getString("issuelogUpdate"));
						//					for(String assetId: assetIdList)
						//					{
							pre.setDate(1, Date.valueOf(LocalDate.now()));
							System.out.println("DAO RETURN "+assetId + " " + userId);
							pre.setInt(2, Integer.parseInt(assetId));
							pre.setInt(3, Integer.parseInt(userId));
							pre.executeUpdate();
							//					}
							status = true;
						}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					try {
						conn.close();
					}
					catch(Exception e) {
						System.out.println("DB Close error in return assets " + e.getStackTrace());
					}
				}

				return status;
			}

			@Override
			public List<String> showAssets(String userid) throws SQLException {


				// TODO Auto-generated method stub
				//return json data//
				 //assetList is an array of json strings where each string represents one json object//
				//assetList=[{},{}]
				//System.out.println("in dao layer show assets method");
			     JSONArray jsonarray = new JSONArray();
				List<String> assetList=new ArrayList<String>();
				//assetList=null;
				Asset asset=null;
				try {
					conn=DBHelper.getConnection();




			//		System.out.println("2");
					pre=conn.prepareStatement(resourceBundle.getString("showassets"));
					pre.setInt(1, Integer.parseInt(userid));
					//pre.setDate(2,Date.valueOf(customer.getDob()));
					resultSet=pre.executeQuery();
					resultSet.next();


					while(resultSet.next())
					{

					//	System.out.println(resultSet.getBoolean(6));
						  JSONObject obj = new JSONObject();
						  obj.put("Asset_ID",resultSet.getInt(1) );
						  obj.put("USER_ID",resultSet.getInt(2));
						  obj.put("ISSUE DATE",resultSet.getDate(3));
						  obj.put("EXPECTED_RETURN_DATE", resultSet.getDate(4));
						  //obj.put("ACTUAL_RETURN_DATE",resultSet.getDate(5));
						  //obj.put("ADMIN_ALERT", resultSet.getString(6));

						  // ((Object) ja).put(obj);
						  jsonarray.add(obj);

					      // assetList.add(obj);
						//asset=new Asset();
					///	assetList.add(asset);

					}


		       //JSONArray jsonArray = (JSONArray)jsonObject;
		     if (jsonarray != null) {
		         int len = jsonarray.size();
		             for (int i=0;i<len;i++){
		                  assetList.add(jsonarray.get(i).toString());
		    }
		}
				}	 catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getErrorCode());
				e.printStackTrace();
					throw new SQLException("Connection Error Occurred");
				}
				finally
				{
					conn.close();
				}

		//System.out.println("end");

				return assetList;
			}

			// @Override
			// public List<Asset> showAvailableAssets(int userId) throws SQLException {
			// 	// TODO Auto-generated method stub

			// 	PreparedStatement pre1,pre2, pre3;
			// 	ResultSet resultSet1,resultSet2, resultSet3;
			// 	String assetType;
			// 	Set<String> assetListAlreadyBurrowed = new HashSet<String>();

			// 	// TODO Auto-generated method stub
			// 	List<Asset> assetList = new ArrayList<Asset>();

			// 	LocalDate expectedReturnDate, actualReturnDate;


			// 	Asset asset = null;
			// 	boolean banStatus = false;
			// 	int assetId = 0;
			// 	int lendingPeriod = 0;
			// 	int lateFees = 0;
			// 	int banPeriod = 0;
			// 	boolean actualDateStatus = false;
			// 	boolean isIssueLogEmpty = false;
			// 	boolean isnewUser = true;
			// 	int count=0;


			// 	try {

			// 		conn = DBHelper.getConnection();
			// 		pre1=conn.prepareStatement(resourceBundle.getString("returncount"));
			// 		pre1.setInt(1, userId);
			// 		resultSet1=pre1.executeQuery();
			// 			resultSet1.next();
			// 	 count=resultSet1.getInt(1);

			// 		pre = conn.prepareStatement(resourceBundle.getString("getFromIssueLog"));
			// 		pre.setInt(1, userId);
			// 		resultSet = pre.executeQuery();


			// 		if (count>0) {
			// 			//not a new user

			// 			// logic for banning the user
			// 			while (resultSet.next()) {

			// 				assetId = resultSet.getInt(1);

			// 				expectedReturnDate = LocalDate.parse(resultSet.getDate(2).toString());


			// 				if (resultSet.getDate(3) == null) {
			// 					actualDateStatus = true;
			// 					actualReturnDate = null;
			// 				} else
			// 					actualReturnDate = LocalDate.parse(resultSet.getDate(3).toString());



			// 				if (actualDateStatus) {

			// 			return status;
			// 		}

					@Override
					public List<Asset> showAvailableAssets(int userId) throws SQLException {
						List<Asset> assetList = new ArrayList<Asset>();
						List<String> borrowedAssetTypes = new ArrayList<String>();
						Asset asset = null;
						boolean isBanned = false, actualDateStatus = false;
						LocalDate expectedReturnDate, actualReturnDate;
						int banPeriod;

						try {
							conn = DBHelper.getConnection();
							// check if user should be banned
							pre=conn.prepareStatement(resourceBundle.getString("getFromCategory"));
							pre.setInt(1,userId);
							resultSet = pre.executeQuery();
//							c.ASSET_TYPE, c.LENDING_PERIOD, c.LATE_FEES, c.BAN_PERIOD, b.EXPECTED_DATE_RETURN, b.ACTUAL_DATE_RETURN
							while(resultSet.next()) {
								expectedReturnDate = LocalDate.parse(resultSet.getDate(5).toString());
								if (resultSet.getDate(6) == null){
									actualDateStatus = false;
									actualReturnDate = null;
								}
								else {
									actualDateStatus = true;
									actualReturnDate = LocalDate.parse(resultSet.getDate(6).toString());
								}

								banPeriod = resultSet.getInt(4);
								if(actualDateStatus == false && expectedReturnDate.isBefore(LocalDate.now())) {
									isBanned = true;
									System.out.println("BANNED 1");
									break;
								}
								else if(actualDateStatus == true && expectedReturnDate.isBefore(actualReturnDate) &&actualReturnDate.plusDays(banPeriod).isAfter(LocalDate.now())) {
									isBanned = true;
									System.out.println("BANNED 2");
									break;
								}
							}

							if (!isBanned) {
								// get list of asset types already borrowed
								pre=conn.prepareStatement(resourceBundle.getString("getBorrowedAssetType"));
								pre.setInt(1,userId);
								resultSet = pre.executeQuery();
								System.out.println(resultSet.toString());
								while(resultSet.next()) {
									borrowedAssetTypes.add(resultSet.getString(1));
								}
								System.out.println(borrowedAssetTypes);

								// get available assets
								String str = String.join("','", borrowedAssetTypes);
								String sql="select * from ASSET where (IS_AVAILABLE='1' AND ASSET_TYPE NOT IN ('"+str+"'))";
								System.out.println(sql);
								Statement st=conn.createStatement();
								resultSet = st.executeQuery(sql);

								while(resultSet.next()) {

									asset = new Asset();
									asset.setAssetId(resultSet.getInt(1));
									asset.setName(resultSet.getString(2));
									asset.setAssetType(resultSet.getString(3));
									asset.setDescription(resultSet.getString(4));
									asset.setDateAdded(LocalDate.parse(resultSet.getDate(5).toString()));
									if (resultSet.getString(6).equals("1"))
									asset.setAvailable("1");
									else
									asset.setAvailable("0");

									// add all assets to assestlist
									assetList.add(asset);
								}

							}
						}
						catch(Exception e) {
							e.printStackTrace();
						}
						finally{
							try {
								conn.close();
							}
							catch(Exception e) {
								e.printStackTrace();
							}
						}
						return assetList;
					}

//					public List<Asset> showAvailableAssets1(int userId) throws SQLException {
//						// TODO Auto-generated method stub
//
//
//						String assetType;
//						Set<String> assetListAlreadyBurrowed = new HashSet<String>();
//
//						// TODO Auto-generated method stub
//						List<Asset> assetList = new ArrayList<Asset>();
//
//						LocalDate expectedReturnDate, actualReturnDate;
//
//
//						Asset asset = null;
//						boolean banStatus = false;
//						int assetId = 0;
//						int lendingPeriod = 0;
//						int lateFees = 0;
//						int banPeriod = 0;
//						boolean actualDateStatus = false;
//						boolean isIssueLogEmpty = false;
//						boolean isnewUser = true;
//						int count=0;
//
//
//						try {
//
//							conn = DBHelper.getConnection();
//							try {
//								pre1=conn.prepareStatement(resourceBundle.getString("returncount"));
//								pre1.setInt(1, userId);
//								resultSet1=pre1.executeQuery();
//								resultSet1.next();
//								count=resultSet1.getInt(1);
//
//								if (count>0) {
//									try {
//										pre = conn.prepareStatement(resourceBundle.getString("getFromIssueLog"));
//										pre.setInt(1, userId);
//										resultSet = pre.executeQuery();
//										conn.setAutoCommit(false);
//
//
//
//										//not a new user
//
//										// logic for banning the user
//										while (resultSet.next()) {
//
//											assetId = resultSet.getInt(1);
//
//											expectedReturnDate = LocalDate.parse(resultSet.getDate(2).toString());
//
//
//											if (resultSet.getDate(3) == null) {
//												actualDateStatus = true;
//												actualReturnDate = null;
//											} else
//											actualReturnDate = LocalDate.parse(resultSet.getDate(3).toString());
//
//
//
//											if (actualDateStatus) {
//
//
//												if (expectedReturnDate.isBefore(LocalDate.now())) {
//													System.out.println("Banned ....");
//													banStatus = true;
//													break;
//												}
//
//
//											}
//
//											else
//
//											{
//
//												pre2 = conn.prepareStatement(resourceBundle.getString("getAssetType"));
//												pre2.setInt(1, assetId);
//												resultSet2 = pre.executeQuery();
//
//
//												while (resultSet2.next()) {
//													assetType = resultSet2.getString(1);
//
//
//													pre3 = conn.prepareStatement(resourceBundle.getString("getFromAssetManagement"));
//													pre3.setString(1, assetType);
//													resultSet3 = pre3.executeQuery();
//
//													while (resultSet3.next()) {
//														lendingPeriod = resultSet3.getInt(1);
//														lateFees = resultSet3.getInt(2);
//														banPeriod = resultSet3.getInt(3);
//
//													}
//
//													if (actualReturnDate.plusDays(banPeriod).isAfter(LocalDate.now()) && (actualReturnDate.isAfter(expectedReturnDate))) {
//														banStatus = true;
//														break;
//													}
//
//												}
//
//												if (banStatus == true)
//												break;
//
//											}
//
//										}
//
//										// end of banning logic
//										if (banStatus)
//										return assetList; // return null asset list to signify that user is banned
//									}
//
//									catch(Exception e) {
//										e.printStackTrace();
//									}
//								}
//							}
//							catch(Exception e){
//								e.printStackTrace();
//							}
//							// ****************************************LOGIC FOR RRETURNING THE ASSETS THAT USER DOESNT POSSESS********************************************
//
//							pre = conn.prepareStatement(resourceBundle.getString("getAssetId"));
//
//							pre.setInt(1, userId);
//							resultSet = pre.executeQuery();
//
//							// we can get a list of items user might have issued
//
//							if (count>0) {
//
//								isnewUser = false;
//								while (resultSet.next()) {
//
//									assetId = resultSet.getInt(1);
//									System.out.println("aseet id user has already="+assetId);
//
//									pre2 = conn.prepareStatement(resourceBundle.getString("getAssetType"));
//									pre2.setInt(1, assetId);
//									resultSet2 = pre2.executeQuery();
//									resultSet2.next();
//									assetType = resultSet2.getString(1);
//
//									// adding the asset type the user has already burrowed
//
//									assetListAlreadyBurrowed.add(assetType);
//
//								}
//
//
//
//							}
//
//
//							pre3 = conn.prepareStatement(resourceBundle.getString("getAssets"));
//							resultSet3 = pre3.executeQuery();
//
//							while(resultSet3.next()) {
//								asset = new Asset();
//								asset.setAssetId(resultSet3.getInt(1));
//								asset.setName(resultSet3.getString(2));
//								asset.setAssetType(resultSet3.getString(3));
//								asset.setDescription(resultSet3.getString(4));
//								asset.setDateAdded(LocalDate.parse(resultSet3.getDate(5).toString()));
//								if (resultSet3.getString(6).equals("1"))
//								asset.setAvailable("1");
//								else
//								asset.setAvailable("0");
//
//								// add all assets to assestlist
//								assetList.add(asset);
//							}
//
//
//							/// remove the assettypes that user already has
//							if (isnewUser == false) {
//								if (assetList.size()>0) {
//									for (Asset assets : assetList) {
//										for(String assetType1:assetListAlreadyBurrowed) {
//											if(assets.getAssetType().contains(assetType1))
//											assetList.remove(assets);
//
//										}
//										if (assetList.size()==0) {
//											break;
//										}
//
//									}
//								}
//							}
//						}
//						catch (SQLException e) {
//							// TODO Auto-generated catch block
//							// System.out.println(e.getErrorCode());
//							e.printStackTrace();
//							throw new SQLException("Connection Error Occurred");
//						}
//						finally {
//
//							try {
//								conn.close();
//							}
//							catch (Exception e){
//								System.out.println("DB Close error in show avaliable assets " + e.getStackTrace());
//							}
//						}
//						return assetList;
//
//					}

					@Override
					public boolean borrowAssets(int assetId,int userId) throws SQLException {
						// TODO Auto-generated method stub

						String assetType=null;
						int lendingPeriod=0;
						int status=0;

						try {
							System.out.println("BORROW ASSET DAO "+ assetId + " "+ userId);
							conn = DBHelper.getConnection();

							//get asset type
							pre=conn.prepareStatement(resourceBundle.getString("getAssetType"));
							pre.setInt(1, assetId);
							resultSet=pre.executeQuery();
							resultSet.next();
							assetType=resultSet.getString(1);

							//get ending peroid from asset_mgmt
							pre=conn.prepareStatement(resourceBundle.getString("getLendingPeriod"));
							pre.setString(1, assetType);
							resultSet=pre.executeQuery();
							resultSet.next();

							lendingPeriod=resultSet.getInt(1);

							//update isseulog
							pre = conn.prepareStatement(resourceBundle.getString("updateIssueLog"));
							pre.setInt(1, assetId);
							pre.setInt(2,userId);
							pre.setDate(3, Date.valueOf(LocalDate.now()));
							pre.setDate(4, Date.valueOf(LocalDate.now().plusDays(lendingPeriod)));
							pre.setDate(5, null);
							pre.setString(6, "0");
							status=pre.executeUpdate();

							//update assets tbale
							if(status>0) {
								//set is_avb in assets=0
								pre=conn.prepareStatement(resourceBundle.getString("setAvailableStatus"));
								pre.setInt(1, assetId);
								status=pre.executeUpdate();


							}

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							// System.out.println(e.getErrorCode());
							e.printStackTrace();
							throw new SQLException("Connection Error Occurred");
						} finally {
							conn.close();
						}
						System.out.println("Returned "+ status + " from borrow Asset Dao");
						if(status>0)
							return true;
						else
							return false;
					}

					public List<String> getOverdueAssets() throws SQLException {
						// TODO Auto-generated method stub
						JSONArray jsonarray = new JSONArray();
						List<String> overdueAssetsList=new ArrayList<String>();
						try {
							conn=DBHelper.getConnection();
							stmt=conn.createStatement();
							resultSet=stmt.executeQuery(resourceBundle.getString("getOverdueAssets"));

							while(resultSet.next())
							{
								JSONObject obj = new JSONObject();
								obj.put("USER_ID",resultSet.getInt(1) );
								obj.put("ASSET_ID",resultSet.getInt(2));
								obj.put("ISSUE_DATE",resultSet.getDate(3));
								obj.put("EXPECTED_RETURN_DATE",	resultSet.getDate(4));
								obj.put("ACTUAL_RETURN_DATE",resultSet.getDate(5));
								obj.put("ADMIN_ALERT",	resultSet.getString(6));
								obj.put("NAME",	resultSet.getString(8));
								obj.put("TELEPHONENUMBER",	resultSet.getLong(9));
								obj.put("EMAILID",	resultSet.getString(10));
								obj.put("USERNAME",	resultSet.getString(11));
								obj.put("PASSWORD",	resultSet.getString(12));
								obj.put("LASTLOGIN",	resultSet.getDate(13));
								obj.put("ROLE",	resultSet.getString(14));
								obj.put("ASSET_NAME",	resultSet.getString(16));
								obj.put("ASSET_TYPE",	resultSet.getString(17));
								obj.put("ASSET_DESCRIPTION",	resultSet.getString(18));
								obj.put("DATE_ADDED",	resultSet.getDate(19));
								obj.put("IS_AVAILABLE",	resultSet.getString(20));

								jsonarray.add(obj);

							}
							if (jsonarray != null) {
								int len = jsonarray.size();
								for (int i=0;i<len;i++)
								overdueAssetsList.add(jsonarray.get(i).toString());
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							//System.out.println(e.getErrorCode());
							throw new SQLException("Connection Error Occurred");
						}
						finally
						{
							try {
								conn.close();
							}
							catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						return overdueAssetsList;
					}
					@Override
					public List<String> getCategoryList() throws SQLException {
						// TODO Auto-generated method stub
//						JSONArray jsonarray = new JSONArray();
						List<String> categoryList=new ArrayList<String>();
						try {
							conn=DBHelper.getConnection();
							stmt=conn.createStatement();
							resultSet=stmt.executeQuery(resourceBundle.getString("getCategoryList"));

//							while(resultSet.next())
//							{
//								JSONObject obj = new JSONObject();
//								obj.put("CATEGORY",resultSet.getString(1) );
//								jsonarray.add(obj);
//
//							}
							while(resultSet.next())
							{
								categoryList.add(resultSet.getString(1));
							}
//							if (jsonarray != null) {
//								int len = jsonarray.size();
//								for (int i=0;i<len;i++)
//								categoryList.add(jsonarray.get(i).toString());
//							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							//System.out.println(e.getErrorCode());
							throw new SQLException("Connection Error Occurred");
						}
						finally
						{
							try {
								conn.close();
							}
							catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						return categoryList;
					}



			@Override
			public List<String> getOverdueMessages(int userId) throws SQLException {
				// TODO Auto-generated method stub
				JSONArray jsonarray = new JSONArray();
				List<String> overdueMessagesList=new ArrayList<String>();
				try {
					conn=DBHelper.getConnection();
					pre=conn.prepareStatement(resourceBundle.getString("getOverdueMessages"));
					pre.setInt(1, userId);
					resultSet=pre.executeQuery();

					while(resultSet.next())
					{
						JSONObject obj = new JSONObject();
				        obj.put("USER_ID",resultSet.getInt(1));
				        obj.put("NAME",resultSet.getString(2));
				        obj.put("ASSET_ID",resultSet.getInt(3));
				        obj.put("ASSET_NAME",resultSet.getString(4));
				        obj.put("ISSUE_DATE",resultSet.getDate(5));
				        obj.put("EXPECTED_RETURN_DATE",resultSet.getDate(6));

				        jsonarray.add(obj);

					}
					if (jsonarray != null) {
						int len = jsonarray.size();
					    for (int i=0;i<len;i++)
					    	overdueMessagesList.add(jsonarray.get(i).toString());
					}
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
				return overdueMessagesList;
			}
			
			@Override
			public List<Borrower> showBorrowedAssets(String userid) throws SQLException {


				// TODO Auto-generated method stub
				//return json data//
				//assetList is an array of json strings where each string represents one json object//
				//assetList=[{},{}]
				//System.out.println("in dao layer show assets method");
				JSONArray jsonarray = new JSONArray();
				List<Borrower> assetList=new ArrayList<Borrower>();
				//assetList=null;
				Borrower borrower=null;
				int lateFees;
				long lateDays, lateAmount;
				try {
					conn=DBHelper.getConnection();

							//		System.out.println("2");
							pre=conn.prepareStatement(resourceBundle.getString("showBorrowedAssets"));
							pre.setInt(1, Integer.parseInt(userid));
							System.out.println(userid);
							//pre.setDate(2,Date.valueOf(customer.getDob()));
							resultSet=pre.executeQuery();
							
							//resultSet.next();
							//a.ASSET_ID, a.ASSET_NAME, a.ASSET_TYPE, a.ASSET_DESCRIPTION, b.ISSUE_DATE, b.EXPECTED_RETURN_DATE, b.
							while(resultSet.next())
							{
	
									System.out.println("ASSET NAME: "+resultSet.getString(2));

									borrower=new Borrower();
									borrower.setAssetId(resultSet.getInt(1));
									borrower.setAssetName(resultSet.getString(2));
									borrower.setAssetType(resultSet.getString(3));
									borrower.setAssetDesc(resultSet.getString(4));
									borrower.setDateOfissue(LocalDate.parse(resultSet.getDate(5).toString()));
									borrower.setExpectedReturnDate(LocalDate.parse(resultSet.getDate(6).toString()));
//									pre1=conn.prepareStatement(resourceBundle.getString("getLateFees"));
//									pre1.setString(1, borrower.getAssetType());
//									resultSet1=pre1.executeQuery();
//									resultSet1.next();
									lateFees = resultSet.getInt(7);
									if(LocalDate.parse(borrower.getExpectedReturnDate().toString()).isBefore(LocalDate.now())) {
										lateDays = ChronoUnit.DAYS.between(LocalDate.parse(borrower.getExpectedReturnDate().toString()), LocalDate.now());
										lateAmount = Math.abs(lateDays * lateFees);
									}
									else {
										lateAmount = 0;
									}
									borrower.setLateAmount(lateAmount);
									assetList.add(borrower);
								

							}

							//						while(resultSet.next())
							//						{
								//
								//						//	System.out.println(resultSet.getBoolean(6));
								//							  JSONObject obj = new JSONObject();
								//							  obj.put(Asset_ID",resultSet.getInt(1) );
								//							  obj.put("USER_ID",resultSet.getInt(2));
								//							  obj.put("ISSUE DATE",resultSet.getDate(3));
								//							  obj.put("EXPECTED_RETURN_DATE", resultSet.getDate(4));
								//							  //obj.put("ACTUAL_RETURN_DATE",resultSet.getDate(5));
								//							  //obj.put("ADMIN_ALERT", resultSet.getString(6));
								//
								//							  // ((Object) ja).put(obj);
								//							  jsonarray.add(obj);
								//
								//						      // assetList.add(obj);
								//							//asset=new Asset();
								//						///	assetList.add(asset);
								//
								//						}


								//JSONArray jsonArray = (JSONArray)jsonObject;
								//			     if (jsonarray != null) {
									//			         int len = jsonarray.size();
									//			             for (int i=0;i<len;i++){
										//			                  assetList.add(jsonarray.get(i).toString());
										//			    }
										//			}
									}	 catch (SQLException e) {
										// TODO Auto-generated catch block
										System.out.println(e.getErrorCode());
										e.printStackTrace();
										throw new SQLException("Connection Error Occurred");
									}
									finally
									{
										conn.close();
									}

									//System.out.println("end");
									System.out.println(assetList);
									return assetList;
								}

			@Override
			public List<Asset> showAllAssets() throws SQLException{
					List<Asset> assetList = new ArrayList<Asset>();
					Asset asset = null;
					try {
						conn=DBHelper.getConnection();

						//		System.out.println("2");
						pre=conn.prepareStatement(resourceBundle.getString("showAllAssets"));
						resultSet=pre.executeQuery();	
						
						while(resultSet.next())
						{
							asset = new Asset();
							asset.setAssetId(resultSet.getInt(1));
							asset.setName(resultSet.getString(2));
							asset.setAssetType(resultSet.getString(3));
							asset.setDescription(resultSet.getString(4));
							asset.setDateAdded(LocalDate.parse(resultSet.getDate(5).toString()));
							if (resultSet.getString(6).equals("1"))
								asset.setAvailable("1");
							else
								asset.setAvailable("0");
							
							assetList.add(asset);
						}
					}
					catch (SQLException e){
						e.printStackTrace();
						throw new SQLException(e.getMessage());
					}
					return assetList;
			}

}
