package com.tta.carthagene.entities;

import java.util.Date;

public class Medecins {
	
	
	
	private String  idMedecin ;
	private String  nom ;
	private String  prenom ;
	private String  telephone ;
	private int  specialiteId ;
	private String  email ;
	private boolean  conventionCnam ;
	private String specialiteMedicales;
	private String explorationFonctionelle;
	private String specialiteChirurgicales;
	private Date creationDate;
	private boolean disabled;
	private String nom_arab;
	private String prenom_arab;
	private String image;

	
	
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getSpecialiteMedicales() {
		return specialiteMedicales;
	}
	public void setSpecialiteMedicales(String specialiteMedicales) {
		this.specialiteMedicales = specialiteMedicales;
	}
	public String getExplorationFonctionelle() {
		return explorationFonctionelle;
	}
	public void setExplorationFonctionelle(String explorationFonctionelle) {
		this.explorationFonctionelle = explorationFonctionelle;
	}
	public String getSpecialiteChirurgicales() {
		return specialiteChirurgicales;
	}
	public void setSpecialiteChirurgicales(String specialiteChirurgicales) {
		this.specialiteChirurgicales = specialiteChirurgicales;
	}

	private Specialites specialites;
	
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getSpecialiteId() {
		return specialiteId;
	}
	public void setSpecialiteId(int specialiteId) {
		this.specialiteId = specialiteId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isConventionCnam() {
		return conventionCnam;
	}
	public void setConventionCnam(boolean conventionCnam) {
		this.conventionCnam = conventionCnam;
	}
	
	public Specialites getSpecialites() {
		return specialites;
	}
	public void setSpecialites(Specialites specialites) {
		this.specialites = specialites;
	}
	public String getNom_arab() {
	    return nom_arab;
	}

	public void setNom_arab(String nom_arab) {
	    this.nom_arab = nom_arab;
	}

	public String getPrenom_arab() {
	    return prenom_arab;
	}

	public void setPrenom_arab(String prenom_arab) {
	    this.prenom_arab = prenom_arab;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Medecins [idMedecin=" + idMedecin + ", nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone
				+ ", specialiteId=" + specialiteId + ", email=" + email + ", conventionCnam=" + conventionCnam
				+ ", specialiteMedicales=" + specialiteMedicales + ", explorationFonctionelle="
				+ explorationFonctionelle + ", specialiteChirurgicales=" + specialiteChirurgicales + ", creationDate="
				+ creationDate + ", disabled=" + disabled + ", nom_arab=" + nom_arab + ", prenom_arab=" + prenom_arab
				+ ", image=" + image + ", specialites=" + specialites + "]";
	}


	
	
	


}
