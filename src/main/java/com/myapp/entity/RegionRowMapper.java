package com.myapp.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RegionRowMapper implements RowMapper<Region> {

	@Override
	public Region mapRow(ResultSet rs, int rowNum) throws SQLException {
		Region region = new Region();
		
		region.setRegionId(rs.getInt("region_id"));
		region.setRegionName(rs.getString("region_name"));
		
		return region;
	}
}
