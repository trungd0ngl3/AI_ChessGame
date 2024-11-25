package model.board;

import java.awt.Color;
import java.awt.Graphics2D;

public class Board {
	final int MAX_COL = 8;
	final int MAX_ROW = 8;
	public static final int TILE_SIZE = 64;
	public static final int HALF_TILE_SIZE = TILE_SIZE / 2;
	
	public void draw(Graphics2D g2) {
		for (int row = 0; row < MAX_ROW; row++) {
			for (int col = 0; col < MAX_COL; col++) {
				if ((row + col) % 2 == 0) {
					g2.setColor(Color.decode("#FFFACD"));
				} else {
					g2.setColor(Color.decode("#593E1A"));
				}
				g2.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			}
		}
	}
}
