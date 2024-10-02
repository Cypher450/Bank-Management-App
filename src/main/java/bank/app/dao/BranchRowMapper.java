package bank.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bank.app.entities.Branch;

public class BranchRowMapper implements RowMapper<Branch> {

	@Override
	public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {

		int branchId = rs.getInt("branch_id");
		String bankName = rs.getString("bank_name");
		String branchName = rs.getString("branch_name");
		String address = rs.getString("address");
		String phone = rs.getString("phone");
		String pincode = rs.getString("pin_code");
		int regionId = rs.getInt("region_id");
		int bankTypeId = rs.getInt("bank_type_id");

		return new Branch(branchId, bankName, branchName, address, phone, pincode, regionId, bankTypeId);

	}

}
