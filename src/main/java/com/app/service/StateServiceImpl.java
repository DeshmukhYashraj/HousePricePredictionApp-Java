package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.customException.DistrictNotFoundException;
import com.app.customException.StateNotFoundException;
import com.app.model.DistrictModel;
import com.app.model.StateModel;
import com.app.repository.StateRepository;
import com.app.repository.StateRepositoryImpl;

public class StateServiceImpl implements StateService {

	private static final String String = null;
	StateRepository stmtRepo = new StateRepositoryImpl();
	
	@Override
	public boolean isAddNewState(StateModel model) {
		
		return  stmtRepo.isAddNewState(model);
	}

	@Override
	public Optional<List<StateModel>> getAllStates() {
		
		return stmtRepo.getAllStates();
	}

	@Override
	public StateModel getStateByName(String stateName) {
		return stmtRepo.getStateByName(stateName);
	}

	@Override
	public StateModel deleteStateById(int stateId) {
		
		return stmtRepo.deleteStateById(stateId);
	}

	@Override
	public boolean updateStateByName(String currStateName, String newStateName) {	
		return stmtRepo.updateStateByName(currStateName, newStateName);
	}

	@Override
	public int getStateIdByName(String stateName) {
		return stmtRepo.getStateIdByName(stateName);
	}

	

}
