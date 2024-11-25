package model.pieces;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import model.board.Board;

public abstract class Piece {
	protected int col, row;
	protected int xPos, yPos;
	protected boolean isWhite;
	protected String name;
	protected int value;
	protected BufferedImage img;

	public Piece(int col, int row, boolean isWhite) {
		this.col = col;
		this.row = row;
		this.isWhite = isWhite;
		xPos = row * Board.TILE_SIZE;
		yPos = col * Board.TILE_SIZE;
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
		g2.drawImage(img, xPos, yPos, Board.TILE_SIZE, Board.TILE_SIZE, null);
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}
}
