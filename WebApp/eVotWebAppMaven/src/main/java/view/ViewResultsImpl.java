/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.ElectionResultsBean;
import common.Implementation;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrei
 */
public class ViewResultsImpl extends Implementation {
    
    public ElectionResultsBean getVoteResults(int idElection) {
        ElectionResultsBean election = null;
        try {
            mDao.connect();
            election = mDao.getElectionResults(idElection);
            mDao.disconnect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewResultsImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ViewResultsImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return election;
    }
}
