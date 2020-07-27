//v2
package application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {
	
	String databaseName = "moviedb";
	String user = "root";
	String host = "localhost";
	String databaseUrl = "jdbc:mysql://" + host + "/" + databaseName + "?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";	
	//jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
	String password = "";
	
	Connection connection = null;
	PreparedStatement statement = null; //with parameter
	private Statement state = null; // for without parameter
	ResultSet resultSet = null;
	
	public Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("databaseURL     "+ databaseUrl);
			connection = DriverManager.getConnection(databaseUrl, user, password);
			System.out.println("Successfully connected to the database");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Account retrieveAccount(String username) {
		String sqlQuery = "SELECT * FROM account a WHERE a.username = ?; ";

		try {
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, username);
			resultSet = statement.executeQuery();

			if (resultSet.next() == false) {
				return null;
			  } else {
				String password =null;
				int user_Id  = 0;  
				password = resultSet.getString("password");
				user_Id = resultSet.getInt("user_Id");
				Account a = new Account(username, password, user_Id);
				return a;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getMaxUserId() {
		String sqlQuery = "SELECT MAX(user_ID) FROM account; ";

		try {
			state = connection.createStatement();
	    	resultSet = state.executeQuery(sqlQuery);

			if (resultSet.next() == false) {
				return -1;
			  } else {
				  return resultSet.getInt(1);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//insert account
	//The executeUpdate() method returns the number of rows affected by the SQL statement
	public int createAccount(Account account) throws SQLException{
		try{
			CallableStatement cStmt = connection.prepareCall("{call insertAccountTable (?, ?, ?)}");
			cStmt.setString(1, account.getUsername());
			cStmt.setString(2, account.getPassword());
			cStmt.setInt(3, account.getUserId());

			int rowAffected = cStmt.executeUpdate();
			return rowAffected;
		}catch (SQLException e) {
			throw new SQLException(); 
		}
	}

	//delete own account
	public int deleteAccount(Account account) {
		try{
			CallableStatement cStmt = connection.prepareCall("{call deleteAccountTable (?)}");
			cStmt.setString(1, account.getUsername());

			int rowAffected = cStmt.executeUpdate();
			return rowAffected;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int updatePassword(Account account) {
		try{
			CallableStatement cStmt = connection.prepareCall("{call updateAccountTable (?, ?)}");
			cStmt.setString(1, account.getUsername());
			cStmt.setString(2, account.getPassword());

			int rowAffected = cStmt.executeUpdate();
			return rowAffected;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int insertRating(Rate rating) throws SQLException {
		
		try{
			CallableStatement cStmt = connection.prepareCall("{call insertRateTable (?, ?, ?)}");
			cStmt.setInt(1, rating.getMovieId());
			cStmt.setInt(2, rating.getUserId());
			cStmt.setDouble(3, rating.getRating());

			int rowAffected = cStmt.executeUpdate();
			return rowAffected;
		}catch (SQLException e) {
			throw new SQLException(); 
		}
	}
	
	public Movie retrieveMovie(String movieName) {
		Movie movieResult = null;
		String sqlQuery = "SELECT * FROM movie m WHERE m.title = ?;";
		try {
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, movieName);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int movieId = resultSet.getInt(1);
				String title = resultSet.getString(2);
				int year = resultSet.getInt(3);
				String country = resultSet.getString(4);
				double RTCriticsRating = resultSet.getDouble(5);
				double RTAudienceRating = resultSet.getDouble(6);
				int RTAudRateNum = resultSet.getInt(7);
				movieResult = new Movie(movieId, title, year, country, RTCriticsRating, RTAudienceRating, RTAudRateNum);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return movieResult;
	}
	
	public List<String> retrieveMovie(String name, boolean isActor){
		List<String> list = new ArrayList<>();
		
		if(isActor) {
			//search movie by actor
			String sqlQuery2 = "SELECT *"+
			" FROM movie m"+
			" WHERE m.movie_ID IN"+
			" (SELECT p.movie_ID FROM act p WHERE p.actor_ID ="+
			" (SELECT a.actor_ID FROM actor a WHERE a.actor_name = ?)); ";

			try {
				statement = connection.prepareStatement(sqlQuery2);
				statement.setString(1, name);
				resultSet = statement.executeQuery();
	
				while (resultSet.next()) {
					String title = resultSet.getString("title");
	    			list.add(title);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			//search movie by director
			String sqlQuery3 = "SELECT *"+
			" FROM movie m"+
			" WHERE m.movie_ID IN"+
			" (SELECT r.movie_ID FROM direct r WHERE r.director_ID ="+
			" (SELECT d.director_ID FROM director d WHERE d.director_name = ?)); ";

			try {
				statement = connection.prepareStatement(sqlQuery3);
				statement.setString(1, name);
				resultSet = statement.executeQuery();
	
				while (resultSet.next()) {
					String title = resultSet.getString("title");
					list.add(title);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	public double retrieveRating(Movie movie) {
		double aveRate = -1;
		String sqlQuery = "SELECT r.movie_id, AVG(r.rating) AS AverageRating  FROM rating r GROUP BY movie_id having movie_id IN (SELECT m.movie_ID FROM movie m WHERE m.title = ?);";

		try {
			statement = connection.prepareStatement(sqlQuery);
			System.out.println(movie.getTitle());
			statement.setString(1, movie.getTitle());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				aveRate = resultSet.getDouble("AverageRating");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return aveRate;
	}
	
	public List<String> retrieveActor(String movieName){
		List<String> actorName = new ArrayList<String>();
		String sqlQuery = "SELECT a.actor_name"+
		" FROM actor a"+
		" WHERE a.actor_ID IN"+
		" (SELECT p.actor_ID FROM act p WHERE p.movie_ID IN"+
		" (SELECT m.movie_ID FROM movie m WHERE m.title = ?)); ";
		
		try {
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, movieName);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				actorName.add(resultSet.getString("actor_name"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return actorName;
	}
	
	public String retrieveRanking(String movieName){
		String actorName = null;
		String sqlQuery = "SELECT a.actor_name"+
		" FROM actor a"+
		" WHERE a.actor_ID ="+
		" (SELECT c.actor_ID FROM act c WHERE c.movie_ID IN (SELECT m.movie_ID FROM movie m WHERE m.title = ?)"+
		" ORDER BY c.ranking DESC"+
		" LIMIT 1); ";

		try {
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, movieName);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				actorName = resultSet.getString(1);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return actorName;
	}
	
	public List<String> retrieveDirector(String movieName) {
		List<String> directorName = new ArrayList<String>();
		String sqlQuery = "SELECT d.director_name"+
		" FROM director d"+ 
		" WHERE d.director_ID IN"+ 
		" (SELECT r.director_ID FROM direct r WHERE r.movie_ID IN"+ 
		" (SELECT m.movie_ID FROM movie m WHERE m.title = ?)); ";
		
		try {
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, movieName);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				directorName.add(resultSet.getString("director_name"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return directorName;
	}
}
