package escapeTheMatrix;

import java.awt.event.*;
import java.awt.*;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MatrixPanel extends JPanel{

		private Player player;
		private Timer test;
		private TimerListener enemyMover;
		private GamePanel gamePanel;
		private StatusPanel statusPanel;
		
		private Obstacle[] obstacles;
		private int numObs;
		
		// seed to help the game understand what parts of the grid will be valid positions
		// private String setupSeed = "";
		
		
		
	public MatrixPanel(LayoutManager lm) {
		super(lm);
		this.setBackground(Color.GRAY);
		player = new Player(200, 200);
		
		// for the time being, there will be no menu selection, meaning you will always start at level 1, and have to progress
		// forward from there
		obstacles = new Obstacle[72];
		String initialLevel = "100000000000"
				            + "010000000000"
				            + "001000000000"
				            + "000000000100"
				            + "000000000010"
				            + "000000000001"
				            + "ssssssssssss"
				            + "ssssssssssss"
				            + "ssssssssssss"
				            + "ssssssssssss"
				            + "ssssssssssss"
				            + "ssssssssssss";
		generateLevelObstacles(initialLevel);
		//this.startBaseLevel();
		this.addKeyListener(new GameKeyListener());
		this.setFocusable(true);

		enemyMover = new TimerListener(3000);
		//timer should have delay of 100
		test = new Timer(100, enemyMover);
		test.start();
		
		// GamePanel - the grid the player moves one - center/west
		// StatusPanel - displays timer, score, level #, etc. - east
		// BorderLayout setup for panels
		statusPanel = new StatusPanel();
		this.add(statusPanel, BorderLayout.WEST);
		gamePanel = new GamePanel();
		this.add(gamePanel, BorderLayout.CENTER);
	}
	
	// ONLY call this if you want to update the ENTIRE screen. 
	//Otherwise, call repaint for the specific part to be repainted (StatusPanel or GamePanel)
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// testing
		
		//g.setColor(Color.GREEN);
		//for (int i = 2; i < 8; i++) {
		//	g.drawLine(i*100 - 50, 50, i*100 - 50, 600);
		//	g.drawLine(150, (i*50) - 50, 650, (i*50) - 50);
		//	g.drawLine(150, i*50 + 250, 650, (i*50) + 250);
		//}
		/*
		g.setColor(Color.GREEN);
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				//g.drawRect((125 + j*100), (25 + i*50), 50, 50);
				String base = "qwertyuiopasdfghjklzxcvbnm1234567890#@%&QWERTYUIOPASDFGHJKLZXCVBNM";
				int rand = (int)(Math.random()*56);
				g.drawString(base.substring(rand, rand +1), (148 + j*100), (53 + i*50));
			}
		}
		g.setColor(Color.BLUE);
		g.fillRect(player.getX() + 25, player.getY() - 25, 50, 50);
		*/
		//g.setColor(Color.RED);
		
	}
	public class GamePanel extends JPanel{
		// old base String:        "qwertyuiopasdfghjklzxcvbnm1234567890#@%&?!+-$=QWERTYUIOPASDFGHJKLZXCVBNM"
		public final String base = "qwh3lp-oDrtyiSadf@gJkzvBNm1+2=?4&IOP56xc790#e!$YTREWQU%AusFGHjKLZXCVbnM8";
		// splitting it into rows and columns to be printed and cycled
		public char[][] gridChars = {
				base.substring(0, 12).toCharArray(), base.substring(12, 24).toCharArray(), 
				base.substring(24, 36).toCharArray(), base.substring(36, 48).toCharArray(),
				base.substring(48, 60).toCharArray(), base.substring(60, 72).toCharArray() 
		};
		public GamePanel() {
			this.setSize(500, 550);
			this.setPreferredSize(this.getSize());
			this.setBackground(Color.BLACK);
			
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			
			g.setColor(Color.GREEN);
			//for (int i = 0; i < 12; i++) {
			//	for (int j = 0; j < 6; j++) {
			//		String base = "qwertyuiopasdfghjklzxcvbnm1234567890#@%&?!+-$=QWERTYUIOPASDFGHJKLZXCVBNM";
			//		int rand = (int)(Math.random()*56);
			//		g.drawString(base.substring(rand, rand +1), (48 + j*100), (53 + i*50));
			//	}
			//}
			
			//  so uh, for some reason the top row copies the second-to-top row?
			// (but it doesn't persist after the first two rows, meaning it is some sort of visual bug)
			// I have no idea why to be honest
			// TODO fix the above, hopefully?
			char temp;
			char temp2;
			for (int i = 0; i < gridChars.length; i++) {
				temp = gridChars[i][0];
				for (int j = 0; j < gridChars[i].length; j++) {
					int iI = i;
					int iJ = j + 1;
					if (iJ >= gridChars[i].length) {iJ = 0; iI++;}
					if (iI >= gridChars.length) iI = 0;
					temp2 = gridChars[iI][iJ];
					gridChars[iI][iJ] = temp;
					temp = temp2;
				}
			}
			for (int i = 0; i < gridChars.length; i++) {
				for (int j = 0; j < gridChars[i].length; j++) {
					g.drawChars(gridChars[i], j, 1, (48 + i*100), (53 + j*50));
				}
			}
			g.setColor(Color.BLUE);
			// finding what character a player is supposed to be:
			// player.getY() / 25 / 2 ex: 525 / 25 / 2 - 1 = 9.5 -> 10
			// player.getX() / 25 / 4 ex: 525 / 25 / 4 = 5.25 -> 5
 			g.drawChars(gridChars[(player.getX() / 25) / 4], ((player.getY() / 25) / 2) - 1, 1, player.getX() + 48, player.getY() +3);
			g.drawRect(player.getX() + 25, player.getY() - 25, 50, 50);
			
			g.setColor(Color.RED);
			for (int i = 0; i < numObs; i++) {
				g.drawRect(obstacles[i].getX(), obstacles[i].getY(), 50, 50);	
				g.drawChars(gridChars[(obstacles[i].getX() / 25) / 4], ((obstacles[i].getY() / 25) / 2), 1, obstacles[i].getX() + 23, obstacles[i].getY() + 28);
				//System.out.println(" Enemy #" + i + " is located at: " + obstacles[i].getX() + " " + obstacles[i].getY());
			}
		}
	}
	public class StatusPanel extends JPanel {
		public StatusPanel () {
			this.setSize(100, 550);
			this.setPreferredSize(this.getSize());
			this.setBackground(Color.GRAY);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.GREEN);
			double tester = ((double) enemyMover.remainingTime) / 1000;
			g.setFont(new Font("name", Font.PLAIN, 15));
			g.drawString(tester + "", 45, 50);
		}
	}
	
	
	
	// player inner class
	public class Player{
		public int x, y;
		
		public Player (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		
		public void setX(int x) {
			this.x = x;
		}
		public void setY(int y) {
			this.y = y;
		}
		public void moveX (int dir) {
			if (canMoveX(dir)) x += dir * 100;
		}
		public void moveY (int dir) {
			if (canMoveY(dir)) y += dir * 50;
		}
		//TODO: potentially add more adaptability towards more unconventional level layouts, instead of hard-coded edges
		// eventually add enemy detection too, but enemies need to be added first
		public boolean canMoveY(int dir) {	
			if (dir == -1) {
				if (y >= 75) return true;
			} else {
				if (y <= 575) return true;
			}
			return false;
		}
		public boolean canMoveX(int dir) {
			if (dir == -1) {
				if (x >= 50) return true;
				else this.setX(500);
			} else {
				if (x <= 450) return true;
				else this.setX(0);
			}
			return false;
		}
	}
	
	
	private class GameKeyListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			// do nothing
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			switch (e.getKeyCode()) {
				case KeyEvent.VK_W:
					player.moveY(-1);
					break;
				case KeyEvent.VK_A:
					player.moveX(-1);
					break;
				case KeyEvent.VK_S:
					player.moveY(1);
					break;
				case KeyEvent.VK_D:
					player.moveX(1);
					break;
				case KeyEvent.VK_UP:
					player.moveY(-1);
					break;
				case KeyEvent.VK_LEFT:
					player.moveX(-1);
					break;
				case KeyEvent.VK_DOWN:
					player.moveY(1);
					break;
				case KeyEvent.VK_RIGHT:
					player.moveX(1);
					break;
			}
			gamePanel.repaint();
			enemyMover.rushTimer();
			System.out.println((player.x + 25) + " " + (player.y - 25));
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// do nothing
		}
		
	}
	
	private class TimerListener implements ActionListener {
		protected int startTime, remainingTime; // both are in milliseconds

		public TimerListener (int startTime) {
			super();
			this.startTime = startTime; 
			this.remainingTime = startTime;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			//double tester = ((double) remainingTime) / 1000;
			//System.out.println(tester);
			remainingTime -= 100;
			if (remainingTime <= 0) {
				resetRemainingTime();
				System.out.println("Enemies move!");
				repaint();
			}
			statusPanel.repaint();
			
		}
		
		protected void resetRemainingTime() {
			remainingTime = startTime;
		}
		
		protected void rushTimer() {
			remainingTime = 0;
			resetRemainingTime();
		}
		
	}
	
	// various methods used within matrixPanel

	// requires a string of length 144: first 72 represent spots on the board, latter 72 represent direction
	// 0 == empty
	// 1 == obstacle of type red
	// 2 == obstacle of type orange
	// 3 == obstacle of type purple
	private void generateLevelObstacles(String layout) {
		numObs = 0;
		for (int k = 0; k < 72; k++) {
			int i = k % 6;
			int j = k % 12;
			if (Character.getNumericValue(layout.charAt(k)) == 1) {
				obstacles[numObs++] = new Obstacle((25 + i*100), (25 + j*50), "RED", layout.charAt(i+72) + "");
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
