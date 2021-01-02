/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.ElectionBean;
import common.Implementation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrei
 */
public class ViewElectionsImpl extends Implementation {
    
    public ViewElectionsImpl() {}
    
    
    
    public ArrayList<ElectionBean> getAllElections() {
        ArrayList<ElectionBean> electionsArray = new ArrayList<>();
        try {
            mDao.connect();
            electionsArray = mDao.getElections();
            mDao.disconnect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewElectionsImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ViewElectionsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return electionsArray;
    }
    
    public void getUpcomingElections() {
        
    }
    
    public void getPastElections() {
        
    }
    
    public void getElectionsByDate() {
        
    }
}
