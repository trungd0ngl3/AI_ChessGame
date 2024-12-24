package model.pieces;

import java.awt.image.BufferedImage;

import model.board.Board;
import model.board.Move;

public class King extends Piece {
	BufferedImage img;

	public King(Board board, int col, int row, boolean isWhite) {
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
		return Math.abs(col - newCol) + Math.abs(row - newRow) == 1
				|| Math.abs(col - newCol) * Math.abs(row - newRow) == 1 || canCastle(newCol, newRow);
	}

	// Check king's castle
	private boolean canCastle(int newCol, int newRow) {
		if (row == newRow) {
			if (newCol == 6) {
				Piece rook = board.getPiece(7, newRow);
				if (rook != null && rook.isFirstMove && isFirstMove) {

					return board.getPiece(5, newRow) == null && board.getPiece(6, newRow) == null
							&& !board.getChecker().isKingChecked(new Move(board, 5, newRow, this));
				}
			}
			if (newCol == 2) {
				Piece rook = board.getPiece(0, newRow);
				if (rook != null && rook.isFirstMove && isFirstMove) {
					return board.getPiece(1, newRow) == null && board.getPiece(2, newRow) == null
							&& board.getPiece(3, newRow) == null
							&& !board.getChecker().isKingChecked(new Move(board, 3, newRow, this));
				}
			}
		}

		return false;
	}
}
