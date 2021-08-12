package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.andrei.evot.ElectionResource;

import common.AppUser;
import common.Candidate;
import common.Election;
import common.Vote;

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
			// TODO Auto-generated catch block
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
			if (state != 0) {
				System.out.print("vote succesful");
			} else {
				System.out.print("vote NOT succesful");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
