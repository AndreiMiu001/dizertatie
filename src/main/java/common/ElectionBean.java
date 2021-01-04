/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Andrei
 */
public class ElectionBean implements Serializable {

    private String mCategory;
    private String mElectionName;
    private String mLocalitate;
    private String mJudet;
    private LocalDate mStartingDate;
    private LocalDate mEndingDate;

    private ArrayList<Candidate> mCandidatesArray;
    private int mIdElection;

    public ElectionBean() {
        mStartingDate = null;
        mEndingDate = null;
        mElectionName = "";
        mLocalitate = "";
        mJudet = "";
        mCategory = "";
        mIdElection = 0;
        mCandidatesArray = new ArrayList<>();
    }

    public String getLocalitate() {
        return mLocalitate;
    }

    public void setLocalitate(String mLocalitate) {
        this.mLocalitate = mLocalitate;
    }

    public String getJudet() {
        return mJudet;
    }

    public void setJudet(String mJudet) {
        this.mJudet = mJudet;
    }
    
    

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
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

    public LocalDate getStartingDate() {
        return mStartingDate;
    }

    public void setStartingDate(LocalDate mStartingDate) {
        this.mStartingDate = mStartingDate;
    }

    public LocalDate getEndingDate() {
        return mEndingDate;
    }

    public void setEndingDate(LocalDate mEndingDate) {
        this.mEndingDate = mEndingDate;
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

    public void setStartingDate(String date) {
        date = date.replace('/', '-');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        mStartingDate = LocalDate.parse(date, formatter);
    }

    public void setEndingDate(String date) {
        date = date.replace('/', '-');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        mEndingDate = LocalDate.parse(date, formatter);
    }

}
