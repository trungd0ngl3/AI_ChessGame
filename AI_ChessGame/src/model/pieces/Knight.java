package model.pieces;

import java.awt.image.BufferedImage;

import model.board.Board;

public class Knight extends Piece {
	BufferedImage img;

	public Knight(Board board,int col, int row, boolean isWhite) {
		super(board, col, row, isWhite);
		name = "knight";
		value = 3;
		if (isWhite) {
			img = getImage("/pieces/white-knight.png");
		} else {
			img = getImage("/pieces/black-knight.png");
		}

	}

	@Override
	public boolean isValidMovement(int newCol, int newRow) {
		if (Math.abs(col - newCol) * Math.abs(row - newRow) == 2) {
			return true;
		} else
			return false;
	}

	public boolean isCollideWithPiece(int newCol, int newRow) {
		// TODO Auto-generated method stub
		return false;
	}


}
