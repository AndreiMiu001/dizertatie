package dao;

import common.AppUser;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DaoLogin extends DAO{
    
    public ArrayList<AppUser> getAllUsers() {
    	String query = "SELECT * FROM `persons`";
        ArrayList<AppUser> userList = new ArrayList<>();
    	try {
            PreparedStatement ps = mConnection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	AppUser tempUser =  new AppUser();
            	tempUser.setCnp(rs.getString("CNP"));
            	tempUser.setPassword(rs.getString("password"));
            	userList.add(tempUser);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    	return userList;
    }
    
    public boolean login(AppUser user) {
    	String query = "SELECT * FROM `persons` WHERE `CNP`=? AND `password`=?";
    	boolean state = false;
    	try {
    		PreparedStatement ps = mConnection.prepareStatement(query);
    		ps.setString(1, user.getCnp());
    		ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            rs.last();
            if (rs.getRow() != 1) {
            	return state;
            } else {
            	rs.beforeFirst();
            }
            while (rs.next()) {
            	AppUser tempUser =  new AppUser();
            	tempUser.setCnp(rs.getString("cnp"));
            	tempUser.setPassword(rs.getString("password"));
            	state = tempUser.checkValues();
            }
    	}   catch (SQLException ex) {
            ex.printStackTrace();
        }
    	return state;
    }
}
