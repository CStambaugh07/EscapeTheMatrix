package escapeTheMatrix;

import java.awt.event.*;
import java.awt.*;

import javax.swing.JPanel;

public class MatrixPanel extends JPanel{

		Player player;
	public MatrixPanel() {
		this.setBackground(Color.BLACK);
		player = new Player(200, 200);
		
		this.addKeyListener(new GameKeyListener());
		this.setFocusable(true);
		repaint();
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
		g.setColor(Color.RED);
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				g.drawRect((125 + j*100), (25 + i*50), 50, 50);
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
					repaint();
					break;
				case KeyEvent.VK_A:
					player.moveX(-1);
					repaint();
					break;
				case KeyEvent.VK_S:
					player.moveY(1);
					repaint();
					break;
				case KeyEvent.VK_D:
					player.moveX(1);
					repaint();
					break;
				case KeyEvent.VK_UP:
					player.moveY(-1);
					repaint();
					break;
				case KeyEvent.VK_LEFT:
					break;
				case KeyEvent.VK_DOWN:
					player.moveY(1);
					repaint();
					break;
				case KeyEvent.VK_RIGHT:
					break;
			}
			System.out.println(player.x + " " + player.y);
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// do nothing
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
