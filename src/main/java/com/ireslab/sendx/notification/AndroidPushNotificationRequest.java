package com.ireslab.sendx.notification;

import org.json.JSONObject;

public class AndroidPushNotificationRequest {
	
	private String topic; 
	private JSONObject body;
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public JSONObject getBody() {
		return body;
	}
	public void setBody(JSONObject body) {
		this.body = body;
	}
	
	
	

}
