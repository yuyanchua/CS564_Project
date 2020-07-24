package application;

public class Movie {
	int movieId;
	String title;
	int year;
	String country;
	double RTCriticsRating;
	double RTAudienceRating;
	int RTAudRateNum;
	
	
	public Movie(int movieId, String title, int year, String country, 
			double RTCriticsRating, double RTAudienceRating, int RTAudRateNum) {
		this.movieId = movieId;
		this.title = title;
		this.year = year;
		this.country = country;
		this.RTCriticsRating = RTCriticsRating;
		this.RTAudienceRating = RTAudienceRating;
		this.RTAudRateNum = RTAudRateNum;
	}


	public int getMovieId() {
		return movieId;
	}

	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}

	public double getRTCriticsRating() {
		return RTCriticsRating;
	}

	public String getCountry() {
		return country;
	}


	public double getRTAudienceRating() {
		return RTAudienceRating;
	}


	public int getRTAudRateNum() {
		return RTAudRateNum;
	}

}
