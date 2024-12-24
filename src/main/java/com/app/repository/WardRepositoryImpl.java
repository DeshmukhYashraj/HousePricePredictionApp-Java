package com.app.repository;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

import com.app.model.CityModel;
import com.app.model.WardModel;

public class WardRepositoryImpl extends DBSTATE implements WardRepository{

	@Override
	public boolean isAddNewWard(String cityName, String wardName, int pincode) {
		
		try {
			CallableStatement cstmt = conn.prepareCall("{call wardProcd(?,?,?)}");
			cstmt.setString(1, cityName);
			cstmt.setString(2, wardName);
			cstmt.setInt(3, pincode);
			
			boolean b = cstmt.execute();
			
			return !b;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false ;
	}

	@Override
	public List<WardModel> showAllWardByCity(String cityName) {
		List<WardModel> wardList = new ArrayList<WardModel>();
		try {
			stmt = conn.prepareStatement(Query.showAllWardCityWise);
			stmt.setString(1, cityName);
			rs = stmt.executeQuery();
			while (rs.next()) {
				WardModel wm = new WardModel();
				wm.setWardId(rs.getInt(1));
				wm.setWardName(rs.getString(2));
				wardList.add(wm);	
			}	
		return wardList;
		} catch (Exception e) {
			System.out.println("Error is "+e.getMessage());
		}
		return null;
	}
	

	@Override
	public boolean updateWardName(String newWardName, int wardId) {
	    if (newWardName == null || newWardName.trim().isEmpty()) {
	        System.out.println("Ward name cannot be empty.");
	        return false;
	    }
	    
	    try {
	        stmt = conn.prepareStatement(Query.updateWardName);
	        stmt.setString(1, newWardName);
	        stmt.setInt(2, wardId);
	        int isUpdated = stmt.executeUpdate();
	        return isUpdated > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	@Override
	public boolean deleteWard(int wardId) {
		try {
			stmt = conn.prepareStatement(Query.deleteWardById);
			stmt.setInt(1, wardId);
			int isDeleted = stmt.executeUpdate();
			return isDeleted > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int getwardIdByName(String wardName) {
		try {
			stmt = conn.prepareStatement("Select wardId from wardMaster where wardName = ?");
			stmt.setString(1, wardName);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}

	
	
	
}
