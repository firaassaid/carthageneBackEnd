package com.tta.carthagene.entities;

import java.util.Date;

public class News {
	
	
	
	private String  idNews ;
	private String  title ;
	private String  description ;
	private String  dateNews ;
	private String  attachment ;
	private Date  creationDate ;

	public String getIdNews() {
		return idNews;
	}
	public void setIdNews(String idNews) {
		this.idNews = idNews;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getDateNews() {
		return dateNews;
	}
	public void setDateNews(String dateNews) {
		this.dateNews = dateNews;
	}
	
	
	
	@Override
	public String toString() {
		return "news [idNews=" + idNews + ", title=" + title + ", desciption=" + description + ", attachment=" + attachment + ", creationDate=" + creationDate + ", dateNews=" + dateNews + "]";
	}
	
	
	


}
