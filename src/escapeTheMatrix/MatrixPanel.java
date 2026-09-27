package escapeTheMatrix;

import java.awt.event.*;
import java.awt.*;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MatrixPanel extends JPanel{

		private Player player;
		private Timer test;
		private TimerListener enemyMover;
	public MatrixPanel() {
		this.setBackground(Color.BLACK);
		player = new Player(200, 200);
		
		this.addKeyListener(new GameKeyListener());
		this.setFocusable(true);
		repaint();
		enemyMover = new TimerListener(3000);
		// timer should have delay of 100; I increased it for testing
		test = new Timer(100, enemyMover);
		test.start();
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// testing
		
		g.setColor(Color.GREEN);
		for (int i = 2; i < 8; i++) {
			g.drawLine(i*100 - 50, 50, i*100 - 50, 600);
			g.drawLine(150, (i*50) - 50, 650, (i*50) - 50);
			g.drawLine(150, i*50 + 250, 650, (i*50) + 250);
		}
		double tester = ((double) enemyMover.remainingTime) / 1000;
		g.drawString(tester + "", 50, 50);
		g.setColor(Color.RED);
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
				if (x >= 150) return true;
				else this.setX(600);
			} else {
				if (x <= 550) return true;
				else this.setX(100);
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
			repaint();
			enemyMover.rushTimer();
			//System.out.println(player.x + " " + player.y);
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
			}
			repaint();
			
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
