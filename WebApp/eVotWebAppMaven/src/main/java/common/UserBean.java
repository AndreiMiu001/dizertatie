package common;

import java.io.Serializable;

public class UserBean implements Serializable {

    private String mUsername;
    private String mPassword;
    private String mPasswordHash;

    private int mPrivilege;
    private int mIdUser;

    public UserBean(String mUsername, String mPassword, int mPrivilege, int mIdUser) {
        this.mUsername = mUsername;
        this.mPassword = mPassword;
        this.mPrivilege = mPrivilege;
        this.mIdUser = mIdUser;
    }

    public UserBean(String username, String password, String passwordHash) {
        this.mUsername = username;
        this.mPassword = password;
        this.mPasswordHash = passwordHash;
    }

    public UserBean(String username, String password) {
        this.mUsername = username;
        this.mPassword = password;
    }

    public UserBean() {
    }

    public String getPasswordHash() {
        return mPasswordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.mPasswordHash = passwordHash;
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
