package bank.app.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

		return jdbcTemplate.query(query, new ProfileDetailsRowMapper(), username);
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

	private User getUserById(int userId) {

		String query = "SELECT * FROM user WHERE user_id = ?";

		return jdbcTemplate.queryForObject(query, new ProfileDetailsRowMapper(), userId);
	}

	@Override
	public User updatePassword(String HashPassword, User user) throws SerialException, IOException, SQLException {

		String query = "UPDATE user SET hashed_password = ? WHERE user_id = ?";
		System.out.println("hash : " + HashPassword);

		jdbcTemplate.update(query, HashPassword, user.getUserId());

		return getUserById(user.getUserId());
	}

}
