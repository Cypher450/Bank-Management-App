package bank.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bank.app.entities.Region;

public class RegionRowMapper implements RowMapper<Region> {

	@Override
	public Region mapRow(ResultSet rs, int rowNum) throws SQLException {

		int regionId = rs.getInt("region_id");
		String regionName = rs.getString("region_name");

		return new Region(regionId, regionName);
	}

}
