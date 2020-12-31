/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import dao.DAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrei
 */
public class LoginImpl {
    private DAO dao;
    
    LoginImpl() {
        dao = new DAO();
    }
    
    public boolean checkIfUserExists(UserBean user) {
        boolean userExists = false;
        try {
            dao.connect();
            userExists = dao.readFromUsers(user);
            dao.disconnect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userExists;
    }
}
