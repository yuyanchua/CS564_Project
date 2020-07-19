package application;

public class Actor {
	private String actorId;
	private String actorName;
	private int ranking;
	
	public Actor(String actorId, String actorName, int ranking) {
		this.actorId = actorId;
		this.actorName = actorName;
		this.ranking = ranking;
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
