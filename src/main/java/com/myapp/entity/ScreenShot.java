package com.myapp.entity;

public class ScreenShot {

	private int requestId;
	private String base64Image;
	
	public int getRequestId() {
		return requestId;
	}
	
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	@Override
	public String toString() {
		return "ScreenShot [requestId=" + requestId + ", base64Image=" + base64Image + "]";
	}
	
}
