package app;

import javax.swing.JFrame;

public class RunApp {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setTitle("Chess Game");
		
		
		f.setSize(800,600);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
}
