/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import common.ElectionBean;
import common.UserBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Andrei
 */
public class DAO {

    private Connection mConnection = null;
    private final String mConnectionDatabaseName = "jdbc:mysql://127.0.0.1:3306/evot2";
    private final String mConnectionUserName = "root";
    private final String mConnectionPassword = "admin";

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

    public String getFieldFromUsers(String fieldName, String username) {
        String query = "SELECT `" + fieldName + "` FROM `users` WHERE `username`=?";
        String salt = "";
        try {
            PreparedStatement ps = mConnection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next() == true) {
                salt = rs.getString(fieldName);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return salt;
    }

    public boolean readFromUsers(UserBean user) {
        String query = "SELECT * FROM `users` WHERE `username`=? AND `password`=?";
        boolean userExists = false;
        try {
            PreparedStatement ps = mConnection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ResultSet rs = ps.executeQuery();
            if (rs.next() == true) {
                user.setPrivilege(rs.getShort("privilege"));
                user.setIdUser(rs.getInt("idUsers"));
                userExists = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userExists;
    }
    
    public ArrayList<ElectionBean> getElections() {
        String query = "SELECT * FROM `elections`";
        ArrayList<ElectionBean> electionArray = new ArrayList<>();
        try {
            PreparedStatement ps = mConnection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ElectionBean elTemp = new ElectionBean();
                elTemp.setElectionName(rs.getString("nameElections"));
                elTemp.setIdElection(rs.getInt("idElections"));
                elTemp.setStartingDate(rs.getDate("startDate").toString());
                elTemp.setEndingDate(rs.getDate("endDate").toString());
                electionArray.add(elTemp);
            }
        } catch (SQLException ex) {
        }
        return electionArray;
    }
}
