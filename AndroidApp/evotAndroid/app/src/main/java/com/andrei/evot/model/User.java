package com.andrei.evot.model;

import java.io.Serializable;

public class User implements Serializable {
    public static String mCnp;
    public static String mPassword;
    public static String mIdElection;

    public User() {
        mCnp = "";
        mPassword = "";
        mIdElection = "";
    }

    @Override
    public String toString() {
        return "CNP: " + mCnp + " Password: " + mPassword + " idElection: " + mIdElection;
    }

    public static void clear() {
        mCnp = null;
        mPassword = null;
        mIdElection = null;
    }
}