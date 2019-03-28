package com.myapp.entity;

public class Request {

	private int requestId;
	private String jobName;
	private String requestedBy;
	private String clientName;
	private int regionId;
	private int teamId;
	private String url;
	private DataPoints[] dataPoints;
	private ProjectNature projectNature;
	
	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public DataPoints[] getDataPoints() {
		return dataPoints;
	}

	public void setDataPoints(DataPoints[] dataPoints) {
		this.dataPoints = dataPoints;
	}

	public ProjectNature getProjectNature() {
		return projectNature;
	}

	public void setProjectNature(ProjectNature projectNature) {
		this.projectNature = projectNature;
	}

	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", jobName=" + jobName + ", requestedBy=" + requestedBy
				+ ", clientName=" + clientName + ", regionId=" + regionId + ", teamId=" + teamId + ", url=" + url + "]";
	}
	
}
