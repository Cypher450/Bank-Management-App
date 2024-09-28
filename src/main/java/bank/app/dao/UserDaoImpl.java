package bank.app.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import bank.app.entities.Branch;
import bank.app.entities.Roles;
import bank.app.entities.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertUser(User user) throws SQLException, IOException {
		
		System.out.println("impl called");
		
		//`user_id`, `username`, `first_name`, `last_name`, `email`, `phone`, `role_id`, `dob`, `address`, `status`, `salt_password`, `hashed_password`

		String query = "INSERT INTO user (`username`, `first_name`, `last_name`, `email`, `phone`,role_id, `dob`, `address`, `salt_password`, `hashed_password`) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";

		return jdbcTemplate.update(query, user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(),user.getRoleId(),user.getDateOfBirth(),user.getAddress(),user.getPasswordSalt(),user.getPasswordHashed());
	}

	@Override
	public List<Roles> fetchAllRoles() {

		String query = "SELECT * FROM roles ORDER BY role_id";
		
		return jdbcTemplate.query(query, new BankRolesRowMapper());
	}

	@Override
	public List<Branch> fetchAllBranch() {

		String query = "SELECT * FROM branch ORDER BY branch_id";
		
		return jdbcTemplate.query(query, new BranchRowMapper());
	}

	

}
