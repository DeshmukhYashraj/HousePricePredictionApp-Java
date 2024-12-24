package com.app.model;
//

//import lombok.*;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor

public class CityModel extends DistrictModel {

	private int cityId;
	private String cityName;

	public CityModel() {

	}

	public CityModel(int cityId, String cityName) {
		this.cityId = cityId;
		this.cityName = cityName;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return  cityId + "\t" + cityName ;
	}

}
