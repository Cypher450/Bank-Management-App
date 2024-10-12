package bank.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BranchDaoImpl implements BranchDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean addNewBranch(String branchName, String address, String phone, String pinCode, String regionId) {
        String sql = "INSERT INTO branch (branch_name, address, phone, pin_code, region_id) VALUES (?, ?, ?, ?, ?)";

        try {
            int rowsInserted = jdbcTemplate.update(sql, branchName, address, phone, pinCode, regionId);
            return rowsInserted > 0; // Return true if at least one row is inserted
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
