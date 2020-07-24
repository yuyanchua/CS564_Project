package application;

public class Rate {
	
	int movieId;
	int userId;
	double rating;
	
	public Rate(int movieId, int userId, double rating) {
		this.movieId = movieId;
		this.userId = userId;
		this.rating = rating;
	}

	public int getMovieId() {
		return movieId;
	}

	public int getUserId() {
		return userId;
	}

	public double getRating() {
		return rating;
	}
}
