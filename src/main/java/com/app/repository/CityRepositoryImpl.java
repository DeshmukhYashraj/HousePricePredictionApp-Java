package com.app.repository;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.app.customException.StateNotFoundException;
import com.app.model.CityModel;
import com.app.model.DistrictModel;

public class CityRepositoryImpl extends DBSTATE implements CityRepository {

	@Override
	public boolean isAddNewCity(CityModel model) {
		try {
			CallableStatement cstmt = conn.prepareCall("{call saveCity(?,?,?)}");
			cstmt.setString(1, model.getCityName());
			cstmt.setInt(2, model.getStateId());
			cstmt.setInt(3, model.getDistId());

			boolean b = cstmt.execute();
			return !b;

		} catch (Exception e) {
			System.out.println("Error is " + e);
		}
		return false;
	}

	@Override
	public int getCityIdByCityName(String cityName, int stateId, int distId) {
		try {
			stmt = conn.prepareStatement(Query.getCityIdByCityName);
			stmt.setString(1, cityName);
			stmt.setInt(2, stateId);
			stmt.setInt(3, distId);
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

	@Override
	public List<CityModel> showAllCityByDist(String stateName, String distName) {
		List<CityModel> cityList = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(Query.getAllCityNamesByDist);
			stmt.setString(1, stateName);
			stmt.setString(2, distName);

			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				CityModel cm = new CityModel();
				cm.setCityId(result.getInt(1)); // City ID
				cm.setCityName(result.getString(2)); // City Name
				cityList.add(cm);
			}
		} catch (SQLException e) {
			System.err.println("Error fetching city details: " + e.getMessage());
			e.printStackTrace();
		}

		return cityList;
	}


	@Override
	public boolean updateCityNameByDist(String cityName, int cityId) {
		try {
			stmt = conn.prepareStatement(Query.updateCityName);
			stmt.setString(1, cityName);
			stmt.setInt(2, cityId);
			int rowsAffected = stmt.executeUpdate();

			return rowsAffected > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	@Override
	public boolean deleteCityByName(String cityName) {
		try {
			stmt = conn.prepareStatement(Query.deleteCityByName);
			stmt.setString(1, cityName);
			int ifRowsAffected = stmt.executeUpdate();
			
			return ifRowsAffected >0;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
