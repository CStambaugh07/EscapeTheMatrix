package escapeTheMatrix;

import java.awt.BorderLayout;

import javax.swing.*;

public class EscapeTheMatrix {

	
	public static void main (String[] args) {
		JFrame frame = new JFrame("Escape the Matrix");
		frame.setSize(800, 675);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MatrixPanel(new BorderLayout()));
		frame.setVisible(true);
	}
}
