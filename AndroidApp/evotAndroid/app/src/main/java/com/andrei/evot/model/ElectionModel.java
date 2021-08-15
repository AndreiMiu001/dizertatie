package com.andrei.evot.model;

import java.io.Serializable;
import java.time.LocalDate;

public class ElectionModel implements Serializable {

    private String electionName;
    private int idElection;
    private int idVotedCandidate;
    private int idWinnerCandidate;
    private String startingDate;
    private String endingDate;

    public ElectionModel() {
        electionName = "";
        idElection = 0;
        idVotedCandidate = 0;
        idWinnerCandidate = 0;
        endingDate = null;
        startingDate = null;
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

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public String toString() {
        return "Name: " + electionName + " id: " + idElection + " idC: " + idVotedCandidate;
    }
}
