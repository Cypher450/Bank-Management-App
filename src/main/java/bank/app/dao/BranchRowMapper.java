package bank.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bank.app.entities.Branch;

public class BranchRowMapper implements RowMapper<Branch> {

	@Override
	public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {

		int branchId = rs.getInt("branch_id");
		String branchName = rs.getString("branch_name");
		
		return new Branch(branchId, branchName);
	}

}
