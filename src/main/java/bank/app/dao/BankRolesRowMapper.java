package bank.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bank.app.entities.Roles;

public class BankRolesRowMapper implements RowMapper<Roles>{

	@Override
	public Roles mapRow(ResultSet rs, int rowNum) throws SQLException {

		int roleId = rs.getInt("role_id");
		String roleName = rs.getString("role_name");
		
		return new Roles(roleId, roleName);
	}

}
