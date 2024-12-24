package com.app.repository;

import java.util.List;
import java.util.Optional;

import com.app.customException.DistrictNotFoundException;
import com.app.customException.StateNotFoundException;
import com.app.model.DistrictModel;
import com.app.model.StateModel;

public interface StateRepository {

	public boolean isAddNewState(StateModel model);
	public Optional<List<StateModel>> getAllStates();
	public StateModel getStateByName(String stateName);
	public StateModel deleteStateById(int stateId);
	public boolean updateStateByName(String currStateName, String newStateName);
	public int getStateIdByName(String stateName);
}

