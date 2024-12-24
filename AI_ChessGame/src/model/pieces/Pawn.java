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
		// capture left or right
		if (newCol == col - 1 && newRow == row - colorIndex && board.getPiece(newCol, newRow) != null) {
			return true;
		}
		// capture right
		if (newCol == col + 1 && newRow == row - colorIndex && board.getPiece(newCol, newRow) != null) {
			return true;
		}
		// en passant left
		if (board.getTileNum(newCol, newRow) == board.getEnPassantTile() && newCol == col - 1
				&& newRow == row - colorIndex && board.getPiece(newCol, newRow + colorIndex) != null) {
			return true;
		}
		// en passant right
		if (board.getTileNum(newCol, newRow) == board.getEnPassantTile() && newCol == col + 1
				&& newRow == row - colorIndex && board.getPiece(newCol, newRow + colorIndex) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isCollideWithPiece(int newCol, int newRow) {
		if (isWhite) {
			for (int i = row - 1; i > newRow; i--) {
				if (board.getPiece(col, i) != null) {
					return true;
				}
			}
		} else {
			for (int i = row + 1; i < newRow; i++) {
				if (board.getPiece(col, i) != null) {
					return true;
				}
			}
		}

		return super.isCollideWithPiece(newCol, newRow);
	}

}