package com.andrei.evot;

public class User {
    private static String cnp_;
    private static String numberUser_;
    private static String seriesUser_;
    private static String idElection_ = "";

    public static String getIdElection_() {
        return idElection_;
    }

    public static void setIdElection_(String idElection_) {
        User.idElection_ = idElection_;
    }

    public static String getCnp() {
        return cnp_;
    }

    public static void setCnp(String cnp_) {
        User.cnp_ = cnp_;
    }

    public static String getNumberPers() {
        return numberUser_;
    }

    public static void setNumberPers(String numberPers_) {
        User.numberUser_ = numberPers_;
    }

    public static String getSeriesPers() {
        return seriesUser_;
    }

    public static void setSeriesPers(String seriesPers_) {
        User.seriesUser_ = seriesPers_;
    }
}