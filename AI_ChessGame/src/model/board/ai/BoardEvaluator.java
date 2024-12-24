package model.board.ai;

import model.board.Board;
import model.pieces.Piece;

public class BoardEvaluator {
	final static int CHECK_BONUS = 50;

	public int evaluate(Board board, int depth) {
		return score(board, true, depth) - score(board, false, depth);
	}

	private int score(Board board, boolean isWhiteTurn, int depth) {
		return piecesValue(board, board.isWhiteTurn) + mobility(board, isWhiteTurn);
	}

	private int kingCheck(Board board, boolean isWhiteTurn) {
		return board.checker.isKingChecked(null) ? CHECK_BONUS : 0;
	}

	private int piecesValue(Board board, boolean isWhiteTurn) {
		int value = 0;
		for (Piece p : board.getPieceList()) {
			if (p.isWhite() == isWhiteTurn) {
				value += p.getValue();
			} 
		}
		return value;
	}

	private int mobility(Board board, boolean isWhiteTurn) {
		return board.getLegalMoves(isWhiteTurn).size();
	}

}
