package escapeTheMatrix;

public class Obstacle {
	public int x, y; // positional coords
	public String type; // what AI does this obstacle use.
	// TYPES KEY (WIP):
	// WHITE = static, ORANGE = staticHostile, Red = basicHostile
	public static final String DEFAULT_TYPE = "WHITE"; // non-moving, non-hostile obstacle
	
	public Obstacle(int x, int y) {
		this.x = x;
		this.y = y;
		this.type = DEFAULT_TYPE;
	}
	// behavior
	public void update() {
		
	}
	
	
	
	// getters and setters
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public String getType() {
		return type;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setType(String type) {
		this.type = type;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
