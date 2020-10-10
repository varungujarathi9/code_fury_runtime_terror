package com.hsbc.easset.dao;


import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


import com.hsbc.easset.models.Asset;
import com.hsbc.easset.models.User;

public interface EAssetDao {
	
	boolean addUser(User user) throws SQLException,SQLIntegrityConstraintViolationException;
	boolean validateLogin(User user) throws SQLException;
	boolean addAsset(Asset asset) throws SQLException;
	boolean isAdmin(String userName) throws SQLException; //to redirect to admin or burrower homepage
	List<Asset> showAvailableAssets() throws   SQLException;
	//List<Asset> searchAvailableAssets(Asset asset);
	//boolean issueAvailableAssets(List<Asset> assetList);
	boolean returnAssets(List<String> assetList);
	boolean existsCategory(String categoryName) throws SQLException;
	boolean addCategory(String categoryName,int lendingPeriod,int lateFees) throws  SQLException;
	User getUserInfo(User user) throws SQLException;
	int addImportUser(String filepath) throws SQLException;
	List<String> showAssets(int userid) throws   SQLException;
	public List<Asset> showAvailableAssets(int userId) throws SQLException;
	List<String> getOverdueAssets() throws SQLException;
}
