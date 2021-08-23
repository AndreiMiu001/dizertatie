package common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Election {

    protected String mElectionName;
    protected int mIdElection;
    protected int mIdVotedCandidate;
    protected int mIdWinnerCandidate;
    private LocalDate mStartingDate;
    private LocalDate mEndingDate;
    
    public Election() {
    	mElectionName = "";
    	mIdElection = 0;
    	mIdVotedCandidate = 0;
    	mIdWinnerCandidate = 0;
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
	public void setIdElection(int mIdElection) {
		this.mIdElection = mIdElection;
	}
	
	public int getIdVotedCandidate() {
		return mIdVotedCandidate;
	}
	public void setIdVotedCandidate(int idVotedCandidate) {
		this.mIdVotedCandidate = idVotedCandidate;
	}

	public int getIdWinnerCandidate() {
		return mIdWinnerCandidate;
	}

	public void setIdWinnerCandidate(int mIdWinneCandidate) {
		this.mIdWinnerCandidate = mIdWinneCandidate;
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
