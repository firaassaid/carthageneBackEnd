package com.tta.carthagene.entities;

public class SpecialitesHAD {
	private int idSpecialiteHAD;
	private String description;
	public int getIdSpecialiteHAD() {
		return idSpecialiteHAD;
	}
	public void setIdSpecialiteHAD(int idSpecialiteHAD) {
		this.idSpecialiteHAD = idSpecialiteHAD;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "SpecialitesHAD [idSpecialiteHAD=" + idSpecialiteHAD + ", description=" + description + "]";
	}



}
