package com.hsbc.easset.dao;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.easset.exceptions.DBConnCreationException;

import com.hsbc.easset.models.Asset;
import com.hsbc.easset.models.User;

public interface EAssetDao {
<<<<<<< HEAD
	boolean addUser(User user) throws DBConnCreationException;
    boolean validateLogin(User user) throws DBConnCreationException;
    boolean addAsset(Asset asset) throws DBConnCreationException;
	boolean isAdmin(User user) throws DBConnCreationException; //to redirect to admin or burrower homepage
=======
	boolean addUser(User user) throws SQLException;
    boolean validateLogin(User user) throws SQLException;
    boolean addAsset(Asset asset) throws SQLException;
	boolean isAdmin(User user) throws SQLException; //to redirect to admin or burrower homepage
>>>>>>> 03822a7729756892a6fc809a99e05e467af72582
	
	List<Asset> showAvailableAssets() throws   SQLException;
	//List<Asset> searchAvailableAssets(Asset asset);
	//boolean issueAvailableAssets(List<Asset> assetList);
	//boolean returnAssets(List<Asset> assetList);

	boolean existsCategory(String categoryName) throws SQLException;
	boolean addCategory(String categoryName,int lendingPeriod,int lateFees) throws  SQLException;
}
