package com.myapp.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProjectNatureRowMapper implements RowMapper<ProjectNature> {

	@Override
	public ProjectNature mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ProjectNature projectNature = new ProjectNature();
		
		projectNature.setRequestId(rs.getInt("request_id"));
		projectNature.setInputSet(rs.getString("input_set"));
		projectNature.setInputSetSize(rs.getInt("input_set_size"));
		projectNature.setExpectedOutput(rs.getString("expected_output"));
		projectNature.setExecutionFrequency(rs.getString("execution_frequency"));
		projectNature.setExecutionDateTime(rs.getString("execution_date_time"));
		projectNature.setDeliveryFrequency(rs.getString("delivery_frequency"));
		projectNature.setDeliveryDateTime(rs.getString("delivery_date_time"));
		projectNature.setDeliveryReceipients(rs.getString("delivery_receipients"));
		projectNature.setInstructions(rs.getString("instructions"));
		
		return projectNature;
	}

}
