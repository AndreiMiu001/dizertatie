package com.andrei.evot.model;

import java.io.Serializable;

public class User implements Serializable {
    public static String mCnp;
    public static String mPassword;
    public static String mIdElection = "";

    @Override
    public String toString() {
        return "CNP: " + mCnp + " Password: " + mPassword + " idElection: " + mIdElection;
    }
}