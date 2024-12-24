package com.app.model;

import lombok.Data;

@Data

public class DistrictModel extends StateModel {

	private int distId;

	public DistrictModel() {

	}

	public DistrictModel(int distId, String distName) {
		this.distId = distId;
		this.distName = distName;
	}

	public int getDistId() {
		return distId;
	}

	public void setDistId(int distId) {
		this.distId = distId;
	}

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	private String distName;

}
