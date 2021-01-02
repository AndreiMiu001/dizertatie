/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import common.Implementation;
import common.BCryptWrapper;
import common.UserBean;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;


/**
 *
 * @author Andrei
 */
public class LoginImpl extends Implementation {

    public LoginImpl() {}

    public boolean checkIfUserExists(UserBean user) {
        boolean userExists = false;
        try {
            mDao.connect();
            String salt = mDao.getFieldFromUsers("salt", user.getUsername());
            if (salt.isEmpty()) {
                mDao.disconnect();
                return false;
            }
            BCryptWrapper bCrypt = new BCryptWrapper(11);
            user.setPasswordHash(bCrypt.hash(user.getPassword(), salt));
            userExists = mDao.readFromUsers(user);
            mDao.disconnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userExists;
    }
}
