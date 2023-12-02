package com.tta.carthagene.entities;

public class NotificationRequest {
    private String title;
    private String body;
    private String deviceToken;

    // Constructors, getters, and setters

    // Example constructor
    public NotificationRequest(String title, String body, String deviceToken) {
        this.title = title;
        this.body = body;
        this.deviceToken = deviceToken;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	@Override
	public String toString() {
		return "NotificationRequest [title=" + title + ", body=" + body + ", deviceToken=" + deviceToken
				+ ", getTitle()=" + getTitle() + ", getBody()=" + getBody() + ", getDeviceToken()=" + getDeviceToken()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public NotificationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

    // Getters and setters
}
