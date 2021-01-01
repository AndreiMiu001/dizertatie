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

    private final DAO dao;

    LoginImpl() {
        dao = new DAO();
    }

    public boolean checkIfUserExists(UserBean user) {
        boolean userExists = false;
        try {
            dao.connect();
            String salt = dao.getFieldFromUsers("salt", user.getUsername());
            if (salt.isEmpty()) {
                dao.disconnect();
                return false;
            }
            BCryptWrapper bCrypt = new BCryptWrapper(11);
            user.setPasswordHash(bCrypt.hash(user.getPassword(), salt));
            userExists = dao.readFromUsers(user);
            dao.disconnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userExists;
    }
}
