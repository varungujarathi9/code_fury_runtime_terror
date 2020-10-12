package com.hsbc.easset.bl;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 *
 * @author Saya
 * @version 1.0
 * @createdOn 04 Oct 2020
 *
 *
 */
import com.hsbc.easset.exceptions.DBConnCreationException;
import com.hsbc.easset.exceptions.InvalidEmailIdException;
import com.hsbc.easset.exceptions.InvalidTelephoneNumberException;
import com.hsbc.easset.exceptions.PasswordMismatchException;
import com.hsbc.easset.models.Asset;
import com.hsbc.easset.models.User;

public interface EAssetBL {

	boolean addUser(User user,String confirmPassword) throws DBConnCreationException,SQLIntegrityConstraintViolationException, InvalidTelephoneNumberException, InvalidEmailIdException, PasswordMismatchException;
    boolean validateLogin(User user) throws DBConnCreationException;
    boolean addAsset(Asset asset) throws DBConnCreationException;
	boolean isAdmin(String userName) throws DBConnCreationException; //to redirect to admin or burrower homepage
	boolean existsCategory(String categoryName) throws DBConnCreationException;
	boolean addCategory(String categoryName, int lendingPeriod, int lateFees, int banPeriod) throws DBConnCreationException;
	User getUserInfo(User user) throws DBConnCreationException;;
	int addImportUser(String filepath) throws DBConnCreationException;
	//int addImportUser(String filepath) throws DBConnCreationException;
	List<String> getOverdueAssets() throws DBConnCreationException;
	List<String> getCategoryList() throws DBConnCreationException;

	boolean returnAssets(String assetId, String userId);
	public List<Asset> showAvailableAssets(int userId);
	boolean borrowAssets(int assetId, int userId) throws SQLException;
	List<String> getOverdueMessages(int userId) throws DBConnCreationException;
	List<Asset> showAllAssets() throws DBConnCreationException;
}
