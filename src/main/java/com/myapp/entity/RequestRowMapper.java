package com.myapp.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RequestRowMapper implements RowMapper<Request>{

	@Override
	public Request mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Request request = new Request();
		
		request.setRequestId(rs.getInt("request_id"));
		request.setJobName(rs.getString("job_name"));
		request.setRequestedBy(rs.getString("requested_by"));
		request.setClientName(rs.getString("client_name"));
		request.setRegionId(rs.getInt("region_id"));
		request.setTeamId(rs.getInt("team_id"));
		request.setUrl(rs.getString("url"));
		
		return request;
	}

}
