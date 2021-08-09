/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delete;

import common.Implementation;
import insert.InsertCandidatesImpl;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrei
 */
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
