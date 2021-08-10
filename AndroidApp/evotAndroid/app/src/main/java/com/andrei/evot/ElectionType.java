package com.andrei.evot;

public class ElectionType {
    private String idElection_;
    private String electionName_;

    public String getIdElection() {
        return idElection_;
    }

    public String getElectionName() {
        return electionName_;
    }

    public ElectionType(String idElection_, String electionName_) {
        this.idElection_ = idElection_;
        this.electionName_ = electionName_;
    }
}