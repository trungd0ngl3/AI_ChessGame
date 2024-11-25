package model.pieces;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import model.board.Board;

public abstract class Piece {
	public int col, row;
	public int xPos, yPos;
	public boolean isWhite;
	protected String name;
	protected int value;
	public BufferedImage img;

	public Piece(int col, int row, boolean isWhite) {
		this.col = col;
		this.row = row;
		this.isWhite = isWhite;
		xPos = col * Board.TILE_SIZE;
		yPos = row * Board.TILE_SIZE;
	}

	public BufferedImage getImage(String path) {
		BufferedImage result = null;
		try {
			img = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public void draw(Graphics2D g2) {
		g2.drawImage(img, xPos, yPos, 64, 64, null);
	}
}
