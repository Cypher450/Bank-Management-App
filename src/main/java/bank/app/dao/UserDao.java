package bank.app.dao;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialException;

import bank.app.entities.AccountType;
import bank.app.entities.Branch;
import bank.app.entities.Customer;
import bank.app.entities.Roles;
import bank.app.entities.User;

public interface UserDao {

	int insertUser(User user) throws SQLException, IOException;

	List<Roles> fetchAllRoles() throws SQLException, IOException;

	Roles fetchRoleById(int roleId) throws SQLException, IOException;

	List<Branch> fetchAllBranch() throws SQLException, IOException;

	Branch fetchBranchById(int branchId) throws SQLException, IOException;

	Customer fetchCustomerById(int customerId) throws SQLException, IOException;

	List<AccountType> fetchAllAccountTypes() throws SQLException, IOException;

	List<User> fetchAllDetails(String username) throws SQLException, IOException;

	void insertEmployee(int userId, int branchId);

	void insertCustomer(int userId, int branchId);

	void insertBankManager(int userId, int branchId);

	Map<String, Object> fetchPwds(String username);

	User modifyUser(User user) throws SerialException, IOException, SQLException;

	User updatePassword(String HashPassword, User user) throws SerialException, IOException, SQLException;

	List<User> getCustomersByBranch(int branchId) throws SerialException, IOException, SQLException;

	User getUserById(int userId) throws SerialException, IOException, SQLException;

	void updateCustomer(User user) throws SerialException, IOException, SQLException;

	void softDeleteCustomer(int userId) throws SerialException, IOException, SQLException;
}
