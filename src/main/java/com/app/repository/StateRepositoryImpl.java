package com.app.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.app.customException.DistrictNotFoundException;
import com.app.customException.StateNotFoundException;
import com.app.model.DistrictModel;
import com.app.model.StateModel;

public class StateRepositoryImpl extends DBSTATE implements StateRepository {

	List<StateModel> list = new ArrayList<>();
	

	@Override
	public boolean isAddNewState(StateModel model) {
		try {
			stmt = conn.prepareStatement("INSERT into statemaster values('0',?)");

			stmt.setString(1, model.getStateName());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;

		} catch (Exception e) {
			System.out.println("Error is " + e);
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Optional<List<StateModel>> getAllStates() {

		List<StateModel> list = new ArrayList<>();
		// System.out.println("StateRepositoryImpl.getAllStates()");

		try {
			stmt = conn.prepareStatement("SELECT * from Statemaster");
			rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(new StateModel(rs.getInt(1), rs.getString(2)));
			}

			return list.isEmpty() ? Optional.empty() : Optional.of(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

	@Override
	public StateModel getStateByName(String stateName) {
		try {
			stmt = conn.prepareStatement(Query.getStateByName);
			stmt.setString(1, stateName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new StateModel(rs.getInt(1), rs.getString(2));
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public StateModel deleteStateById(int stateId) {

		try {
			stmt = conn.prepareStatement(Query.deleteStateById);
			stmt.setInt(1, stateId);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				return new StateModel(stateId, "Deleted State");

			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateStateByName(String currStateName, String newStateName) {
		try {
			stmt = conn.prepareStatement(Query.updateStateByName);
			stmt.setString(1, newStateName);
			stmt.setString(2, currStateName);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int getStateIdByName(String stateName) {
		try {
			stmt = conn.prepareStatement("Select stateId from statemaster where stateName = ?");
			stmt.setString(1, stateName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


/////////
}
