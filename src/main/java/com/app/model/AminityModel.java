package com.app.model;

import lombok.Data;

@Data

public class AminityModel {

	private String aminityName;
	private int aminityId;
	private int amPrice;
	
	public AminityModel() {
		
	}
	
	public AminityModel(String aminityName, int aminityId) {
		this.aminityName = aminityName;
		this.aminityId = aminityId;
	}
	
	public int getAminityId() {
		return aminityId;
	}
	public void setAminityId(int aminityId) {
		this.aminityId = aminityId;
	}
	public String getAminityName() {
		return aminityName;
	}
	public void setAminityName(String aminityName) {
		this.aminityName = aminityName;
	}
}
