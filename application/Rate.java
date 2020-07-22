package application;

public class Rate {
	
	int movieId;
	String userId;
	double rating;
	
	public Rate(int movieId, String userId, double rating) {
		this.movieId = movieId;
		this.userId = userId;
		this.rating = rating;
	}
}
