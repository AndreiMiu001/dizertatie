package model;

public class ElectionModel {
    private String electionName;
    private int idElection;

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
