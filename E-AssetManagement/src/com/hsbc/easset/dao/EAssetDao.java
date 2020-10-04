package com.hsbc.easset.dao;

import java.sql.SQLException;

import com.hsbc.easset.models.Asset;
import com.hsbc.easset.models.User;

public interface EAssetDao {
	boolean addUser(User user) throws SQLException;
    boolean validateLogin(User user) throws SQLException;
    boolean addAsset(Asset asset) throws SQLException;
	boolean isAdmin(User user) throws SQLException; //to redirect to admin or burrower homepage
	boolean existsCategory(String categoryName) throws SQLException;
	boolean addCategory(String categoryName,int lendingPeriod,int lateFees) throws SQLException;
}
