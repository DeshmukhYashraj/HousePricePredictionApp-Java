package com.app.service;

import java.util.List;

import com.app.model.AminityModel;

public interface AminityService {

	public boolean isAddNewAminity(AminityModel model);
	
	public List<AminityModel> getAllAminities();
	
	public boolean updateAminityNameById(String amiName, int amiId);
	
	public boolean deleteAminityName(int amiId);


}
