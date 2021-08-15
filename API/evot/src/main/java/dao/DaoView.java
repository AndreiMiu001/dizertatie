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
				election.setIdWinnerCandidate(getIdWinner(id));
				electionList.add(getSingleElection(id, election));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return electionList;
	}
	
	public ArrayList<Election> getUpcomingElections(AppUser user) {
		String query = "SELECT * FROM `elections` WHERE current_date() < startDate";
		ArrayList<Election> electionList = new ArrayList<>();
		PreparedStatement ps;
		try {
			ps = mConnection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Election election = new Election();
				election.setElectionName(rs.getString("nameElections"));
				election.setIdElection(rs.getInt("idElections"));
				election.setStartingDate(rs.getDate("startDate").toString());
				election.setEndingDate(rs.getDate("endDate").toString());
				electionList.add(election);
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
	
	private int getIdWinner(int idElection) {
		String query = "SELECT `idCandidates`,COUNT(`CNP`) AS `count` FROM `votes` WHERE `idElections`=?"
				+ " GROUP BY `idCandidates` ORDER BY `count` DESC LIMIT 1";
		PreparedStatement ps;
		int idWinnerCandidate = -1;
		try {
			ps = mConnection.prepareStatement(query);
			ps.setInt(1, idElection);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				idWinnerCandidate = rs.getInt("idCandidates");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idWinnerCandidate;
	}
}
