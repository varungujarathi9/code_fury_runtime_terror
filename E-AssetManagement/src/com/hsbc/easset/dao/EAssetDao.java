package com.hsbc.easset.dao;

import com.hsbc.easset.models.User;

public interface EAssetDao {
	boolean addUser(User user);
    boolean validateLogin(User user);
}
