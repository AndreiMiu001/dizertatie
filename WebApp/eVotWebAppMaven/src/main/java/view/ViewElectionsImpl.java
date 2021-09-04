package view;

import common.ElectionBean;
import common.Implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewElectionsImpl extends Implementation {
    
    public ViewElectionsImpl() {}    
    
    public ArrayList<ElectionBean> getAllElections() {
        ArrayList<ElectionBean> electionsArray = new ArrayList<>();
        try {
            mDao.connect();
            electionsArray = mDao.getElections();
            mDao.disconnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ViewElectionsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return electionsArray;
    }
}
