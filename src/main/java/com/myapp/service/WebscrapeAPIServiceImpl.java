package com.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.DAO.WebscrapeDAO;
import com.myapp.entity.Region;
import com.myapp.entity.Request;
import com.myapp.entity.Team;

@Service
public class WebscrapeAPIServiceImpl implements WebscrapeAPIService {

	@Autowired
	private WebscrapeDAO webscrapeDAO;

	@Override
	public List<Region> getAllRegions() {
		return webscrapeDAO.getAllRegions();
	}

	@Override
	public List<Team> getTeamByRegionId(Integer id) {
		return webscrapeDAO.getTeamByRegionId(id);
	}

	@Override
	public void addRequest(Request request) {
		webscrapeDAO.addRequest(request);

	}

	@Override
	public Request getRequestByRequestId(Integer requestId) {
		return webscrapeDAO.getRequestByRequestId(requestId);
	}

	@Override
	public List<Request> getAllRequests() {
		return webscrapeDAO.getAllRequests();
	}

	@Override
	public void deleteRequest(Integer requestId) {
		webscrapeDAO.deleteRequest(requestId);
	}

	@Override
	public void updateRequestByRequestId(Request request) {
		webscrapeDAO.updateRequestByRequestId(request);
	}

}
