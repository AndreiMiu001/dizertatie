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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrei
 */
public class UpdateElectionImpl extends Implementation {

    private ElectionBean election;

    public ElectionBean getSingleElectionForUpdate(int id) {
        try {
            mDao.connect();
            election = mDao.getSingleElection(id);
            mDao.disconnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InsertCandidatesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (election.getCategory().getId()) {
            case 3:
                election.isLocal = true;
                break;
            case 2:
                election.isCounty = true;
                break;
            default:
                election.isNational = true;
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
            mDao.updateCandidates(election);
            mDao.disconnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InsertCandidatesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean updateWithInsertOrDelete(ElectionBean election) {
        try {
            mDao.connect();
            mDao.updateElection(election);
            mDao.deleteCandidates(election.getIdElection());
            mDao.insertCandidates(election.getCandidates(), election.getIdElection());
            mDao.disconnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InsertCandidatesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public ArrayList<ElectionBean> getElectionsForUpdate() {
        ArrayList<ElectionBean> electionsArray = new ArrayList<>();
        try {
            mDao.connect();
            electionsArray = mDao.getElectionsForUpdate();
            mDao.disconnect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateElectionImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateElectionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return electionsArray;
    }
}
