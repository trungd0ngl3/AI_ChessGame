package model.pieces;

import java.awt.image.BufferedImage;

import model.board.Board;

public class Rook extends Piece {
	BufferedImage img;

	public Rook(Board board, int col, int row, boolean isWhite) {
		super(board, col, row, isWhite);
		name = "rook";
		value = 5;
		if (isWhite) {
			img = getImage("/pieces/white-rook.png");
		} else {
			img = getImage("/pieces/black-rook.png");
		}
	}

	public boolean isValidMovement(int newCol, int newRow) {
		if (col == newCol || row == newRow) {
			return true;
		} else
			return false;
	}

	@Override
	public boolean isCollideWithPiece(int newCol, int newRow) {
		// check left
		if (col > newCol) {
			for (int i = col - 1; i > newCol; i--) {
				if (board.getPiece(i, row) != null) {
					return true;
				}
			}
		}

		// check right
		if (col < newCol) {
			for (int i = col + 1; i < newCol; i++) {
				if (board.getPiece(i, row) != null) {
					return true;
				}
			}
		}

		// check up
		if (row > newRow) {
			for (int i = row - 1; i > newRow; i--) {
				if (board.getPiece(col, i) != null) {
					return true;
				}
			}
		}

		// check down
		if (row < newRow) {
			for (int i = row + 1; i < newRow; i++) {
				if (board.getPiece(col, i) != null) {
					return true;
				}
			}
		}

		return false;
	}

}
