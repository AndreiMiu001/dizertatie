package common;

public class Election {

    protected String mElectionName;
    protected int mIdElection;
    protected int mIdVotedCandidate;
    protected int mIdWinnerCandidate;
    
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
	
	
}
