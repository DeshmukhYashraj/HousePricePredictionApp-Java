package com.app.service;

import java.util.List;

import com.app.model.CityModel;

public interface CityService {

	public boolean isAddNewCity(CityModel model);
	// public Optional<List<CityModel>> getAllCityNameDistrict(String stateName,
	// String distName);

	public List<CityModel> showAllCityByDist(String stateName, String distName);
	
	public boolean updateCityNameByDist(String cityName, int cityId);
	
	public boolean deleteCityByName(String cityName);



}
