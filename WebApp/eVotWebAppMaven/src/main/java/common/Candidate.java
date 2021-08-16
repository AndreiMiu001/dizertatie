/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author Andrei
 */
public class Candidate implements Comparable<Candidate>{

    private String mCandidateName;
    private String mPoliticalParty;
    private String mDescription;
    private int mIdCandidate;
    private int mIdElection;
    private int mVotesCount;

    public Candidate() {
        mCandidateName = "";
        mPoliticalParty = "";
        mDescription = "";
        mIdCandidate = 0;
        mIdElection = 0;
        mVotesCount = 0;
    }

    public Candidate(String name, int votes) {
        super();
        mCandidateName = name;
        mVotesCount = votes;
    }

    public Candidate(String name, String description) {
        super();
        mCandidateName = name;
        mDescription = description;
    }

    public int getVotesCount() {
        return mVotesCount;
    }

    public void setVotesCount(int mVotesCount) {
        this.mVotesCount = mVotesCount;
    }

    public String getCandidateName() {
        return mCandidateName;
    }

    public void setCandidateName(String mName) {
        this.mCandidateName = mName;
    }

    public String getPoliticalParty() {
        return mPoliticalParty;
    }

    public void setPoliticalParty(String mPoliticalParty) {
        this.mPoliticalParty = mPoliticalParty;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getIdCandidate() {
        return mIdCandidate;
    }

    public void setIdCandidate(int idCandidate) {
        this.mIdCandidate = idCandidate;
    }

    public int getIdElection() {
        return mIdElection;
    }

    public void setIdElection(int idElection) {
        this.mIdElection = idElection;
    }

    @Override
    public int compareTo(Candidate o) {
        return (this.mVotesCount < o.getVotesCount()) ? -1 : 
                (this.mVotesCount == o.getVotesCount() ? 0 : 1);
    }

}
