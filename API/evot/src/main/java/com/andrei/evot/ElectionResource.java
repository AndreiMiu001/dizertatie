package com.andrei.evot;

import java.sql.SQLException;
import java.util.ArrayList;

import common.Candidate;
import common.Election;
import dao.DaoElection;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("elections")
public class ElectionResource {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Election> getElections() {
		DaoElection dao = new DaoElection();
		ArrayList<Election> electionList = null;
		try {
			dao.connect();
			electionList = dao.getElections();
			dao.disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return candidateList;
	}

}
