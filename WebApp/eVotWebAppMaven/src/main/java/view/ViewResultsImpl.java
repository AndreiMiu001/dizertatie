package view;

import common.ElectionBean;
import common.ElectionResultsBean;
import common.Implementation;
import insert.InsertCandidatesImpl;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public ElectionBean getSingleElection(int id) {
        ElectionBean election = null;
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
}
