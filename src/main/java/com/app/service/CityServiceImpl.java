package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.model.CityModel;
import com.app.repository.CityRepository;
import com.app.repository.CityRepositoryImpl;

public class CityServiceImpl implements CityService {

	CityRepository cityRepo = new CityRepositoryImpl();

	@Override
	public boolean isAddNewCity(CityModel model) {

		return cityRepo.isAddNewCity(model);
	}

	@Override
	public List<CityModel> showAllCityByDist(String stateName, String distName) {
		return cityRepo.showAllCityByDist(stateName, distName);
	}

	@Override
	public boolean updateCityNameByDist(String cityName, int cityId) {
		return cityRepo.updateCityNameByDist(cityName, cityId);
	}

	@Override
	public boolean deleteCityByName(String cityName) {
		return cityRepo.deleteCityByName(cityName);
	}

}
