package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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
	
	
}
