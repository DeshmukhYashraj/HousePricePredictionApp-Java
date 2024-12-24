package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.customException.DistrictNotFoundException;
import com.app.model.DistrictModel;
import com.app.repository.DistrictRepository;
import com.app.repository.DistrictRepositoryImpl;

public class DistrictServiceImpl implements DistrictService {

	DistrictRepository distRepo = new DistrictRepositoryImpl();
	
	@Override
	public boolean isAddNewDistByState(String stateName, String distName) {
		
		return distRepo.isAddNewDistByState(stateName, distName);
	}

	@Override
	public boolean isAddBulkDist(String stateName) {
		
		return distRepo.isAddBulkDist(stateName);
	}

	@Override
	public Optional<List<DistrictModel>> getAllDistrictByState(String stateName) {
		
		return distRepo.getAllDistrictByState(stateName);
	}

	@Override
	public boolean updateDistNameByStateName(String stateName, String currDistName, String newDistName) {
		
		return distRepo.updateDistNameByStateName(stateName, currDistName, newDistName);
	}

	@Override
	public boolean deleteDistNameByState(String stateName, String distName) throws DistrictNotFoundException {
		
		return distRepo.deleteDistNameByState(stateName, distName);
	}

	@Override
	public int getDistIdByDistName(String distName) {
		
		return distRepo.getDistIdByDistName(distName);
	}

}
