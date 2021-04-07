package com.mbvan.mbvan.common.dto;

//the request header
public class ClientRequestHeader {
	
	private String sessionId;
	
	private String userId;
	
	//"Mobile", "MMC", "Portal"
	private String channelType;
	
	private String vin;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}
	
	
	
}
