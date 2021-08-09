package com.example.evot_new2;

public class Person {
    private static String cnp_;
    private static String numberPers_;
    private static String seriesPers_;
    private static String idElection_ = "";

    public static String getIdElection_() {
        return idElection_;
    }

    public static void setIdElection_(String idElection_) {
        Person.idElection_ = idElection_;
    }

    public static String getCnp() {
        return cnp_;
    }

    public static void setCnp(String cnp_) {
        Person.cnp_ = cnp_;
    }

    public static String getNumberPers() {
        return numberPers_;
    }

    public static void setNumberPers(String numberPers_) {
        Person.numberPers_ = numberPers_;
    }

    public static String getSeriesPers() {
        return seriesPers_;
    }

    public static void setSeriesPers(String seriesPers_) {
        Person.seriesPers_ = seriesPers_;
    }
}
