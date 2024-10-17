package bank.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RegionDaoImpl implements RegionDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Override
	public int getRegionId(int rm_id) {

		String sql = "SELECT region_id from regional_manager WHERE rm_id = ?";
		
		return jdbcTemplate.queryForObject(sql,int.class ,rm_id);
	}

	
	
}
