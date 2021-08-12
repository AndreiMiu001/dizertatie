package common;

public class Election {

    protected String mElectionName;
    protected int mIdElection;
    protected int mIdVotedCandidate;
    
    public Election() {
    	mElectionName = "";
    	mIdElection = 0;
    	mIdVotedCandidate = 0;
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
}
