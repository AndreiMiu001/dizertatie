package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.AppUser;
import common.Election;

public class DaoView extends DAO {
	
	public ArrayList<Election> viewElectionsWhereUserVoted(AppUser user) {
		String query = "SELECT `idElections`, `idCandidates` FROM `votes` WHERE `CNP`=?";
		ArrayList<Election> electionList = new ArrayList<>();
		PreparedStatement ps;
		try {
			ps = mConnection.prepareStatement(query);
			ps.setString(1, user.getCnp());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Election election = new Election();
				election.setIdVotedCandidate(rs.getInt("idCandidates"));
				int id = rs.getInt("idElections");
				electionList.add(getSingleElection(id, election));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return electionList;
	}

	private Election getSingleElection(int id, Election election) {
		String query = "SELECT * FROM `elections` WHERE `idElections`=?";
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
