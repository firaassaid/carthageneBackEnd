package com.tta.carthagene.entities;

public class Specialites {
    private int idSpecialite;
    private String description;
    private String descriptionArab;
    private String descriptionEng;

    public int getIdSpecialite() {
        return idSpecialite;
    }

    public void setIdSpecialite(int id) {
        this.idSpecialite = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   

    public String getDescriptionEng() {
        return descriptionEng;
    }

    public void setDescriptionEng(String descriptionEng) {
        this.descriptionEng = descriptionEng;
    }

	public String getDescriptionArab() {
		return descriptionArab;
	}

	public void setDescriptionArab(String descriptionArab) {
		this.descriptionArab = descriptionArab;
	}

	@Override
	public String toString() {
		return "Specialites [idSpecialite=" + idSpecialite + ", description=" + description + ", descriptionArab="
				+ descriptionArab + ", descriptionEng=" + descriptionEng + "]";
	}

   
}
