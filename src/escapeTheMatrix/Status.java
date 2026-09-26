package escapeTheMatrix;

public class Status {

	public int level, score, deaths;
	
	public Status () {
		level = 1;
		score = 0;
		deaths = 0;
	}
	
	
	// getters and setters
	public int getLevel() {
		return level;
	}
	public int getScore() {
		return score;
	}
	public int getDeaths() {
		return deaths;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	
	public void incrementLevel() {
		level++;
	}
	public void incrementDeaths() {
		deaths++;
	}
}
