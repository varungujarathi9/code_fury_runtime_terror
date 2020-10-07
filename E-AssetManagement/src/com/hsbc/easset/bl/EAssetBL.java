package com.hsbc.easset.bl;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author Sayan
 * @version 1.0
 * @createdOn 04 Oct 2020
 *
 *
 */
import com.hsbc.easset.exceptions.DBConnCreationException;
import com.hsbc.easset.models.Asset;
import com.hsbc.easset.models.User;

public interface EAssetBL {

	boolean addUser(User user) throws DBConnCreationException,SQLIntegrityConstraintViolationException;
    boolean validateLogin(User user) throws DBConnCreationException;
    boolean addAsset(Asset asset) throws DBConnCreationException;
	boolean isAdmin(User user) throws DBConnCreationException; //to redirect to admin or burrower homepage
	boolean existsCategory(String categoryName) throws DBConnCreationException;
	boolean addCategory(String categoryName, int lendingPeriod, int lateFees) throws DBConnCreationException;
	int addImportUser(String filepath) throws DBConnCreationException;
	User getUserInfo(User user);
}
