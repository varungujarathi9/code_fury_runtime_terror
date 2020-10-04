package com.hsbc.easset.dao;

import com.hsbc.easset.exceptions.DBConnCreationException;
import com.hsbc.easset.models.Asset;
import com.hsbc.easset.models.User;

public interface EAssetDao {
	boolean addUser(User user);
    boolean validateLogin(User user) throws DBConnCreationException;
    boolean addAsset(Asset asset) throws DBConnCreationException;
	boolean isAdmin(User user) throws DBConnCreationException; //to redirect to admin or burrower homepage
}
