package com.hsbc.easset.bl;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.hsbc.easset.dao.EAssetDao;
import com.hsbc.easset.dao.EAssetDaoImpl;
import com.hsbc.easset.exceptions.DBConnCreationException;
import com.hsbc.easset.exceptions.InvalidEmailIdException;
import com.hsbc.easset.exceptions.InvalidTelephoneNumberException;
import com.hsbc.easset.exceptions.PasswordMismatchException;
import com.hsbc.easset.models.Asset;
import com.hsbc.easset.models.User;

public class EAssetBLImpl implements EAssetBL{
	private EAssetDao eAssetDao;

	public EAssetBLImpl()
	{
		eAssetDao=new EAssetDaoImpl();
		System.out.println("In easset BL constructor");
	}

	//Telephone Number,EmailId and Password validations
		private boolean validateTelephoneNumber(long telephoneNumber)
		{
			return Pattern.matches("\\d{10}", String.valueOf(telephoneNumber));
		}
		private boolean validateEmailId(String emailId)
		{
			return Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", emailId);
		}
		private boolean validatePassword(String password,String confirmPassword)
		{
			return (password==confirmPassword);
		}
		@Override
		public boolean addUser(User user,String confirmPassword) throws DBConnCreationException, SQLIntegrityConstraintViolationException, InvalidTelephoneNumberException,InvalidEmailIdException,PasswordMismatchException {
			// TODO Auto-generated method stub
			boolean status = false;
//			if(!validateTelephoneNumber(user.getTelphoneNumber()))
//				throw new InvalidTelephoneNumberException("Telephone Number must be in ten digits.....");
//			if(!validateEmailId(user.getEmailId()))
//				throw new InvalidEmailIdException("Invalid Email Id.....");
//			if(!validatePassword(user.getPassword(),confirmPassword))
//				throw new PasswordMismatchException("Passwords do not match.....");
			try {
				status = eAssetDao.addUser(user);
			} 
			catch(SQLIntegrityConstraintViolationException e)
			{
				
				throw new SQLIntegrityConstraintViolationException("Email Id or Username already exists.......");
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBConnCreationException("Connection Error Occurred");
			}
			return status;
		}

	@Override
	public boolean validateLogin(User user) throws DBConnCreationException {
		// TODO Auto-generated method stub
		boolean status=false;
		try {
			status=eAssetDao.validateLogin(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBConnCreationException("Connection Error Occurred");
		}
		return status;
	}

	@Override
	public boolean addAsset(Asset asset) throws DBConnCreationException {
		// TODO Auto-generated method stub
		boolean status=false;
		try {
			System.out.println("Calling E Asset Dao");
			eAssetDao.addAsset(asset);
			System.out.println("Easset Dao returned");
			System.out.println("Added Asset Dao");

			status=true;
		} catch (SQLException e) {//SQLEXCEPTION
			// TODO Auto-generated catch block
			throw new DBConnCreationException("Connection Error Occurred");
		}
		return status;
	}

	@Override
	public boolean isAdmin(String userName) throws DBConnCreationException {
		// TODO Auto-generated method stub
		boolean status=false;
		try {
			status = eAssetDao.isAdmin(userName);
		} catch (SQLException e) {
			throw new DBConnCreationException("Connection Error Occurred");
		}
		return status;
	}

	@Override
	public boolean existsCategory(String categoryName) throws DBConnCreationException {
		// TODO Auto-generated method stub
		boolean status=false;
		try {
			status = eAssetDao.existsCategory(categoryName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBConnCreationException("Connection Error Occurred");
		}
		return status;
	}

	@Override
	public boolean addCategory(String categoryName, int lendingPeriod, int lateFees, int banPeriod) throws DBConnCreationException {
		// TODO Auto-generated method stub
		boolean status=false;
		try {
			status = eAssetDao.addCategory(categoryName, lendingPeriod, lateFees, banPeriod);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBConnCreationException("Connection Error Occurred");
		}
		return status;
	}

	@Override
	public User getUserInfo(User user) throws DBConnCreationException {
		// TODO Auto-generated method stub
		User userObj=null;
		try {
			userObj=eAssetDao.getUserInfo(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBConnCreationException("Connection Error Occurred");
		}
		return userObj;

	}

	@Override
	public int addImportUser(String filepath) throws DBConnCreationException {
		// TODO Auto-generated method stub
		int result =0;
		try {
			result = eAssetDao.addImportUser(filepath);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBConnCreationException("Connection Error Occurred");
		}

		return result;
	}

	@Override
	public List<Asset> showAvailableAssets(int userId) {
		// TODO Auto-generated method stub
		List<Asset> assetList=new ArrayList<>();
		try {
			assetList=eAssetDao.showAvailableAssets(userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return assetList;
	}

	@Override
	public boolean borrowAssets(int assetId, int userId) throws SQLException  {
		// TODO Auto-generated method stub
		boolean status=false;
		try {
			status=eAssetDao.borrowAssets(assetId, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException("Connection Error Occurred in BL");
		}
		return status;


	}

	@Override
			public List<String> getCategoryList() throws DBConnCreationException {
				// TODO Auto-generated method stub
				List<String> categoryList=null;
				try {
					categoryList=eAssetDao.getCategoryList();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new DBConnCreationException("Connection Error Occurred");
				}
				return categoryList;
	}

	@Override
	public List<String> getOverdueAssets() throws DBConnCreationException {
		// TODO Auto-generated method stub
		List<String> overdueAssetsList=null;
		try {
			overdueAssetsList=eAssetDao.getOverdueAssets();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBConnCreationException("Connection Error Occurred");
		}
		return overdueAssetsList;
	}
	
	@Override
	public boolean returnAssets(String assetId, String userId) {
		// TODO Auto-generated method stub
		boolean status=false;
		status = eAssetDao.returnAssets(assetId, userId);
		return false;
	}
	@Override
		public List<String> getOverdueMessages(int userId) throws DBConnCreationException {
			// TODO Auto-generated method stub
			List<String> overdueMessagesList=null;
			try {
				overdueMessagesList=eAssetDao.getOverdueMessages(userId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DBConnCreationException("Connection Error Occurred");
			}
			return overdueMessagesList;
	}

	@Override
	public List<Asset> showAllAssets() throws DBConnCreationException {
		// TODO Auto-generated method stub
		List<Asset> assetList=null;
		try {
			assetList=eAssetDao.showAllAssets();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBConnCreationException("Connection Error Occurred");
		}
		return assetList;
	}


}
