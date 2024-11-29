package model.pieces;

import java.awt.image.BufferedImage;

import model.board.Board;

public class King extends Piece {
	BufferedImage img;

	public King(Board board,int col, int row, boolean isWhite) {
		super(board, col, row, isWhite);
		name = "king";
		value = 999;
		if (isWhite) {
			img = getImage("/pieces/white-king.png");
		} else {
			img = getImage("/pieces/black-king.png");
		}

	}

	// Check king's valid movement
	@Override
	public boolean isValidMovement(int newCol, int newRow) {
		if (Math.abs(col - newCol) + Math.abs(row - newRow) == 1
				|| Math.abs(col - newCol) * Math.abs(row - newRow) == 1) {
			return true;
		} else
			return false;
	}

	public boolean isCollideWithPiece(int newCol, int newRow) {
		return false;
	}

}
