package application;

public class Rate {
	
	String movieId;
	String userId;
	double rating;
	
	public Rate(String movieId, String userId, double rating) {
		this.movieId = movieId;
		this.userId = userId;
		this.rating = rating;
	}
}
