package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.Election;

public class DaoElection extends DAO {

	public ArrayList<Election> getElections() {
		ArrayList<Election> electionsList = new ArrayList<>();
		String query = "SELECT * FROM `elections`";
		PreparedStatement ps;
		try {
			ps = mConnection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Election elTemp = new Election();
				elTemp.setElectionName(rs.getString("nameElections"));
				elTemp.setIdElection(rs.getInt("idElections"));
				// elTemp.setStartingDate(rs.getDate("startDate").toString());
				// elTemp.setEndingDate(rs.getDate("endDate").toString());
				// elTemp.setCategory(getSingleElectionCategory(rs.getInt("idElectionType")));
				electionsList.add(elTemp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return electionsList;
	}
}
