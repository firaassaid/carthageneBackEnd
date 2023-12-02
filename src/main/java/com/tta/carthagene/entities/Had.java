package com.tta.carthagene.entities;

import java.util.Date;

public class Had {
	

	private String  idHad;
	private String  idPatient;
	private String  besoin ;
	private String  address ;
	private String  status ;
	private String  numeroTel ;
	private String type;
	
	private Date  dateRdvHad ;
	
	private Date creationDate;
	 // encours valide annuler

	private Double rating;
	private String  code;
	private String materiel;
	private String image;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIdHad() {
		return idHad;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setIdHad(String idHad) {
		this.idHad = idHad;
	}

	public String getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(String idPatient) {
		this.idPatient = idPatient;
	}

	public String getBesoin() {
		return besoin;
	}

	public void setBesoin(String besoin) {
		this.besoin = besoin;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNumeroTel() {
		return numeroTel;
	}

	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}

	public Date getDateRdvHad() {
		return dateRdvHad;
	}

	public void setDateRdvHad(Date dateRdvHad) {
		this.dateRdvHad = dateRdvHad;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getMateriel() {
		return materiel;
	}

	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Had [idHad=" + idHad + ", idPatient=" + idPatient + ", besoin=" + besoin + ", address=" + address
				+ ", status=" + status + ", numeroTel=" + numeroTel + ", type=" + type + ", dateRdvHad=" + dateRdvHad
				+ ", creationDate=" + creationDate + ", rating=" + rating + ", code=" + code + ", materiel=" + materiel
				+ ", image=" + image + "]";
	}




}
