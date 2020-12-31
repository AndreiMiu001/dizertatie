/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Andrei
 */
public class DAO {
    private Connection con = null;
    private final String connectionDatabaseName = "jdbc:mysql://127.0.0.1:3306/evot2";
    private final String connectionUserName = "root";
    private final String connectionPassword = "admin";
    
    public void connect() throws ClassNotFoundException, SQLException {
        if (con == null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connectionDatabaseName, connectionUserName, connectionPassword);
            if (con != null) {
                con.setAutoCommit(false);
            }
        }
    }

    public void disconnect() throws SQLException {
        if (con != null) {
            con.commit();
            con.close();
        }
    }
        
    public void disconnect(boolean errState) throws SQLException {
        if (con != null && errState == true) {
            con.commit();
            con.close();
        } else if (errState == false) {
            con.rollback();
            con.close();
        }
    }
    
}
