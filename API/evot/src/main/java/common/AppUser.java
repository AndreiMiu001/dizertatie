package common;

public class AppUser {
	private String cnp;
	private String password;
		
	
	
	public AppUser(String cnp, String password) {
		super();
		this.cnp = cnp;
		this.password = password;
	}
	
	public AppUser() {
		cnp = "";
		password = "";
	}



	public String getCnp() {
		return cnp;
	}



	public void setCnp(String cnp) {
		this.cnp = cnp;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String toString() {
		return "Name = " + cnp + "; Password = " + password;
	}

	public boolean checkValues() {
		if (cnp.isEmpty() || password.isEmpty()) {
			return false;
		}
		return true;
	}

}
