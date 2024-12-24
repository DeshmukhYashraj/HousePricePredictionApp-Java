package com.app.service;

import java.util.List;

import com.app.model.AminityModel;
import com.app.repository.AminityRepository;
import com.app.repository.AminityRepositoryImpl;

public class AminityServiceImpl implements AminityService {

	AminityRepository amiRepo = new AminityRepositoryImpl();
	
	@Override
	public boolean isAddNewAminity(AminityModel model) {
		return amiRepo.isAddNewAminity(model);
	}

	@Override
	public List<AminityModel> getAllAminities() {
		return amiRepo.getAllAminities();
	}

	@Override
	public boolean updateAminityNameById(String amiName, int amiId) {
		return amiRepo.updateAminityNameById(amiName, amiId);
	}

	@Override
	public boolean deleteAminityName(int amiId) {
		return amiRepo.deleteAminityName(amiId);
	}

}
