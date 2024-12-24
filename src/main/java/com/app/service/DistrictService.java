package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.customException.DistrictNotFoundException;
import com.app.model.DistrictModel;

public interface DistrictService {

	public boolean isAddNewDistByState(String stateName,String distName);
	public boolean isAddBulkDist(String stateName);
	public Optional<List<DistrictModel>> getAllDistrictByState(String stateName);
	public boolean updateDistNameByStateName(String stateName, String currDistName, String newDistName);
	public boolean deleteDistNameByState(String stateName, String distName) throws DistrictNotFoundException;
	public int getDistIdByDistName(String distName);

}
