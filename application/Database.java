package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class Database {
	
	String databaseName = "";
	String user = "";
	String host = "";
	String databaseUrl = "jdbc:mysql://" + host + "/" + databaseName + "?autoReconnect=true&useSSL=false";
	String password = "";
	
	Connection connection = null;
	Statement statement = null;
	
	public Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(databaseUrl, user, password);
			statement = connection.createStatement();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	
	
	}
	
	public Account retrieveAccount(String username) {
		return null;
	}
	
	public int getMaxUserId() {
		return -1;
	}
	
	public boolean createAccount(Account account) {
		return false;
	}
	
	public boolean updatePassword(Account account) {
		return false;
	}
	
	public boolean insertRating(Rate rating) {
		return false;
	}
	
	public Movie retrieveMovie(String name, int retrieveType) {
		switch(retrieveType) {
		case 1:
			//search by Movie
		case 2:
			//search by Actor
		case 3: 
			//search by Director
		}
		return null;
	}
	
	public double retrieveRating(Movie movie) {
		return -1;
	}
	
	public List<Actor> retrieveActor(String movieName){
		return null;
	}
	
	public List<Actor> retrieveRanking(int num, String movieName){
		return null;
	}
	
	public Director retrieveDirector(String movieName) {
		return null;
	}
	
	
	
	
}
