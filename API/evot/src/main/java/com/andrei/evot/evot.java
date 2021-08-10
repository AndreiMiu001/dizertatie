package com.andrei.evot;

import java.sql.SQLException;
import java.util.ArrayList;

import common.DAO;
import common.MyBoolean;
import common.AppUser;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("evot")
public class evot {
    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<AppUser> getAppUser() {
    	DAO dao = new DAO();
    	ArrayList<AppUser> userList = null;
    	try {
			dao.connect();
			userList = dao.getAllUsers();
	    	dao.disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return userList;
    }
    
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyBoolean loginAPI(AppUser user) {
    	DAO dao = new DAO();
    	boolean state = false;
    	try {
			dao.connect();
			state = dao.login(user);
			dao.disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			state = false;
			e.printStackTrace();
		}
    	System.out.println(user);
    	MyBoolean myState = new MyBoolean(state);
    	return myState;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
}
