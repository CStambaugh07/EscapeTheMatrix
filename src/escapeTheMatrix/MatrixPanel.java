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
		
		
		
		
		
	public MatrixPanel(LayoutManager lm) {
		super(lm);
		this.setBackground(Color.GRAY);
		player = new Player(200, 200);
		
		this.addKeyListener(new GameKeyListener());
		this.setFocusable(true);
		//repaint();
		enemyMover = new TimerListener(3000);
		//timer should have delay of 100; I increased it for testing
		test = new Timer(100, enemyMover);
		test.start();
		// GamePanel - the grid the player moves one
		// StatusPanel - displays timer, score, level #, etc.
		/* old gridBag version
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		statusPanel = new StatusPanel();
		this.add(statusPanel, c);
		c.gridx = GridBagConstraints.RELATIVE;
		c.gridy = 0;
		gamePanel = new GamePanel();
		this.add(gamePanel, c);
		
		statusPanel.repaint();
		gamePanel.repaint();
		*/
		// turns out BorderLayout was way easier for this, neat!
		statusPanel = new StatusPanel();
		this.add(statusPanel, BorderLayout.WEST);
		gamePanel = new GamePanel();
		this.add(gamePanel);
		
	}
	
	// no longer used, instead call repaint for the specific part to be repainted (TimerPanel or GamePanel)
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
		public final String base = "qwh3lp-usrtyioadf@gjkzvbnm1+2=?4&IOP56xc790#e!$YTREWQU%ASDFGHJKLZXCVBNM8";
		// don't mind this thing
		public char[][] lol = {
				base.substring(0, 12).toCharArray(), base.substring(12, 24).toCharArray(), 
				base.substring(24, 36).toCharArray(), base.substring(36, 48).toCharArray(),
				base.substring(48, 60).toCharArray(), base.substring(60, 72).toCharArray() 
		};
		public GamePanel() {
			this.setSize(500, 550);
			this.setPreferredSize(this.getSize());
			this.setBackground(Color.BLACK);
			// just some
			
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
			// I have no idea why to be honest
			// TODO fix the above, hopefully?
			char temp;
			char temp2;
			for (int i = 0; i < lol.length; i++) {
				temp = lol[i][0];
				for (int j = 0; j < lol[i].length; j++) {
					int iI = i;
					int iJ = j + 1;
					if (iJ >= lol[i].length) {iJ = 0; iI++;}
					if (iI >= lol.length) iI = 0;
					temp2 = lol[iI][iJ];
					lol[iI][iJ] = temp;
					temp = temp2;
				}
			}
			for (int i = 0; i < lol.length; i++) {
				for (int j = 0; j < lol[i].length; j++) {
					g.drawChars(lol[i], j, 1, (48 + i*100), (53 + j*50));
				}
			}
			g.setColor(Color.BLUE);
			// player.getY() / 25 / 2 ex: 525 / 25 / 2 - 1 = 9.5 -> 10
			// player.getX() / 25 / 4 ex: 525 / 25 / 4 = 5.25 -> 5
 			g.drawChars(lol[(player.getX() / 25) / 4], ((player.getY() / 25) / 2) - 1, 1, player.getX() + 48, player.getY() +3);
			g.drawRect(player.getX() + 25, player.getY() - 25, 50, 50);
			
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
			g.drawString(tester + "", 50, 50);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
