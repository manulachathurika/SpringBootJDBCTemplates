package com.myapp.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ScreenShotRowMapper implements RowMapper<ScreenShot>{

	@Override
	public ScreenShot mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ScreenShot screenShot = new ScreenShot();
		screenShot.setRequestId(rs.getInt("request_id"));
		screenShot.setBase64Image(rs.getString("screen_capture"));
		
		return screenShot;
	}

}
