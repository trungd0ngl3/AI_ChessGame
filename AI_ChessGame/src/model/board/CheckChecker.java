package model.board;

import model.pieces.Piece;

public class CheckChecker {
	private Board board;

	public CheckChecker(Board board) {
		this.board = board;
	}

	public boolean isKingChecked(Move move) {
		Piece king = board.findKing(move.getPiece().isWhite());
		assert king != null;
		// get current postition of the king
		int kingCol = king.getCol();
		int kingRow = king.getRow();
		if (board.getSelectedPiece() != null && board.getSelectedPiece().getName().equals("king")) {
			// compute the valid postition of the king
			kingCol = move.getNewCol();
			kingRow = move.getNewRow();
		}

		return // rook check
		checkedByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 0, 1)
				|| checkedByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 0, -1)
				|| checkedByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 1, 0)
				|| checkedByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, -1, 0)
				// bishop check
				|| checkedByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 1, 1)
				|| checkedByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 1, -1)
				|| checkedByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, -1, 1)
				|| checkedByBishop(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, -1, -1)
				// knight check
				|| checkedByKnight(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow)
				// pawn check
				|| checkedByPawn(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow)
				// king check
				|| checkedByKing(king, kingCol, kingRow);
	}

	private boolean checkedByRook(int col, int row, Piece king, int kingCol, int kingRow, int colOffset,
			int rowOffset) {
		for (int i = 1; i < Board.MAX_COL; i++) {
			if (kingCol + (i * colOffset) == col && kingRow + (i * rowOffset) == row) {
				break;
			}

			Piece p = board.getPiece(kingCol + (i * colOffset), kingRow + (i * rowOffset));
			if (p != null && p != board.getSelectedPiece()) {
				if (!board.isSameTeam(p, king) && (p.getName().equals("rook") || p.getName().equals("queen"))) {
					return true;
				}
				break;
			}
		}
		return false;
	}

	private boolean checkedByBishop(int col, int row, Piece king, int kingCol, int kingRow, int colOffset,
			int rowOffset) {
		for (int i = 1; i < Board.MAX_COL; i++) {
			if (kingCol - (i * colOffset) == col && kingRow - (i * rowOffset) == row) {
				break;
			}
			Piece p = board.getPiece(kingCol - (i * colOffset), kingRow - (i * rowOffset));
			if (p != null && p != board.getSelectedPiece()) {
				if (!board.isSameTeam(p, king) && (p.getName().equals("bishop") || p.getName().equals("queen"))) {
					return true;
				}
				break;
			}
		}
		return false;
	}

	private boolean checkedByKnight(int col, int row, Piece king, int kingCol, int kingRow) {
		return checkKnight(board.getPiece(kingCol - 1, kingRow - 2), king, col, row)
				|| checkKnight(board.getPiece(kingCol + 1, kingRow - 2), king, col, row)
				|| checkKnight(board.getPiece(kingCol + 2, kingRow - 1), king, col, row)
				|| checkKnight(board.getPiece(kingCol + 2, kingRow + 1), king, col, row)
				|| checkKnight(board.getPiece(kingCol + 1, kingRow + 2), king, col, row)
				|| checkKnight(board.getPiece(kingCol - 1, kingRow + 2), king, col, row)
				|| checkKnight(board.getPiece(kingCol - 2, kingRow + 1), king, col, row)
				|| checkKnight(board.getPiece(kingCol - 2, kingRow - 1), king, col, row);
	}

	private boolean checkKnight(Piece p, Piece king, int col, int row) {
		return p != null && !board.isSameTeam(p, king) && p.getName().equals("knight")
				&& !(p.getCol() == col && p.getRow() == row);
	}

	private boolean checkedByKing(Piece king, int kingCol, int kingRow) {
		return checkKing(board.getPiece(kingCol - 1, kingRow - 1), king)
				|| checkKing(board.getPiece(kingCol, kingRow - 1), king)
				|| checkKing(board.getPiece(kingCol + 1, kingRow - 1), king)
				|| checkKing(board.getPiece(kingCol - 1, kingRow + 1), king)
				|| checkKing(board.getPiece(kingCol, kingRow + 1), king)
				|| checkKing(board.getPiece(kingCol + 1, kingRow + 1), king)
				|| checkKing(board.getPiece(kingCol - 1, kingRow), king)
				|| checkKing(board.getPiece(kingCol + 1, kingRow), king);
	}

	private boolean checkKing(Piece p, Piece king) {
		return p != null && !board.isSameTeam(p, king) && p.getName().equals("king");
	}

	private boolean checkedByPawn(int col, int row, Piece king, int kingCol, int kingRow) {
		int colorIndex;
		if (king.isWhite()) {
			colorIndex = -1;
		} else
			colorIndex = 1;
		return checkPawn(board.getPiece(kingCol + 1, kingRow + colorIndex), king, col, row)
				|| checkPawn(board.getPiece(kingCol - 1, kingRow + colorIndex), king, col, row);
	}

	private boolean checkPawn(Piece p, Piece king, int col, int row) {
		return p != null && !board.isSameTeam(p, king) && p.getName().equals("pawn");
	}
}
