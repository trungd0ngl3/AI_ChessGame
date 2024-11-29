package model.pieces;

import java.awt.image.BufferedImage;

import model.board.Board;

public class Bishop extends Piece {
	BufferedImage img;

	public Bishop(Board board, int col, int row, boolean isWhite) {
		super(board, col, row, isWhite);
		name = "bishop";
		value = 3;
		if (isWhite) {
			img = getImage("/pieces/white-bishop.png");
		} else {
			img = getImage("/pieces/black-bishop.png");
		}

	}

	@Override
	public boolean isValidMovement(int newCol, int newRow) {
		if (Math.abs(col - newCol) == Math.abs(row - newRow)) {
			return true;
		} else
			return false;
	}

	public boolean isCollideWithPiece(int newCol, int newRow) {
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
		return false;
	}

}
