package com.hsbc.easset.dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


import com.hsbc.easset.models.Asset;
import com.hsbc.easset.models.Borrower;
import com.hsbc.easset.models.User;

public interface EAssetDao {

	boolean addUser(User user) throws SQLException,SQLIntegrityConstraintViolationException;
	boolean validateLogin(User user) throws SQLException;
	boolean addAsset(Asset asset) throws SQLException;
	boolean isAdmin(String userName) throws SQLException; //to redirect to admin or burrower homepage
	boolean existsCategory(String categoryName) throws SQLException;
	boolean addCategory(String categoryName,int lendingPeriod,int lateFees, int banPeriod) throws  SQLException;
	User getUserInfo(User user) throws SQLException;
	int addImportUser(String filepath) throws SQLException;
	public List<Asset> showAvailableAssets(int userId) throws SQLException;
	boolean borrowAssets(int assetId, int userId) throws SQLException;
	List<String> getOverdueAssets() throws SQLException;
	List<String> getCategoryList() throws SQLException;
	List<Borrower> showBorrowedAssets(String userid) throws SQLException;
	boolean returnAssets(String assetId, String userId);
	List<String> getOverdueMessages(int userId) throws SQLException;
	List<String> showAssets(String userid) throws SQLException;
	List<Asset> showAllAssets() throws SQLException;
}
