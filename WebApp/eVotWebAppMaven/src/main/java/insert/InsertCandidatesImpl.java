/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insert;

import common.ElectionBean;
import common.Implementation;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrei
 */
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
