package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class Database {
	
	String databaseName = "";
	String user = "";
	String host = "";
	String databaseUrl = "jdbc:mysql://" + host + "/" + databaseName + "?autoReconnect=true&useSSL=false";
	String password = "";
	
	Connection connection = null;
	//Statement statement = null;
	PreparedStatement statement = null; //with parameter
	ResultSet resultSet = null;
	
	public Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("databaseURL"+ databaseURL);
			connection = DriverManager.getConnection(databaseUrl, user, password);
			//statement = connection.createStatement();
			System.out.println("Successfully connected to the database");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//whether the input username is in the dataset and whether it matches with the password
	//if both are yes, return true
	public boolean retrieveAccount(String username, String password) {
		String sqlQuery = "SELECT a.password FROM account a WHERE a.username = ?; ";

		try {
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, username);
			resultSet = prepareStatement.executeQuery();

			if (resultSet.next() == false) {
				return false;
			  } else {
				if (resultSet.getString("password").equals(password)){
					return true;
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getMaxUserId() {
		String sqlQuery = "SELECT MAX(user_ID) FROM account; ";

		try {
			statement = connection.createStatement();
	    	resultSet = statement.executeQuery(sqlQuery);

			if (resultSet.next() == false) {
				return -1;
			  } else {
					return resultSet.getInt("user_ID");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//insert account
	//The executeUpdate() method returns the number of rows affected by the SQL statement
	public int createAccount(Account account) {
		String sp = "delimiter $$"+
		" Drop procedure if exists insertAccountTable"+
		" create procedure insertAccountTable (IN username VARCHAR(255), password VARCHAR(255), user_ID BIGINT)"+
		" begin"+
		" INSERT INTO account (username, password, user_ID)"+
		" VALUES (?, ?, ?);"+
		" end $$"+
		" delimiter;";
		try{
			CallableStatement cStmt = conn.prepareCall("{call sp (?, ?, ?)}");
			cStmt.setString(1, account.getUsername());
			cStmt.setString(2, account.getPassword());
			cStmt.setInt(3, account.getUserId());

			int rowAffected = cStmt.executeUpdate();
			return rowAffected;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	//delete own account
	public int deleteAccount(Account account) {
		String sp = "delimiter $$"+
		" Drop procedure if exists deleteAccountTable"+
		" create procedure deleteAccountTable (IN input_username VARCHAR(255))"+
		" begin"+
		" DELETE from account where username = ?;"+
		" end $$"+
		" delimiter;";

		try{
			CallableStatement cStmt = conn.prepareCall("{call sp (?}");
			cStmt.setString(1, account.getUsername());

			int rowAffected = cStmt.executeUpdate();
			return rowAffected;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int updatePassword(Account account, String newPassword) {
		String sp ="delimiter $$"+
		" Drop procedure if exists updateAccountTable"+
		" create procedure updateAccountTable (IN input_username VARCHAR(255), input_password VARCHAR(255))"+
		" begin"+
		" update account set password = ? where username = ?;"+
		" end $$"+
		" delimiter;";
		try{
			CallableStatement cStmt = conn.prepareCall("{call sp (?, ?)}");
			cStmt.setString(1, newPassword);
			cStmt.setString(2, account.getUsername());

			int rowAffected = cStmt.executeUpdate();
			return rowAffected;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int insertRating(Rate rating) {
		String sp = "Drop procedure if exists insertRateTable"+
		" create procedure insertRateTable (IN movie_ID BIGINT, user_ID BIGINT, rating FLOAT)"+
		" begin"+
		" INSERT INTO rate (movie_ID, user_ID, rating) VALUES"+
		" (?, ?, ?);"+
		" end $$"+
		" delimiter;";
		
		try{
			CallableStatement cStmt = conn.prepareCall("{call sp (?, ?, ?)}");
			cStmt.setInt(1, rating.getMovieId());
			cStmt.setInt(2, rating.getUserId());
			cStmt.setDouble(3, rating.getRating());

			int rowAffected = cStmt.executeUpdate();
			return rowAffected;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public List<Movie> retrieveMovie(String name, int retrieveType) {
		switch(retrieveType) {
		case 1:
			//search by Movie
			List<Movie> movies = new ArrayList<Movie>();
			String sqlQuery = "SELECT * FROM movie m WHERE m.title = ?;";

			try {
				statement = connection.prepareStatement(sqlQuery);
				statement.setString(1, name);
				resultSet = prepareStatement.executeQuery();
	
				while (resultSet.next()) {
					Movie movieResult = new Movie();
					movieResult.movieId = resultSet.getInt(1);
					movieResult.title = resultSet.getString(2);
					movieResult.year = resultSet.getInt(3);
					movieResult.country = resultSet.getString(4);
					movieResult.RTCriticsRating = resultSet.getDouble(5);
					movieResult.RTAudienceRating = resultSet.getDouble(6);
					movieResult.RTAudRateNum = resultSet.getInt(7);
	    			movies.add(movieResult);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return movies;
		case 2:
			//search by actor
			List<Movie> movies2 = new ArrayList<Movie>();
			String sqlQuery2 = "SELECT *"+
			" FROM movie m"+
			" WHERE m.movie_ID ="+
			" (SELECT p.movie_ID FROM act p WHERE p.actor_ID ="+
			" (SELECT a.actor_ID FROM actor a WHERE a.actor_name = ?)); ";

			try {
				statement = connection.prepareStatement(sqlQuery2);
				statement.setString(1, name);
				resultSet = prepareStatement.executeQuery();
	
				while (resultSet.next()) {
					Movie movieResult = new Movie();
					movieResult.movieId = resultSet.getInt(1);
					movieResult.title = resultSet.getString(2);
					movieResult.year = resultSet.getInt(3);
					movieResult.country = resultSet.getString(4);
					movieResult.RTCriticsRating = resultSet.getDouble(5);
					movieResult.RTAudienceRating = resultSet.getDouble(6);
					movieResult.RTAudRateNum = resultSet.getInt(7);
	    			movies2.add(movieResult);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return movies2;
		case 3: 
			//search by Director
			List<Movie> movies3 = new ArrayList<Movie>();
			String sqlQuery3 = "SELECT *"+
			" FROM movie m"+
			" WHERE m.movie_ID ="+
			" (SELECT r.movie_ID FROM direct r WHERE r.director_ID ="+
			" (SELECT d.director_ID FROM director d WHERE d.director_name = ?); ";

			try {
				statement = connection.prepareStatement(sqlQuery3);
				statement.setString(1, name);
				resultSet = prepareStatement.executeQuery();
	
				while (resultSet.next()) {
					Movie movieResult = new Movie();
					movieResult.movieId = resultSet.getInt(1);
					movieResult.title = resultSet.getString(2);
					movieResult.year = resultSet.getInt(3);
					movieResult.country = resultSet.getString(4);
					movieResult.RTCriticsRating = resultSet.getDouble(5);
					movieResult.RTAudienceRating = resultSet.getDouble(6);
					movieResult.RTAudRateNum = resultSet.getInt(7);
	    			movies3.add(movieResult);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return movies3;
		}
		return null;
	}

	public double retrieveRating(Movie movie) {
		double aveRate = 0;
		String sqlQuery = "SELECT AVG(r.rating) AS AverageRating"+
		" FROM rate r"+
		" WHERE r.movie_ID ="+
		" (SELECT m.movie_ID FROM movie m WHERE m.title = ?);";

		try {
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, movie.getTitle());
			resultSet = prepareStatement.executeQuery();

			aveRate = resultSet.getInt(1));
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
		" WHERE a.actor_ID ="+
		" (SELECT p.actor_ID FROM act p WHERE p.movie_ID ="+
		" (SELECT m.movie_ID FROM movie m WHERE m.title = ?)); ";
		
		try {
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, movieName);
			resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				actorName.add(resultSet.getString("actorName"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return actorName;
	}
	
	//given a movie, output its top-ranked actor
	public String retrieveRanking(String movieName){
		String actorName;
		String sqlQuery = "SELECT a.actor_name"+
		" FROM actor a"+
		" WHERE a.actor_ID ="+
		"(SELECT c.actor_ID FROM act c WHERE c.movie_ID = (c.movie_ID = (SELECT m.movie_ID FROM movie m WHERE m.title = ?)"+
		" ORDER BY c.ranking DESC"+
		" LIMIT 1)";

		try {
			statement = connection.prepareStatement(sqlQuery);
			statement.setInt(1, movieName);
			resultSet = prepareStatement.executeQuery();

			actorName = resultSet.getString("actor_name")
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
		" WHERE d.director_ID ="+ 
		" (SELECT r.director_ID FROM direct r WHERE r.movie_ID ="+ 
		" (SELECT m.movie_ID FROM movie m WHERE m.title = ?)); ";
		
		try {
			statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, movieName);
			resultSet = prepareStatement.executeQuery();

			// ResultSetMetaData metaData = resultSet.getMetaData();
			// int columns = metaData.getColumnCount();

			while (resultSet.next()) {
				directorName.add(resultSet.getString("directorName"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return directorName;
	}
}
