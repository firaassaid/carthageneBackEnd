package com.tta.carthagene.entities;

import java.util.Date;

public class RendezVous {
	

	private String  idRdv;
	private String  idPatient;
	private String  idMedecin ;
	private String  nom ;
	private String  prenom ;
	private String  numeroTel ;
	private String  specialiteId ;
	private String  serviceId ;
	private String  email ;
	private Date  dateRdv ;
	private Date dateNaissance;
	private Date creationDate;
	private String status;
	private Double rating;// encours valide annuler
	
	
	public String getIdRdv() {
		return idRdv;
	}
	public void setIdRdv(String idRdv) {
		this.idRdv = idRdv;
	}
	public String getIdPatient() {
		return idPatient;
	}
	public void setIdPatient(String idPatient) {
		this.idPatient = idPatient;
	}
	public String getIdMedecin() {
		return idMedecin;
	}
	public void setIdMedecin(String idMedecin) {
		this.idMedecin = idMedecin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNumeroTel() {
		return numeroTel;
	}
	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}
	public String getSpecialiteId() {
		return specialiteId;
	}
	public void setSpecialiteId(String specialiteId) {
		this.specialiteId = specialiteId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateRdv() {
		return dateRdv;
	}
	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	@Override
	public String toString() {
		return "RendezVous [idRdv=" + idRdv + ", idPatient=" + idPatient + ", idMedecin=" + idMedecin + ", nom=" + nom
				+ ", prenom=" + prenom + ", numeroTel=" + numeroTel + ", specialiteId=" + specialiteId + ", serviceId="
				+ serviceId + ", email=" + email + ", dateRdv=" + dateRdv + ", dateNaissance=" + dateNaissance
				+ ", creationDate=" + creationDate + ", status=" + status + ", rating=" + rating + "]";
	}
	


}
