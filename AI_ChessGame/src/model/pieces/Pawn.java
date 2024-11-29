package model.pieces;

import java.awt.image.BufferedImage;

import model.board.Board;

public class Pawn extends Piece {
	BufferedImage img;

	public Pawn(Board board, int col, int row, boolean isWhite) {
		super(board, col, row, isWhite);
		name = "pawn";
		value = 1;
		if (isWhite) {
			img = getImage("/pieces/white-pawn.png");
		} else {
			img = getImage("/pieces/black-pawn.png");
		}

	}

	@Override
	public boolean isValidMovement(int newCol, int newRow) {
		int colorIndex;
		if (isWhite)
			colorIndex = 1;
		else
			colorIndex = -1;

		// pawn move 1 square
		if (col == newCol && newRow == row - colorIndex && board.getPiece(newCol, newRow) == null) {
			return true;
		}
		// pawn's first move (2 squares)
		if (isFirstMove && col == newCol && newRow == row - colorIndex * 2 && board.getPiece(newCol, newRow) == null) {
			return true;
		}
		// capture left
		if (newCol == col - 1 && newRow == row - colorIndex && board.getPiece(newCol, newRow) != null) {
			return true;
		}
		// capture right
		if (newCol == col + 1 && newRow == row - colorIndex && board.getPiece(newCol, newRow) != null) {
			return true;
		}

		return false;
	}

	public boolean isCollideWithPiece(int newCol, int newRow) {
		return false;
	}

}