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

public class DistrictRepositoryImpl extends DBSTATE implements DistrictRepository {

	List<DistrictModel> listDist = new ArrayList<>();
	
	@Override
	public boolean isAddNewDistByState(String stateName, String distName) {

		try {
			CallableStatement cstmt = conn.prepareCall("{call state_dist_join_procd(?,?)}");
			cstmt.setString(1, stateName);
			cstmt.setString(2, distName);

			boolean executed = cstmt.execute();

			return !executed;

		} catch (Exception e) {
			System.out.println("Error occur while adding new district to state" + e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean isAddBulkDist(String stateName) {
		try {

			boolean b = false;
			BufferedReader br = new BufferedReader(
					new FileReader("D:\\workspaces\\workspace\\MaharashtraDistricts.txt"));
			String distName;
			while ((distName = br.readLine()) != null) {
				CallableStatement cstmt = conn.prepareCall("{call state_dist_join_procd(?,?)}");
				cstmt.setString(1, stateName);
				cstmt.setString(2, distName);
				b = cstmt.execute();
			}
			return !b;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Optional<List<DistrictModel>> getAllDistrictByState(String stateName) {

//		Optional<List<DistrictModel>> list=null;
		try {
			stmt = conn.prepareStatement(Query.getDistByStateName);
			stmt.setString(1, stateName);
			rs = stmt.executeQuery();
			
			listDist.clear();
			while (rs.next()) {
				DistrictModel model = new DistrictModel(rs.getInt(1),rs.getString(2));
				this.listDist.add(model);
//				int distId = rs.getInt("distId");
//				String distName = rs.getString("distName");
//				listDist.add(new DistrictModel(distId, distName));
			}
//			return listDist.isEmpty() ? Optional.empty() : Optional.of(listDist);
			if (this.listDist.size()>0) {
				return Optional.of(listDist);
			} else {
				throw new StateNotFoundException("State not found : "+stateName);
			}

		} catch (SQLException e) {
			return Optional.empty();			
		}
		catch (StateNotFoundException se) {
			System.out.println(se.getMessage());
			return Optional.empty();
		}

		
	}

	@Override
	public boolean updateDistNameByStateName(String stateName, String currDistName, String newDistName) {

		try {
			stmt = conn.prepareStatement(Query.updateDistNameByState);
			stmt.setString(1, newDistName);
			stmt.setString(2, stateName);
			stmt.setString(3, currDistName);
			int isDistupdated = stmt.executeUpdate();
			return isDistupdated > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteDistNameByState(String stateName, String distName) throws DistrictNotFoundException {
		try {
			stmt = conn.prepareStatement(Query.deleteDistrictNameByState);
			stmt.setString(1, stateName);
			stmt.setString(2, distName);

			int rowsAffected = stmt.executeUpdate();

			if (rowsAffected == 0) {
				throw new DistrictNotFoundException(
						"District '" + distName + "' not found in state '" + stateName + "'.");
			}

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	@Override
	public int getDistIdByDistName(String distName) {
		try {
			stmt = conn.prepareStatement("SELECT distId from DISTRICTMASTER where distName = ?");
			stmt.setString(1, distName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	
}
