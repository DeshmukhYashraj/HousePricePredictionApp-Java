package com.app.repository;

import java.util.List;

import com.app.model.AminityModel;
import com.app.model.PropertyModel;
import com.app.model.WardModel;

public class PropertyRepositoryImpl extends DBSTATE implements PropertyRepository{
	
	public int getPropIdByName(String propName) {
		int propId = 0;
		try {
			stmt = conn.prepareStatement("Select pid from propertymaster where pname = ?");
			stmt.setString(1, propName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}
		} catch (Exception e) {
			System.out.println("Error is "+e);
			return -1;
		}
	}

	@Override
	public boolean isAddNewProperty(PropertyModel model) {
	    WardModel wardModel = model.getWModel();
	    int wardId = wardModel.getWardId();
	    String propName = model.getPropName();
	    String propAddress = model.getPropAddress();
	    int sqFeetArea = model.getSqFeetArea();
	    double propPrice = model.getPropPrice();

	    try {
	        stmt = conn.prepareStatement("INSERT INTO propertymaster (wardid, pname, propAddress, sqFeetArea, propPrice) VALUES (?, ?, ?, ?, ?)");
	        stmt.setInt(1, wardId);
	        stmt.setString(2, propName);
	        stmt.setString(3, propAddress);
	        stmt.setInt(4, sqFeetArea);
	        stmt.setDouble(5, propPrice);
	        int value = stmt.executeUpdate();

	        boolean flag = false;
	        if (value > 0) {
	            List<AminityModel> aminiList = model.getList();
	            int pid = this.getPropIdByName(propName);
	            if (pid != -1) {
	                for (AminityModel aminityModel : aminiList) {
	                    stmt = conn.prepareStatement("Insert into aminitymaster values (?,?,?)");
	                    stmt.setInt(1, aminityModel.getAminityId());
	                    stmt.setInt(2, pid);
	                    stmt.setInt(3, aminityModel.getAmPrice());
	                    value = stmt.executeUpdate();

	                    if (value > 0) {
	                        flag = true;
	                    }

	                    propPrice = propPrice + aminityModel.getAmPrice();
	                }

	                // Update total property price
	                stmt = conn.prepareStatement("UPDATE propertymaster SET propPrice = ? WHERE pid = ?");
	                stmt.setDouble(1, propPrice);
	                stmt.setInt(2, pid);
	                stmt.executeUpdate();
	            } else {
	                return false;
	            }
	        }

	        System.out.println("\nTotal property price is $" + propPrice);
	        return flag;

	    } catch (Exception e) {
	        System.out.println("Error is " + e);
	        return false;
	    }
	}


}
