package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.AppUser;
import common.Candidate;
import common.Election;
import common.Vote;

public class DaoElection extends DAO {

	public ArrayList<Election> getElections() {
		ArrayList<Election> electionsList = new ArrayList<>();
		String query = "SELECT * FROM `elections` WHERE current_date() BETWEEN `startDate` AND `endDate`";
		PreparedStatement ps;
		try {
			ps = mConnection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Election elTemp = new Election();
				elTemp.setElectionName(rs.getString("nameElections"));
				elTemp.setIdElection(rs.getInt("idElections"));
				electionsList.add(elTemp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return electionsList;
	}
	
	public ArrayList<Integer> getVotedElections(AppUser user) {
		String query = "SELECT `idElections` FROM `votes` WHERE `cnp`=?";
		ArrayList<Integer> idList = new ArrayList<>();
		PreparedStatement ps;
		try {
			ps = mConnection.prepareStatement(query);
			ps.setString(1, user.getCnp());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				idList.add(rs.getInt("idElections"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idList;
	}

	public ArrayList<Candidate> getCandidates(Election election) {
		ArrayList<Candidate> candidateList = new ArrayList<>();
		String query = "SELECT * FROM `candidates` WHERE `idElections`=?";
		PreparedStatement ps;
		try {
			ps = mConnection.prepareStatement(query);
			ps.setInt(1, election.getIdElection());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Candidate tempCandidate = new Candidate();
				tempCandidate.name = rs.getString("nameCandidates");
				tempCandidate.description = rs.getString("description");
				tempCandidate.id = rs.getInt("idCandidates");
				candidateList.add(tempCandidate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return candidateList;
	}

	public void voteCandidate(Vote vote) {
		String query = "INSERT INTO `votes` (`CNP`, `idElections`, `idCandidates`) VALUES (?, ?, ?);";
		PreparedStatement ps;
		try {
			ps = mConnection.prepareStatement(query);
			ps.setString(1, vote.cnp);
			ps.setInt(2, vote.election.getIdElection());
			ps.setInt(3, vote.candidate.id);
			int state = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
