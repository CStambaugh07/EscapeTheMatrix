package escapeTheMatrix;

public class Obstacle {
	public int x, y; // positional coords
	public String type; // what AI does this obstacle use. (and also their color)
	public String direction; // what direction the obstacle is facing (only relevant to obstacles that move)
	// TYPE KEY (WIP):
	// ORANGE = setPath, Red = static, purple = chaser
	public static final String DEFAULT_TYPE = "RED"; // non-moving obstacle
	
	public Obstacle(int x, int y) {
		this.x = x;
		this.y = y;
		this.type = DEFAULT_TYPE;
		this.direction = "south";
	}
	
	public Obstacle(int x, int y, String type) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.direction = "south";
	}
	
	public Obstacle(int x, int y, String type, String direction) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.direction = direction;
	}
	// behavior
	public void update() {
		if (type.equals(DEFAULT_TYPE)) {
			// do nothing
		}
		else if (type.equals("ORANGE")) {
			// moves in their set direction
			orange();
		}
	}
	
	private void orange() {
		move();
	}
	
	// internal functions	
	private void move() {
		if (direction.equals("north") || direction.equals("n")) 
		{
			if (y >= 75) y -= 50;
			else this.setY(575);
		} 
		else if (direction.equals("south") || direction.equals("s")) 
		{	
			if (y >= 75) y += 50;
			else this.setY(575);
		} 
		else if (direction.equals("west") || direction.equals("w")) 
		{	
			if (x >= 50) x -= 100;
			else this.setY(450);
		} 
		else if (direction.equals("east") || direction.equals("e")) 
		{	
			if (y >= 450) x += 100;
			else this.setY(50);
		} 
		else 
		{
			System.out.print("Attribute Error in canMove in Obstacle.java");
		}
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
	public String getDirection() {
		return direction;
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
	public void setDirection(String direction) {
		this.direction = direction;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
