package com.andrei.evot;

import java.sql.SQLException;
import java.util.ArrayList;

import common.AppUser;
import common.Candidate;
import common.Election;
import common.MyBoolean;
import common.Vote;
import dao.DaoElection;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("elections")
public class ElectionResource {
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Election> getElections(ArrayList<AppUser> user) {
		DaoElection dao = new DaoElection();
		ArrayList<Election> electionList = null;
		try {
			dao.connect();
			electionList = dao.getElections();
			removeVotedElections(electionList, user.get(0));
			dao.disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return electionList;
	}
	
	@POST
	@Path("candidates")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Candidate> getCandidates(ArrayList<Election> election) {
		DaoElection dao = new DaoElection();
		ArrayList<Candidate> candidateList = null;
		try {
			dao.connect();
			candidateList = dao.getCandidates(election.get(0));
			dao.disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return candidateList;
	}
	
	@POST
	@Path("vote")
	@Consumes(MediaType.APPLICATION_JSON)
	public MyBoolean voteCandidate(Vote vote) {
		MyBoolean status = new MyBoolean(false);
		DaoElection dao = new DaoElection();
		try {
			dao.connect();
			status.state = true;
			dao.voteCandidate(vote);
			dao.disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	private void removeVotedElections(ArrayList<Election> electionList, AppUser user) {
		DaoElection dao = new DaoElection();
		ArrayList<Integer> idList = null;
		try {
			dao.connect();
			idList = dao.getVotedElections(user);
			dao.disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		
		if (idList != null) {
			for (int i = 0; i < electionList.size(); i++) {
				if (idList.contains(electionList.get(i).getIdElection())) {
					electionList.remove(i);
					i--;
				}
			}
		}
	}

}
