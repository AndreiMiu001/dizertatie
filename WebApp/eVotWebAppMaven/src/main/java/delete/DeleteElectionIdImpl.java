package delete;

import common.Implementation;
import insert.InsertCandidatesImpl;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteElectionIdImpl extends Implementation {
    
    public boolean deleteId(int id) {
        boolean status = false;
        try {
            mDao.connect();
            if (mDao.deleteId(id) > 0) {
                status = true;
            }
            mDao.disconnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InsertCandidatesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }   
}
