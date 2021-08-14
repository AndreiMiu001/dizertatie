package com.andrei.evot.model;

import java.io.Serializable;

public class ElectionModel implements Serializable {
    private String electionName;
    private int idElection;
    private int idVotedCandidate;
    private int idWinnerCandidate;

    public ElectionModel() {
        electionName = "";
        idElection = 0;
        idVotedCandidate = 0;
        idWinnerCandidate = 0;
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

    public int getIdVotedCandidate() {
        return idVotedCandidate;
    }

    public void setIdVotedCandidate(int idVotedCandidate) {
        this.idVotedCandidate = idVotedCandidate;
    }

    public int getIdWinnerCandidate() {
        return idWinnerCandidate;
    }

    public void setIdWinnerCandidate(int idWinnerCandidate) {
        this.idWinnerCandidate = idWinnerCandidate;
    }

    public String toString() {
        return "Name: " + electionName + " id: " + idElection + " idC: " + idVotedCandidate;
    }
}
