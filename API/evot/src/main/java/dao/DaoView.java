package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.AppUser;
import common.Election;

public class DaoView extends DAO {
	
	public ArrayList<Election> viewElectionsWhereUserVoted(AppUser user) {
		String query = "SELECT `idElections` FROM `votes` WHERE `CNP`=?";
		ArrayList<Election> electionList = new ArrayList<>();
		PreparedStatement ps;
		try {
			ps = mConnection.prepareStatement(query);
			ps.setString(1, user.getCnp());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("idElections");
				electionList.add(getSingleElection(id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return electionList;
	}

	private Election getSingleElection(int id) {
		String query = "SELECT * FROM `elections` WHERE `idElections`=?";
		Election election = new Election();
		PreparedStatement ps;
		try {
			ps = mConnection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				election.setIdElection(id);
				election.setElectionName(rs.getString("nameElections"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return election;
	}

}
