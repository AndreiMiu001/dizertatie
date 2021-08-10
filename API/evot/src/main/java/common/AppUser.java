package common;

public class AppUser {
	private String name;
	private String password;
	
	public AppUser(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public AppUser() {
		name = "";
		password = "";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean checkValues() {
		if (name.isEmpty() || password.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public String toString() {
		return "Name = " + name + "; Password = " + password;
	}
}
