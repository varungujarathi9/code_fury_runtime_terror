package com.hsbc.easset.bl;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
			if(!validateTelephoneNumber(user.getTelphoneNumber()))
				throw new InvalidTelephoneNumberException("Telephone Number must be in ten digits.....");
			if(!validateEmailId(user.getEmailId()))
				throw new InvalidEmailIdException("Invalid Email Id.....");
			if(!validatePassword(user.getPassword(),confirmPassword))
				throw new PasswordMismatchException("Passwords do not match.....");
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
		boolean status=false;
		try {
			eAssetDao.validateLogin(user);
			status=true;
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
	public boolean isAdmin(User user) throws DBConnCreationException {
		// TODO Auto-generated method stub
		boolean status=false;
		try {
			eAssetDao.isAdmin(user);
			status=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBConnCreationException("Connection Error Occurred");
		}
		return status;
	}

	@Override
	public boolean existsCategory(String categoryName) throws DBConnCreationException {
		// TODO Auto-generated method stub
		boolean status=false;
		try {
			eAssetDao.existsCategory(categoryName);
			status=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBConnCreationException("Connection Error Occurred");
		}
		return status;
	}

	@Override
	public boolean addCategory(String categoryName, int lendingPeriod, int lateFees)
			throws DBConnCreationException {
		// TODO Auto-generated method stub
		boolean status=false;
		try {
			eAssetDao.addCategory(categoryName, lendingPeriod, lateFees);
			status=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DBConnCreationException("Connection Error Occurred");
		}
		return status;
	}

}
