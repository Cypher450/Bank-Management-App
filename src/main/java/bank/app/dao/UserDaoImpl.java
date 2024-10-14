package bank.app.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import bank.app.entities.AccountType;
import bank.app.entities.Branch;
import bank.app.entities.Customer;
import bank.app.entities.Roles;
import bank.app.entities.User;
import jakarta.transaction.Transactional;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public int insertUser(User user) throws SQLException, IOException {

		System.out.println("impl called");

		// `first_name`, `last_name`,`username`,`salt_password`, `hashed_password`,
		// `email`, `dob`, `phone`,
		// `role_id`, `address`, `status`,
		System.out.println(user);

		String sql = "INSERT INTO user (first_name, last_name, username, salt_password, hashed_password, email, dob, phone, role_id, address, approval_status) "
				+ "VALUES (:firstName, :lastName, :username, :saltPassword, :hashedPassword, :email, :dateOfBirth, :phone, :roleId, :address, :approvalStatus)";

		Map<String, Object> params = new HashMap();
		params.put("firstName", user.getFirstName());
		params.put("lastName", user.getLastName());
		params.put("username", user.getUsername());
		params.put("saltPassword", user.getPasswordSalt());
		params.put("hashedPassword", user.getPasswordHashed());
		params.put("email", user.getEmail());
		params.put("dateOfBirth", user.getDateOfBirth());
		params.put("phone", user.getPhone());
		params.put("roleId", user.getRoleId());
		params.put("address", user.getAddress());
		params.put("approvalStatus", user.getApprovalStatus());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(params), keyHolder,
				new String[] { "user_id" });

		return keyHolder.getKey().intValue();

	}

	@Override
	public List<Roles> fetchAllRoles() {

		String query = "SELECT * FROM roles WHERE role_id > 1 ORDER BY role_id";

		return jdbcTemplate.query(query, new BankRolesRowMapper());
	}

	@Override
	public Roles fetchRoleById(int roleId) throws SQLException, IOException {
		String query = "SELECT * FROM roles WHERE role_id = ?";

		return jdbcTemplate.queryForObject(query, new BankRolesRowMapper(), roleId);
	}

	@Override
	public List<Branch> fetchAllBranch() {

		String query = "SELECT * FROM branch ORDER BY branch_id";

		return jdbcTemplate.query(query, new BranchRowMapper());
	}

	@Override
	public Branch fetchBranchById(int branchId) throws SQLException, IOException {
		String query = "SELECT * FROM branch WHERE branch_id=?";

		return jdbcTemplate.queryForObject(query, new BranchRowMapper(), branchId);
	}

	@Override
	public Customer fetchCustomerById(int customerId) throws SQLException, IOException {
		String query = "SELECT * FROM customer WHERE customer_id=?";

		return jdbcTemplate.queryForObject(query, new CustomerRowMapper(), customerId);
	}

	@Override
	public List<AccountType> fetchAllAccountTypes() throws SQLException, IOException {
		String query = "SELECT * FROM account_type ORDER BY account_type_id";

		return jdbcTemplate.query(query, new AccountTypeRowMapper());
	}

	@Override
	public List<User> fetchAllDetails(String username) throws SQLException, IOException {

		String query = "SELECT * FROM user WHERE username = ?";

		return jdbcTemplate.query(query, new UserRowMapper(), username);
	}

	@Override
	public void insertEmployee(int userId, int branchId) {
		String sql = "INSERT INTO bank_employee (be_id, branch_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, userId, branchId);

	}

	@Override
	public void insertCustomer(int userId, int branchId) {
		String sql = "INSERT INTO customer (customer_id, branch_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, userId, branchId);

	}

	@Override
	public void insertBankManager(int userId, int branchId) {
		String sql = "INSERT INTO bank_manager (bm_id, branch_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, userId, branchId);

	}

	@Override
	public Map<String, Object> fetchPwds(String username) {
		String sql = "SELECT salt_password, hashed_password, role_id, approval_status FROM user WHERE username = ?";
		return jdbcTemplate.queryForMap(sql, username);
	}

	@Override
	public User modifyUser(User user) throws SerialException, IOException, SQLException {
		System.out.println("Use Id : " + user.getUserId());

		String query = "UPDATE user SET first_name = ?, last_name = ?,email = ?, address = ? ,dob = ? WHERE user_id = ?";

		jdbcTemplate.update(query, user.getFirstName(), user.getLastName(), user.getEmail(), user.getAddress(),
				user.getDateOfBirth(), user.getUserId());

		System.out.println("updated : " + user);

		return getUserById(user.getUserId());
	}

	@SuppressWarnings("deprecation")
	@Override
	public User getUserById(int userId) {
		String sql = "SELECT * FROM user WHERE user_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { userId }, new UserRowMapper());
	}

	@Override
	public User updatePassword(String HashPassword, User user) throws SerialException, IOException, SQLException {

		String query = "UPDATE user SET hashed_password = ? WHERE user_id = ?";
		System.out.println("hash : " + HashPassword);

		jdbcTemplate.update(query, HashPassword, user.getUserId());

		return getUserById(user.getUserId());
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<User> getCustomersByBranch(int branchId) throws SerialException, IOException, SQLException {
		String sql = "SELECT u.* FROM user u " + "INNER JOIN customer c ON u.user_id = c.customer_id "
				+ "WHERE c.branch_id = ? AND u.role_id = 4 AND u.approval_status = 'approved' AND u.active_status = 'true'";
		return jdbcTemplate.query(sql, new Object[] { branchId }, new UserRowMapper());
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<User> getCustomersApprovalList(int branchId) throws SerialException, IOException, SQLException {
		String sql = "SELECT u.* FROM user u " + "INNER JOIN customer c ON u.user_id = c.customer_id "
				+ "WHERE c.branch_id = ? AND u.role_id = 4 AND u.approval_status = 'pending' AND u.active_status = 'false'";
		return jdbcTemplate.query(sql, new Object[] { branchId }, new UserRowMapper());
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<User> getCustomerPendingAccount(int branchId) throws SerialException, IOException, SQLException {
		String sql = "SELECT u.* FROM user u INNER JOIN customer c ON u.user_id = c.customer_id WHERE c.branch_id = ? AND u.role_id = 4 AND u.approval_status = 'approved' AND u.active_status = 'false'";
		return jdbcTemplate.query(sql, new Object[] { branchId }, new UserRowMapper());
	}

	@Override
	public void updateCustomer(User user) throws SerialException, IOException, SQLException {
		// `first_name`, `last_name`, `email`, `phone`, `address`, `dob`
		String sql = "UPDATE user SET first_name = ?, last_name = ?, email = ?, phone = ?, address = ?, dob = ? WHERE user_id = ?";
		jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(),
				user.getAddress(), user.getDateOfBirth(), user.getUserId());
	}

	@Override
	public void updateBankManager(User user) throws SerialException, IOException, SQLException {
		// `first_name`, `last_name`, `email`, `phone`, `address`, `dob`
		String sql = "UPDATE user SET first_name = ?, last_name = ?, email = ?, phone = ?, address = ?, dob = ? WHERE user_id = ?";
		jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(),
				user.getAddress(), user.getDateOfBirth(), user.getUserId());
	}

	@Transactional
	@Override
	public void softDeleteCustomer(int userId) throws SerialException, IOException, SQLException {
		String sqlDeactivateUser = "UPDATE user SET active_status='false' WHERE user_id = ?";
		String sqlDeleteAccounts = "DELETE FROM account WHERE customer_id = ?";

		jdbcTemplate.update(sqlDeactivateUser, userId);
		jdbcTemplate.update(sqlDeleteAccounts, userId);
	}

	@Override
	public void softDeleteEmployee(int userId) throws SerialException, IOException, SQLException {
		String sql = "UPDATE user SET active_status='false' WHERE user_id = ?";
		jdbcTemplate.update(sql, userId);
	}

	@Override
	public void softDeleteBankManager(int userId) throws SerialException, IOException, SQLException {
		String sql = "UPDATE user SET active_status='false' WHERE user_id = ?";
		jdbcTemplate.update(sql, userId);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<User> getEmployeesByBranch(int branchId) throws SerialException, IOException, SQLException {
		String sql = "SELECT u.* FROM user u " + "INNER JOIN bank_employee be ON u.user_id = be.be_id "
				+ "WHERE be.branch_id = ? AND u.role_id = 3 AND u.approval_status = 'approved' AND u.active_status = 'true'";
		return jdbcTemplate.query(sql, new Object[] { branchId }, new UserRowMapper());
	}

	@Override
	public void updateEmployee(User user) throws SerialException, IOException, SQLException {
		// `first_name`, `last_name`, `email`, `phone`, `address`, `dob`
		String sql = "UPDATE user SET first_name = ?, last_name = ?, email = ?, phone = ?, address = ?, dob = ? WHERE user_id = ?";
		jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(),
				user.getAddress(), user.getDateOfBirth(), user.getUserId());
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<User> getBranchManagersForRegion(List<Branch> branches)
			throws SerialException, IOException, SQLException {
		if (branches == null || branches.isEmpty()) {
			return new ArrayList<>();
		}

		// Extract branch IDs from the List<Branch>
		List<Integer> branchIds = branches.stream().map(Branch::getBranchId).collect(Collectors.toList());

		// Create a comma-separated string of placeholders for the IN clause
		String placeholders = String.join(",", Collections.nCopies(branchIds.size(), "?"));

		String sql = "SELECT u.* FROM user u INNER JOIN bank_manager bm ON u.user_id = bm.bm_id WHERE bm.branch_id IN ("
				+ placeholders + ") AND active_status='true'";

		// Convert the List<Integer> to an array of Objects for the query
		Object[] params = branchIds.toArray();

		return jdbcTemplate.query(sql, params, new UserRowMapper());
	}

	public void changeApprovalStatus(int userId) {

		System.out.println("User approval id : " + userId);

		String query = "UPDATE user SET approval_status = 'approved' WHERE user_id = ?;";
		jdbcTemplate.update(query, userId);
	}

}
