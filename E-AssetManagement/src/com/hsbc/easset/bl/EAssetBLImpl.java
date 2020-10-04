package com.hsbc.easset.bl;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.hsbc.easset.dao.EAssetDao;
import com.hsbc.easset.dao.EAssetDaoImpl;
import com.hsbc.easset.exceptions.DBConnCreationException;
import com.hsbc.easset.models.Asset;
import com.hsbc.easset.models.User;

public class EAssetBLImpl implements EAssetBL{
	private EAssetDao eAssetDao;
	
	public EAssetBLImpl()
	{
		eAssetDao=new EAssetDaoImpl();
	}

	@Override
	public boolean addUser(User user) throws DBConnCreationException, SQLIntegrityConstraintViolationException{
		// TODO Auto-generated method stub
			boolean status=false;
			try {
				eAssetDao.addUser(user);
				status=true;
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
			eAssetDao.addAsset(asset);
			status=true;
		} catch (SQLException e) {
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
