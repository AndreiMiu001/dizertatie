package insert;

import common.ElectionBean;
import common.Implementation;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsertCandidatesImpl extends Implementation {

    public boolean insertElection(ElectionBean election) {
        boolean status = false;
        try {
            mDao.connect();
            status = mDao.insertElection(election);
            mDao.disconnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InsertCandidatesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
}
