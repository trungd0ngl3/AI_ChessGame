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
		g2.drawImage(img, xPos, yPos, Board.TILE_SIZE, Board.TILE_SIZE, null);
	}

	//////////////////// GETTERS AND SETTERS
	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public String getName() {
		return name;
	}

	public boolean isWhite() {
		return this.isWhite;
	}
}
