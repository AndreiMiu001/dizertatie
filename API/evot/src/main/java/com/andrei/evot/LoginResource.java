package com.andrei.evot;

import java.sql.SQLException;
import java.util.ArrayList;

import common.AppUser;
import common.MyBoolean;
import dao.DaoLogin;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("login")
public class LoginResource {
	
	@GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<AppUser> getAppUser() {
    	DaoLogin dao = new DaoLogin();
    	ArrayList<AppUser> userList = null;
    	try {
			dao.connect();
			userList = dao.getAllUsers();
	    	dao.disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
    	return userList;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyBoolean loginAPI(AppUser user) {
    	DaoLogin dao = new DaoLogin();
    	boolean state = false;
    	try {
			dao.connect();
			state = dao.login(user);
			dao.disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			state = false;
			e.printStackTrace();
		}
    	MyBoolean myState = new MyBoolean(state);
    	return myState;
    }
}
