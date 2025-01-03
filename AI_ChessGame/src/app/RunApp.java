package app;

import javax.swing.JFrame;

import model.board.Board;

public class RunApp {
	JFrame window;

	public RunApp() {
		window = new JFrame();
		window.setTitle("Chess Game");
		Board board = new Board();
		window.add(board);
		window.pack();
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	public static void main(String[] args) {
		new RunApp();
	}

}
