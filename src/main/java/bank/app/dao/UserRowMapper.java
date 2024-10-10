package bank.app.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bank.app.entities.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		// `user_id`, `username`, `first_name`, `last_name`, `email`, `phone`,
		// `role_id`, `address`, `dob`, `approval_status`, `salt_password`,
		// `hashed_password`, `active_status`
		int userId = rs.getInt("user_id");
		String username = rs.getString("username");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		String email = rs.getString("email");
		String phone = rs.getString("phone");
		int roleId = rs.getInt("role_id");
		String address = rs.getString("address");
		Date dateOfBirth = rs.getDate("dob");
		String approvalStatus = rs.getString("approval_status");
		String saltPassword = rs.getString("salt_password");
		String hashedPassword = rs.getString("hashed_password");
		String activeStatus = rs.getString("active_status");

		return new User(userId, username, firstName, lastName, email, phone, roleId, address, dateOfBirth,
				approvalStatus, saltPassword, hashedPassword, activeStatus);
	}

}
