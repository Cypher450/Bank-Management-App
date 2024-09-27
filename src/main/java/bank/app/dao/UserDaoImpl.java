package bank.app.dao;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
		
		//`username`, `first_name`, `last_name`, `email`, `phone`, `dob`, `address`

		String query = "INSERT INTO user (`username`, `first_name`, `last_name`, email) "
				+ "VALUES (?,?,?,?)";

		return jdbcTemplate.update(query, user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail());
	}

}
