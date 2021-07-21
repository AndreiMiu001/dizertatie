/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package update;

import common.Candidate;
import common.ElectionBean;
import common.Implementation;
import insert.InsertCandidatesImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrei
 */
public class UpdateElectionImpl extends Implementation {

    private ElectionBean election;

    public ElectionBean getElectionForUpdate(int id) {
        try {
            mDao.connect();
            election = mDao.getSingleElection(id);
            mDao.disconnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InsertCandidatesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return election;
    }

    public ArrayList<Candidate> getCandidates(int id) {
        ArrayList<Candidate> candidatesArr = new ArrayList<>();
        try {
            mDao.connect();
            candidatesArr = mDao.getCandidates(id);
            mDao.disconnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InsertCandidatesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return candidatesArr;
    }

    public boolean update(ElectionBean election) {
        try {
            mDao.connect();
            mDao.updateElection(election);
            mDao.disconnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InsertCandidatesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
