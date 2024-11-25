package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import model.board.Board;
import model.pieces.Bishop;
import model.pieces.Piece;

public class MainView extends JPanel implements Runnable {
	final int MAX_COL = 8;
	final int MAX_ROW = 8;
	final static int TILE_SIZE = 64;
	Thread gameThread;
	Board board;

	public MainView() {
		setPreferredSize(new Dimension(800, 512));
		setBackground(Color.black);
		board = new Board();
	}

	public void runGame() {
		
		this.gameThread = new Thread(this);
		gameThread.start();
	}

	private void update() {

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		board.draw(g2);
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / 60;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}
}
