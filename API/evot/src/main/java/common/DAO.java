package common;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO {
    private final String mConnectionDatabaseName = "jdbc:mysql://127.0.0.1:3306/evot2";
    private final String mConnectionUserName = "root";
    private final String mConnectionPassword = "admin";
    private Connection mConnection = null;
	
    public void connect() throws ClassNotFoundException, SQLException {
        if (mConnection == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            mConnection = DriverManager.getConnection(mConnectionDatabaseName,
                    mConnectionUserName, mConnectionPassword);
            if (mConnection != null) {
                mConnection.setAutoCommit(false);
            }
        }
    }

    public void disconnect() throws SQLException {
        if (mConnection != null) {
            mConnection.commit();
            mConnection.close();
            mConnection = null;
        }
    }

    public void disconnect(boolean errState) throws SQLException {
        if (mConnection != null && errState == true) {
            mConnection.commit();
            mConnection.close();
        } else if (errState == false) {
            mConnection.rollback();
            mConnection.close();
        }
    }
    
    public ArrayList<AppUser> getAllUsers() {
    	String query = "SELECT * FROM `users`";
        ArrayList<AppUser> userList = new ArrayList<>();
    	try {
            PreparedStatement ps = mConnection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	AppUser tempUser =  new AppUser();
            	tempUser.setName(rs.getString("username"));
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
    		ps.setString(1, user.getName());
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
            	tempUser.setName(rs.getString("cnp"));
            	tempUser.setPassword(rs.getString("password"));
            	state = tempUser.checkValues();
            }
    	}   catch (SQLException ex) {
            ex.printStackTrace();
        }
    	return state;
    }
}
