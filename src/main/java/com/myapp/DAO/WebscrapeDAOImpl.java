package com.myapp.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.entity.DataPoints;
import com.myapp.entity.DataPointsRowMapper;
import com.myapp.entity.ProjectNature;
import com.myapp.entity.ProjectNatureRowMapper;
import com.myapp.entity.Region;
import com.myapp.entity.RegionRowMapper;
import com.myapp.entity.Request;
import com.myapp.entity.RequestRowMapper;
import com.myapp.entity.Team;
import com.myapp.entity.TeamRowMapper;

@Transactional
@Repository
public class WebscrapeDAOImpl implements WebscrapeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static final Logger logger = LoggerFactory.getLogger(WebscrapeDAOImpl.class);

	@Override
	public List<Region> getAllRegions() {
		String sql = "SELECT * FROM webscrape_region";
		RowMapper<Region> rowMapper = new RegionRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Team> getTeamByRegionId(Integer id) {
		String sql = "SELECT * FROM webscrape_team where region_id = " + id;
		RowMapper<Team> rowMapper = new TeamRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public void addRequest(Request request) {
		
		request = insertDataToRequestTable(request);
		insertDataToDataPointTable(request);
		insertDataToProjectNatureTable(request);
	}
	
	@Override
	public List<Request> getAllRequests() {
		
		String sql = "SELECT * from webscrape_request";
		RowMapper<Request> rowMapper = new RequestRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	
	@Override
	public Request getRequestByRequestId(Integer requestId) {

		String sql = "SELECT * from webscrape_request where request_id = " + requestId;
		RowMapper<Request> rowMapper = new RequestRowMapper();
		
		ProjectNature projectNature = getProjectNature(requestId);
		List<DataPoints> dataPoints = getDataPoints(requestId);

		Request request = this.jdbcTemplate.queryForObject(sql, rowMapper);

		request.setProjectNature(projectNature);

		DataPoints[] dataPointArray = new DataPoints[dataPoints.size()];

		int i = 0;
		for (DataPoints data : dataPoints) {
			dataPointArray[i] = data;
			i++;
		}

		request.setDataPoints(dataPointArray);

		return request;
	}
	
	@Override
	public void deleteRequest(Integer requestId) {
		String sql = "DELETE from webscrape_request where request_id = ?";
		this.jdbcTemplate.update(sql, requestId);
	}
	
	@Override
	public void updateRequestByRequestId(Request request) {
		updateRequestTable(request);
		updateDataPointTable(request);
		updateProjectNatureTable(request);
		
	}
	
	private Request insertDataToRequestTable(Request request) {
		final String sql = "INSERT INTO webscrape_request (job_name, requested_by, client_name, region_id, team_id, url) values (?, ?, ?, ?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		this.jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[] { "request_id" });
				preparedStatement.setString(1, request.getJobName());
				preparedStatement.setString(2, request.getRequestedBy());
				preparedStatement.setString(3, request.getClientName());
				preparedStatement.setInt(4, request.getRegionId());
				preparedStatement.setInt(5, request.getTeamId());
				preparedStatement.setString(6, request.getUrl());
				return preparedStatement;
			}
		}, keyHolder);

		request.setRequestId(keyHolder.getKey().intValue());
		
		return request;
	}
	
	private void insertDataToDataPointTable(Request request) {
		for (DataPoints datapoint : request.getDataPoints()) {
			String sql = "INSERT INTO webscrape_data_points (request_id, data_point, data_type, comments) values (?, ?, ?, ?)";
			this.jdbcTemplate.update(sql, request.getRequestId(), datapoint.getDataPoint(), datapoint.getDataType(),
					datapoint.getComments());
		}
	}
	
	private void insertDataToProjectNatureTable(Request request) {
		ProjectNature projectNature = request.getProjectNature();

		String sql = "INSERT INTO webscrape_project_nature (request_id, input_set, input_set_size, expected_output, execution_frequency, execution_date_time, "
				+ "delivery_frequency, delivery_date_time, delivery_receipients, instructions) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		this.jdbcTemplate.update(sql, request.getRequestId(), projectNature.getInputSet(), projectNature.getInputSetSize(),
				projectNature.getExpectedOutput(), projectNature.getExecutionFrequency(),
				projectNature.getExecutionDateTime(), projectNature.getDeliveryFrequency(),
				projectNature.getDeliveryDateTime(), projectNature.getDeliveryReceipients(),
				projectNature.getInstructions());
		
	}

	private ProjectNature getProjectNature(Integer requestId) {
		String sql = "SELECT * FROM webscrape_project_nature where request_id = " + requestId;
		RowMapper<ProjectNature> rowMapper = new ProjectNatureRowMapper();

		return this.jdbcTemplate.queryForObject(sql, rowMapper);
	}

	private List<DataPoints> getDataPoints(Integer requestId) {
		String sql = "SELECT * FROM webscrape_data_points where request_id = " + requestId;
		RowMapper<DataPoints> rowMapper = new DataPointsRowMapper();

		return this.jdbcTemplate.query(sql, rowMapper);
	}

	private void updateRequestTable(Request request) {
		String sql = "UPDATE webscrape_request SET job_name=?, requested_by=?, client_name=?, region_id=?, team_id=?, url=? where request_id=?";
		
		this.jdbcTemplate.update(sql, request.getJobName(), request.getRequestedBy(), request.getClientName(),
				request.getRegionId(), request.getTeamId(), request.getUrl(), request.getRequestId());
		
	}

	private void updateDataPointTable(Request request) {
		for (DataPoints datapoint : request.getDataPoints()) {
			String sql = "UPDATE webscrape_data_points SET data_point=?, data_type=?, comments=? where request_id=? and point_id=?";
			this.jdbcTemplate.update(sql, datapoint.getDataPoint(), datapoint.getDataType(), datapoint.getComments(),
					request.getRequestId(), datapoint.getPointId());
		}
		
	}

	private void updateProjectNatureTable(Request request) {
		ProjectNature projectNature = request.getProjectNature();

		String sql = "UPDATE webscrape_project_nature SET input_set=?, input_set_size=?, expected_output=?, execution_frequency=?, execution_date_time=?, "
				+ "delivery_frequency=?, delivery_date_time=?, delivery_receipients=?, instructions=? where request_id=?";

		this.jdbcTemplate.update(sql, projectNature.getInputSet(), projectNature.getInputSetSize(),
				projectNature.getExpectedOutput(), projectNature.getExecutionFrequency(),
				projectNature.getExecutionDateTime(), projectNature.getDeliveryFrequency(),
				projectNature.getDeliveryDateTime(), projectNature.getDeliveryReceipients(),
				projectNature.getInstructions(), request.getRequestId());
		
	}

}
