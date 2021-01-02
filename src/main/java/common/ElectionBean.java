/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Andrei
 */
public class ElectionBean implements Serializable {
    private Date mStartingDate;
    private Date mEndingDate;
    private String mElectionName;

    private ArrayList<String> mEandidatesNameArray;
    private ArrayList<String> mDescriptionArray;
    private int mIdElection;

    public Date getStartingDate() {
        return mStartingDate;
    }

    public void setStartingDate(Date mStartingDate) {
        this.mStartingDate = mStartingDate;
    }

    public Date getEndingDate() {
        return mEndingDate;
    }

    public void setEndingDate(Date mEndingDate) {
        this.mEndingDate = mEndingDate;
    }

    public String getElectionName() {
        return mElectionName;
    }

    public void setElectionName(String mElectionName) {
        this.mElectionName = mElectionName;
    }

    public ArrayList<String> getEandidatesNameArray() {
        return mEandidatesNameArray;
    }

    public void setEandidatesNameArray(ArrayList<String> mEandidatesNameArray) {
        this.mEandidatesNameArray = mEandidatesNameArray;
    }

    public ArrayList<String> getDescriptionArray() {
        return mDescriptionArray;
    }

    public void setDescriptionArray(ArrayList<String> mDescriptionArray) {
        this.mDescriptionArray = mDescriptionArray;
    }

    public int getIdElection() {
        return mIdElection;
    }

    public void setIdElection(int idElection) {
        this.mIdElection = idElection;
    }
    
    
}
