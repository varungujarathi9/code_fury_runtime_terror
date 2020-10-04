package com.hsbc.easset.dao;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.easset.exceptions.DBConnCreationException;

import com.hsbc.easset.models.Asset;
import com.hsbc.easset.models.User;

public interface EAssetDao {
	boolean addUser(User user) throws DBConnCreationException;
    boolean validateLogin(User user) throws DBConnCreationException;
    boolean addAsset(Asset asset) throws DBConnCreationException;
	boolean isAdmin(User user) throws DBConnCreationException; //to redirect to admin or burrower homepage
	
	List<Asset> showAvailableAssets() throws   SQLException;
	//List<Asset> searchAvailableAssets(Asset asset);
	//boolean issueAvailableAssets(List<Asset> assetList);
	//boolean returnAssets(List<Asset> assetList);

	boolean existsCategory(String categoryName) throws SQLException;
	boolean addCategory(String categoryName,int lendingPeriod,int lateFees) throws  SQLException;
}
