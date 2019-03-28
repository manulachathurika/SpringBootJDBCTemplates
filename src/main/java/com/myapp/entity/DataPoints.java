package com.myapp.entity;

public class DataPoints {

	private int requestId;
	private int pointId;
	private String dataPoint;
	private String dataType;
	private String comments;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}

	public String getDataPoint() {
		return dataPoint;
	}

	public void setDataPoint(String dataPoint) {
		this.dataPoint = dataPoint;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "DataPoints [requestId=" + requestId + ", pointId=" + pointId + ", dataPoint=" + dataPoint
				+ ", dataType=" + dataType + ", comments=" + comments + "]";
	}
	
}
