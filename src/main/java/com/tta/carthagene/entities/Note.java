package com.tta.carthagene.entities;

import java.util.Map;

public class Note {
    private String subject;
    private String content;
    private Map<String, String> data;
    private String image;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Note [subject=" + subject + ", content=" + content + ", data=" + data + ", image=" + image
				+ ", getSubject()=" + getSubject() + ", getContent()=" + getContent() + ", getData()=" + getData()
				+ ", getImage()=" + getImage() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
    
}