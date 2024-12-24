package com.app.repository;

import java.util.List;

import com.app.model.CityModel;

public interface CityRepository {

	public boolean isAddNewCity(CityModel model);

	public int getCityIdByCityName(String cityName, int stateId, int distId);

	public List<CityModel> showAllCityByDist(String stateName, String distName);
	
	public boolean updateCityNameByDist(String cityName, int cityId);
	
	public boolean deleteCityByName(String cityName);

}
