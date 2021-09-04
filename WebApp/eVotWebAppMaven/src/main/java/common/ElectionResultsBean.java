package common;

import java.io.Serializable;
import java.util.ArrayList;

public class ElectionResultsBean implements Serializable {

    protected String mElectionName;
    protected int mIdElection;
    protected ArrayList<Candidate> mCandidatesArray;
    
    public ElectionResultsBean() {
        mElectionName = "";
        mIdElection = 0;
        mCandidatesArray  = new ArrayList<>();
    }

    public String getElectionName() {
        return mElectionName;
    }

    public void setElectionName(String mElectionName) {
        this.mElectionName = mElectionName;
    }

    public int getIdElection() {
        return mIdElection;
    }

    public void setIdElection(int idElection) {
        this.mIdElection = idElection;
    }

    public void setCandidatesArray(ArrayList<Candidate> candidatesArr) {
        this.mCandidatesArray = candidatesArr;
    }

    public void addCandidate(Candidate candidate) {
        mCandidatesArray.add(candidate);
    }

    public ArrayList<Candidate> getCandidates() {
        return mCandidatesArray;
    }
}
