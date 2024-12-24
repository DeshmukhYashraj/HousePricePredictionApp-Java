package com.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.model.AminityModel;
import com.app.model.WardModel;

public class AminityRepositoryImpl extends DBSTATE implements AminityRepository {

	@Override
	public boolean isAddNewAminity(AminityModel model) {
		try {
			stmt = conn.prepareStatement(Query.addAminity);
			stmt.setString(1, model.getAminityName());

			int isAdded = stmt.executeUpdate();

			return isAdded > 0;

		} catch (Exception e) {
			System.out.println("Error is " + e);
		}
		return false;
	}

	@Override
	public List<AminityModel> getAllAminities() {
		List<AminityModel> amiList = new ArrayList<AminityModel>();
		try {
			stmt = conn.prepareStatement("select *from amenitiesmaster order by amId");
			rs = stmt.executeQuery();
			while(rs.next()) {
				AminityModel amiModel = new AminityModel();
				amiModel.setAminityId(rs.getInt(1));
				amiModel.setAminityName(rs.getString(2));
				amiList.add(amiModel);
			}
			return amiList;
		} catch (Exception e) {
			System.out.println("Error is "+e.getMessage());
		}
		return null;
	}

	@Override
	public boolean updateAminityNameById(String amiName, int amiId) {
		try {
			stmt = conn.prepareStatement(" Update amenitiesmaster SET amitName = ? where amid = ?");
			stmt.setString(1, amiName);
			stmt.setInt(2, amiId);
			int isUpdated = stmt.executeUpdate();
			return isUpdated > 0;
		} catch (Exception e) {
			System.out.println("Error is "+e.getMessage());
		}
		return false;
	}

	@Override
	public boolean deleteAminityName(int amiId) {
		try {
			stmt = conn.prepareStatement("DELETE from amenitiesmaster where amid = ?;");
			stmt.setInt(1, amiId);
			int isDeleted = stmt.executeUpdate();
			return isDeleted > 0;
		} catch (Exception e) {
			System.out.println("Error is "+e.getMessage());
		}
		return false;
	}


}
