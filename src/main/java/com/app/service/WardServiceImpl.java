package com.app.service;

import java.util.List;

import com.app.model.WardModel;
import com.app.repository.WardRepository;
import com.app.repository.WardRepositoryImpl;

public class WardServiceImpl implements WardService{

	WardRepository wardRepo = new WardRepositoryImpl();
	
	@Override
	public boolean isAddNewWard(String cityName, String wardName, int pincode) {
		
		return wardRepo.isAddNewWard(cityName, wardName, pincode);
	}

	@Override
	public List<WardModel> showAllWardByCity(String cityName) {
		return wardRepo.showAllWardByCity(cityName);
	}

	@Override
	public boolean updateWardName(String newWardName, int wardId) {
		return wardRepo.updateWardName(newWardName, wardId);
	}

	@Override
	public boolean deleteWard(int wardId) {
		return wardRepo.deleteWard(wardId);
	}

	@Override
	public int getwardIdByName(String wardName) {
		
		return wardRepo.getwardIdByName(wardName);
	}

}
