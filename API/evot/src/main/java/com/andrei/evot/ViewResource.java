package com.andrei.evot;

import java.sql.SQLException;
import java.util.ArrayList;

import common.AppUser;
import common.Election;
import dao.DaoView;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("view")
public class ViewResource {

	@POST
	@Path("elections")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Election> getElectionsWhereUserVoted(ArrayList<AppUser> user) {
		DaoView dao = new DaoView();
		ArrayList<Election> electionList = null;
		try {
			dao.connect();
			electionList = dao.viewElectionsWhereUserVoted(user.get(0));
			dao.disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return electionList;
	}

	@POST
	@Path("upcoming")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Election> getUpcomingElections(ArrayList<AppUser> user) {
		DaoView dao = new DaoView();
		ArrayList<Election> electionList = null;
		try {
			dao.connect();
			electionList = dao.getUpcomingElections(user.get(0));
			dao.disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return electionList;
	}
	
	/*
	@GET
	@Path("up")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Election> getUpcomingElections2() {
		DaoView dao = new DaoView();
		ArrayList<Election> electionList = null;
		try {
			dao.connect();
			electionList = dao.getUpcomingElections(null);
			dao.disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return electionList;
	}
	*/
	
}
