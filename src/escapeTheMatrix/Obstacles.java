package escapeTheMatrix;
import java.util.ArrayList;

public class Obstacles {
	public ArrayList<Obstacle> obstacles; // list of obstacles
	public int updateInterval; // time between each enemy update (in ms)
	
	public Obstacles(int updateInterval) {
		this.updateInterval = updateInterval;
		obstacles = new ArrayList<Obstacle>();
	}
	
	
	
	
	// getters and setters
	public int getInterval() {
		return updateInterval;
	}
	
	public void setInterval(int updateInterval) {
		this.updateInterval = updateInterval;
	}
}
