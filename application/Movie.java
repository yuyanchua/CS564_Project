package application;

public class Movie {
	int movieID;
	String title;
	int year;
	String country;
	double RTCriticsRating;
	double RTAudienceRating;
	int RTAudRateNum;
	
	
	public Movie(int movieID, String title, int year, String country, 
			double RTCriticsRating, double RTAudienceRating, int RTAudRateNum) {
		this.movieID = movieID;
		this.title = title;
		this.year = year;
		this.country = country;
		this.RTCriticsRating = RTCriticsRating;
		this.RTAudienceRating = RTAudienceRating;
		this.RTAudRateNum = RTAudRateNum;
	}
}
