package com.andrei.evot.model;

import java.io.Serializable;

public class VoteModel implements Serializable {
    private ElectionModel election;
    private CandidateModel candidate;
    private String cnp;

    public ElectionModel getElection() {
        return election;
    }

    public void setElection(ElectionModel election) {
        this.election = election;
    }

    public CandidateModel getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateModel candidate) {
        this.candidate = candidate;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }
}
