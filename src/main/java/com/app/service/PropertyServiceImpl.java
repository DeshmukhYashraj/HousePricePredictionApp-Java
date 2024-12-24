package com.app.service;

import com.app.model.PropertyModel;
import com.app.repository.PropertyRepository;
import com.app.repository.PropertyRepositoryImpl;

public class PropertyServiceImpl implements PropertyService{

	PropertyRepository propRepo = new PropertyRepositoryImpl();
	
	@Override
	public boolean isAddNewProperty(PropertyModel model) {
		return propRepo.isAddNewProperty(model);
	}

}
