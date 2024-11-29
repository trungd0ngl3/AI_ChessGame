package model.board;

import model.pieces.Piece;

public class CheckChecker {
	private Board board;

	public CheckChecker(Board board) {
		this.board = board;
	}

	public boolean isKingChecked(Move move) {
		Piece king = board.findKing(move.getPiece().isWhite());
		int kingCol = king.getCol();
		int kingRow = king.getRow();
		if (board.getSelectedPiece() != null || board.getSelectedPiece().getName().equals("king")) {
			kingCol = move.getNewCol();
			kingRow = move.getNewRow();
		}

		return checkedByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 0, 1)
				|| checkedByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 0, -1)
				|| checkedByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, 1, 0)
				|| checkedByRook(move.getNewCol(), move.getNewRow(), king, kingCol, kingRow, -1, 0);
	}

	private boolean checkedByRook(int col, int row, Piece king, int kingCol, int kingRow, int colOffset,
			int rowOffset) {
		for (int i = 0; i < board.MAX_COL; i++) {
			if (kingCol + (i * colOffset) == col && kingRow + (i * rowOffset) == row) {
				break;
			}
			Piece p = board.getPiece(kingCol + (i * colOffset), kingRow + (i * colOffset));
			if (p != null && p != board.getSelectedPiece()) {
				if (!board.isSameTeam(p, king) && p.getName().equals("rook") || p.getName().equals("queen")) {
					return true;
				}
				break;
			}
		}
		return false;
	}
}
