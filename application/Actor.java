package application;

public class Actor {
	private int movieId;
	private String actorId;
	private String actorName;
	private int ranking;
	
	public Actor(int movieId, String actorId, String actorName, int ranking) {
		this.movieId = movieId;
		this.actorId = actorId;
		this.actorName = actorName;
		this.ranking = ranking;
	}
	
	public int getMovieId() {
		return this.movieId;
	}
	
	public String getActorName() {
		return this.actorName;
	}
	
	public int getRanking() {
		return this.ranking;
	}
	
	public String toString() {
		return this.actorId + ", " + this.actorName + ", " + this.ranking + "\n";
	}
	
	
}
