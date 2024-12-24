package com.app.service;

import java.util.List;

import com.app.model.WardModel;

public interface WardService {
	
	public boolean isAddNewWard(String cityName, String wardName, int pincode);

	public List<WardModel> showAllWardByCity(String cityName);
	
	public boolean updateWardName(String newWardName, int wardId);
	
	public boolean deleteWard(int wardId);
	
	public int getwardIdByName(String wardName);

	
}
