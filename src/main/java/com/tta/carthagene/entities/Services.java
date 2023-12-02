package com.tta.carthagene.entities;

import java.util.Date;
public class Services {
    private String idService;
    private String description;
    private String descriptionArab; // Updated field name
    private String descriptionEng; // New field
    private int categorie;
    private Date creationDate;

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionArab() { // Updated getter method name
        return descriptionArab;
    }

    public void setDescriptionArab(String descriptionArab) { // Updated setter method name
        this.descriptionArab = descriptionArab;
    }

    public String getDescriptionEng() {
        return descriptionEng;
    }

    public void setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

   
    @Override
    public String toString() {
        return "Services [idService=" + idService + ", description=" + description + ", descriptionArab=" + descriptionArab + ", descriptionEng=" + descriptionEng + ", categorie=" + categorie + "]";
    }
}