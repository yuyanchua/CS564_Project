package application;

public class Account {
	String username;
	String password;
	int userId;
	
	public Account(String username, String password, int userId) {
		this.username = username;
		this.password = password;
		this.userId  = userId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getUserId() {
		return userId;
	}
}
