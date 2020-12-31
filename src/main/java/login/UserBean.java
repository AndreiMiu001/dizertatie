/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.Serializable;

/**
 *
 * @author Andrei
 */
public class UserBean implements Serializable {
    private String mUsername;
    private String mPassword;
    private int mPrivilege;
    private int mIdUser;

    public UserBean(String mUsername, String mPassword, int mPrivilege, int mIdUser) {
        this.mUsername = mUsername;
        this.mPassword = mPassword;
        this.mPrivilege = mPrivilege;
        this.mIdUser = mIdUser;
    }
    
    public UserBean(String mUsername, String mPassword) {
        this.mUsername = mUsername;
        this.mPassword = mPassword;
    }

    public UserBean() {
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        this.mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public int getPrivilege() {
        return mPrivilege;
    }

    public void setPrivilege(int privilege) {
        this.mPrivilege = privilege;
    }

    public int getIdUser() {
        return mIdUser;
    }

    public void setIdUser(int idUser) {
        this.mIdUser = idUser;
    }
    
    
}
    
   
