/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import org.mindrot.jbcrypt.*;

public class BCryptWrapper {
    private final int logRounds;

    public BCryptWrapper(int logRounds) {
        this.logRounds = logRounds;
    }

    public String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
    }
    
    public String hash(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
