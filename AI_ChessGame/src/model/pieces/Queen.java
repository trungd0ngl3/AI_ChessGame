package model.pieces;

import java.awt.image.BufferedImage;

import model.board.Board;

public class Queen extends Piece {
	BufferedImage img;

	public Queen(Board board, int col, int row, boolean isWhite) {
		super(board, col, row, isWhite);
		name = "queen";
		value = 9;
		if (isWhite) {
			img = getImage("/pieces/white-queen.png");
		} else {
			img = getImage("/pieces/black-queen.png");
		}

	}

	@Override
	public boolean isValidMovement(int newCol, int newRow) {
		// bishop movement
		if (Math.abs(col - newCol) == Math.abs(row - newRow)) {
			return true;
		}
		// rook movement
		if (col == newCol || row == newRow) {
			return true;
		} else
			return false;
	}

	public boolean isCollideWithPiece(int newCol, int newRow) {

		// check if queen move like a rook
		if (col == newCol || row == newRow) {
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
		}

		// check if queen move like a bishop
		else {
			// check upleft
			if (col > newCol && row > newRow) {
				for (int i = 1; i < Math.abs(newCol - col); i++) {
					if (board.getPiece(col - i, row - i) != null) {
						return true;
					}
				}
			}
			// check upright
			if (col < newCol && row > newRow) {
				for (int i = 1; i < Math.abs(newCol - col); i++) {
					if (board.getPiece(col + i, row - i) != null) {
						return true;
					}
				}
			}
			// check downleft
			if (col > newCol && row < newRow) {
				for (int i = 1; i < Math.abs(newCol - col); i++) {
					if (board.getPiece(col - i, row + i) != null) {
						return true;
					}
				}
			}
			// check downright
			if (col < newCol && row < newRow) {
				for (int i = 1; i < Math.abs(newCol - col); i++) {
					if (board.getPiece(col + i, row + i) != null) {
						return true;
					}
				}
			}
		}
		return false;

	}

}
