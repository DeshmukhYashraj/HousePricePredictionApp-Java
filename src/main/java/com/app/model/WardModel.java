package com.app.model;

import lombok.ToString;

public class WardModel extends CityModel {

	private int wardId;
	private String wardName;

	public WardModel() {

	}

	public WardModel(String wardName, int wardId) {
		this.wardName = wardName;
		this.wardId = wardId;
	}

	public int getWardId() {
		return wardId;
	}

	public void setWardId(int wardId) {
		this.wardId = wardId;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	
	@Override
	public String toString() {
		return wardId + "\t" + wardName ;
	}

}
