package com.tta.carthagene.entities;

import java.util.Date;

public class Partenaires {
	
	private String  idPartenaireHAD ;
	private String  nom ;
	private String  prenom ;
	private String  email ;
	private int  specialiteHADId ;
	private String  telephone ;
	private Date creationDate;
	private SpecialitesHAD specialitesHAD;
	private String image;
	
	public String getIdPartenaireHAD() {
		return idPartenaireHAD;
	}
	public void setIdPartenaireHAD(String idPartenaireHAD) {
		this.idPartenaireHAD = idPartenaireHAD;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSpecialiteHADId() {
		return specialiteHADId;
	}
	public void setSpecialiteHADId(int specialiteHADId) {
		this.specialiteHADId = specialiteHADId;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public SpecialitesHAD getSpecialitesHAD() {
		return specialitesHAD;
	}
	public void setSpecialitesHAD(SpecialitesHAD specialitesHAD) {
		this.specialitesHAD = specialitesHAD;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Partenaires [idPartenaireHAD=" + idPartenaireHAD + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", specialiteHADId=" + specialiteHADId + ", telephone=" + telephone + ", creationDate="
				+ creationDate + ", specialitesHAD=" + specialitesHAD + ", image=" + image + "]";
	}
	
	
	

	
	

}
