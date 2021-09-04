package login;

import common.Implementation;
import common.BCryptWrapper;
import common.Pair;
import common.UserBean;
import insert.InsertDataImpl;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<Pair<Integer, String>> getCities() {
        ArrayList<Pair<Integer, String>> cityArr = null;
        try {
            mDao.connect();
            cityArr = mDao.getCities();
            mDao.disconnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InsertDataImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cityArr;
    }

    public ArrayList<Pair<Integer, String>> getCounties() {
        ArrayList<Pair<Integer, String>> countyArr = null;
        try {
            mDao.connect();
            countyArr = mDao.geteCounties();
            mDao.disconnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InsertDataImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return countyArr;
    }
}
