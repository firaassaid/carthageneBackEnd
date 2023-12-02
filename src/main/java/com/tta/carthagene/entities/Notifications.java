package com.tta.carthagene.entities;

import java.util.Date;

public class Notifications {
	
	
	
	private String  idNotif ;
	private String  idRdv ;
	private String  description ;
	private String  idPatient ;
	private Date  creationDate ;
	private String type;
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIdNotif() {
		return idNotif;
	}
	public void setIdNotif(String idNotif) {
		this.idNotif = idNotif;
	}
	public String getIdRdv() {
		return idRdv;
	}
	public void setIdRdv(String idRdv) {
		this.idRdv = idRdv;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIdPatient() {
		return idPatient;
	}
	public void setIdPatient(String idPatient) {
		this.idPatient = idPatient;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	@Override
	public String toString() {
		return "Notifications [idNotif=" + idNotif + ", idRdv=" + idRdv + ", description=" + description
				+ ", idPatient=" + idPatient + ", creationDate=" + creationDate + ", type=" + type + "]";
	}

	
	

}
