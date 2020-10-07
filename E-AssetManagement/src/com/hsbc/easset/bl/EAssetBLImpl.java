package com.hsbc.easset.bl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.hsbc.easset.dao.EAssetDao;
import com.hsbc.easset.dao.EAssetDaoImpl;
import com.hsbc.easset.exceptions.DBConnCreationException;
import com.hsbc.easset.models.Asset;
import com.hsbc.easset.models.User;

public class EAssetBLImpl implements EAssetBL {
	private EAssetDao eAssetDao;

	public EAssetBLImpl() {
		eAssetDao = new EAssetDaoImpl();
		//System.out.println("0");
	}

	@Override
	public boolean addUser(User user) throws DBConnCreationException, SQLIntegrityConstraintViolationException {
		// TODO Auto-generated method stub
		boolean status = false;
		try {
			eAssetDao.addUser(user);
			status = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBConnCreationException("Connection Error Occurred");
		}
		return status;
	}

	@Override
	public boolean validateLogin(User user) throws DBConnCreationException {
		// TODO Auto-generated method stub
		boolean status = false;
		try {
			eAssetDao.validateLogin(user);
			status = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBConnCreationException("Connection Error Occurred");
		}
		return status;
	}

	@Override
	public boolean addAsset(Asset asset) throws DBConnCreationException {
		// TODO Auto-generated method stub
		boolean status = false;
		try {
			eAssetDao.addAsset(asset);
			status = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBConnCreationException("Connection Error Occurred");
		}
		return status;
	}

	@Override
	public boolean isAdmin(User user) throws DBConnCreationException {
		// TODO Auto-generated method stub
		boolean status = false;
		try {
			eAssetDao.isAdmin(user);
			status = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBConnCreationException("Connection Error Occurred");
		}
		return status;
	}

	@Override
	public boolean existsCategory(String categoryName) throws DBConnCreationException {
		// TODO Auto-generated method stub
		boolean status = false;
		try {
			eAssetDao.existsCategory(categoryName);
			status = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBConnCreationException("Connection Error Occurred");
		}
		return status;
	}

	@Override
	public boolean addCategory(String categoryName, int lendingPeriod, int lateFees) throws DBConnCreationException {
		// TODO Auto-generated method stub
		boolean status = false;
		try {
			eAssetDao.addCategory(categoryName, lendingPeriod, lateFees);
			status = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBConnCreationException("Connection Error Occurred");
		}
		return status;
	}

	@Override
	public int addImportUser(String filepath) throws DBConnCreationException  {
		// TODO Auto-generated method stub
		int count=0;
		//System.out.println("entered BL");
		//System.out.println(filepath);
		JSONParser parser = new JSONParser();
		Object obj;
		int flag=0;
		int valid=0;
		int res;
		//validations done
		
		try {
			obj = parser.parse(new FileReader(filepath));
			JSONArray jsonObject = (JSONArray)obj;
			//JSONArray jsonObject = (JSONArray)obj;
			int i =0;
		     //to count how many users were successfully imported//
			  int UniqueID =1000;             //unique id///
			Iterator iterator = ((List) jsonObject).iterator();
	         while (iterator.hasNext()) {
	      
	        	 JSONObject name = (JSONObject)jsonObject.get(i);
	        	 String phoneno= name.get("Telephone").toString();
	        	 if (!(phoneno.matches("\\d{10}"))) 
	        	 {
	        		 valid=valid+1;            //phoneno validations
	        	 }
	        	 
	        	 String email=name.get("Email").toString();
	        	 String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
	             //Matching the given phone number with regular expression
	        	 if(!(email.matches(regex)))
	        	 {
	        		 flag=flag+1;
	        	 }
	          
	        	 i++;
	        	 iterator.next();
	         }
			 if(valid==0 && flag==0)        ///if both validations are passed then only it will call dao
			 {
				//System.out.println("calling dao");
			     count=eAssetDao.addImportUser(filepath);
			 }
			 else
			 {
				 //System.out.println("phone number invalid");
				 count=0;
			 }
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	catch (SQLException e) {
			// TODO Auto-generated catch block
		throw  new DBConnCreationException("Connection Error Occurred");
		}
	         return count;
	
		
	         
	         //obj = parser.parse(new FileReader(filepath));
				
				//JSONArray jsonObject = (JSONArray)obj;
			/*
			 * int y =0;
			 * 
			 * //to count how many users were successfully imported// //int uniqueId =70;
			 * //unique id/// // Iterator iterator = ((List) jsonObject).iterator(); while
			 * (iterator.hasNext()) { //all fields have to be mandatory validations
			 * System.out.println("4"); JSONObject name = (JSONObject)jsonObject.get(y);
			 * String names=name.get("Name").toString(); String Role=
			 * name.get("Role").toString(); String phoneno=
			 * name.get("Telephone").toString(); String Email=name.get("Email").toString();
			 * String Username=name.get("UserName").toString(); String
			 * Password=name.get("Password").toString(); if(names==null) { valid++; ///all
			 * fields have to be mandatory System.out.println("5");
			 * System.out.println("some error"); } else if(Role==null) { valid++;
			 * System.out.println("some error"); }
			 * 
			 * else if(phoneno==null) { valid++; System.out.println("some error"); } else
			 * if(Email==null) { valid++; System.out.println("some error"); } else
			 * if(Username==null) { valid++; System.out.println("some error"); } else
			 * if(Password==null) { valid++; System.out.println("some error"); } y++; //if
			 * valid>0 means your file has null fields...all fields have to be mandatory }
			 */
	         
	         
/*
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			throw new DBConnCreationException("Connection Error Occurred");
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			
			throw new DBConnCreationException("Connection Error Occurred");
					} catch (ParseException e1) {
			// TODO Auto-generated catch block
					
						throw new DBConnCreationException("Connection Error Occurred");
		}*/
		
		//all fields mandatory validations//
		
	
	
		
		
		
	}

}
