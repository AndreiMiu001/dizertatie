package com.andrei.evot.model;

import java.io.Serializable;

public class ElectionModel implements Serializable {
    private String electionName;
    private int idElection;

    public ElectionModel() {
        electionName = "";
        idElection = 0;
    }

    public String getElectionName() {
        return electionName;
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }

    public int getIdElection() {
        return idElection;
    }

    public void setIdElection(int idElection) {
        this.idElection = idElection;
    }

    public String toString() {
        return "Name: " + electionName + " id: " + idElection;
    }
}
