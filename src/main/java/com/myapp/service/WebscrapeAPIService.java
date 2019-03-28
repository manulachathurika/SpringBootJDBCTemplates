package com.myapp.service;

import java.util.List;

import com.myapp.entity.Region;
import com.myapp.entity.Request;
import com.myapp.entity.Team;

public interface WebscrapeAPIService {

	public List<Region> getAllRegions();

	public List<Team> getTeamByRegionId(Integer id);

	public void addRequest(Request request);

	public Request getRequestByRequestId(Integer requestId);

	public List<Request> getAllRequests();

	public void deleteRequest(Integer requestId);

	public void updateRequestByRequestId(Request request);

}
