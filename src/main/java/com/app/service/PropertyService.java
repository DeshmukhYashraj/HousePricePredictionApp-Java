package com.app.service;

import com.app.model.PropertyModel;
import com.app.repository.PropertyRepository;
import com.app.repository.PropertyRepositoryImpl;

public interface PropertyService {

	public boolean isAddNewProperty(PropertyModel model);

}
