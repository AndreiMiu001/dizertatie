package com.andrei.evot.model;

import java.io.Serializable;

public class Person implements Serializable  {
    private String cnp;
    private String password;

    public Person(String mCnp, String mPassword) {
        this.cnp = mCnp;
        this.password = mPassword;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String mCnp) {
        this.cnp = mCnp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String mPassword) {
        this.password = mPassword;
    }
}
