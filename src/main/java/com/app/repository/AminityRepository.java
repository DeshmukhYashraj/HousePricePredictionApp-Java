package com.app.repository;

import java.util.List;

import com.app.model.AminityModel;

public interface AminityRepository {

	public boolean isAddNewAminity(AminityModel model);
	
	public List<AminityModel> getAllAminities();
	
	public boolean updateAminityNameById(String amiName, int amiId);
	
	public boolean deleteAminityName(int amiId);
}
