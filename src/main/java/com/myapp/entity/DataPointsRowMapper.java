package com.myapp.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DataPointsRowMapper implements RowMapper<DataPoints>{

	@Override
	public DataPoints mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		DataPoints dataPoints = new DataPoints();
		
		dataPoints.setRequestId(rs.getInt("request_id"));
		dataPoints.setPointId(rs.getInt("point_id"));
		dataPoints.setDataPoint(rs.getString("data_point"));
		dataPoints.setDataType(rs.getString("data_type"));
		dataPoints.setComments(rs.getString("comments"));
		
		return dataPoints;
		
	}

}
