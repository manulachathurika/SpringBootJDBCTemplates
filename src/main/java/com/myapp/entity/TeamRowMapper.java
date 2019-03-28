package com.myapp.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TeamRowMapper implements RowMapper<Team>{

	@Override
	public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
		Team team = new Team();
		
		team.setTeamId(rs.getInt("team_id"));
		team.setTeamName(rs.getString("team_name"));
		team.setRegionId(rs.getInt("region_id"));
		
		return team;
	}

}
