package model.board.ai;

import model.board.Board;
import model.board.Move;

public class MiniMax {
	BoardEvaluator boardEvaluator;

	public MiniMax() {
		this.boardEvaluator = new BoardEvaluator();
	}

	public Move execute(Board board, int depth) {
		long startTime = System.currentTimeMillis();
		Move bestMove = null;

		int minValue = Integer.MAX_VALUE;
		int maxValue = Integer.MIN_VALUE;

		int currentValue = 0;
		int numMoves = board.getLegalMoves(!board.isWhiteTurn).size();
		System.out.println(board.status);
		for (Move move : board.getLegalMoves(!board.isWhiteTurn)) {
			if (board.status) {
				board.makeMove(move);
				currentValue = minimax(board, !board.isWhiteTurn, depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
				System.out.println(currentValue);
//				if (!board.isWhiteTurn && currentValue >= maxValue) {
//					maxValue = currentValue;
//					bestMove = move;
//				} else if (board.isWhiteTurn && currentValue <= minValue) {
//					minValue = currentValue;
//					bestMove = move;
//				}
			}
		}
		board.status = true;

//		System.out.println(bestMove.getPiece().getName() + " " + bestMove.getPiece().isWhite());
//		System.out.println(bestMove.getNewCol() + " " + bestMove.getNewRow());
		return bestMove;
	}

	// alpha-beta pruning
	public int minimax(Board board, boolean isWhiteTurn, int depth, int alpha, int beta) {
		if (depth == 0) {
			return boardEvaluator.evaluate(board, depth);
		}
		if (isWhiteTurn) {
			int maxValue = Integer.MIN_VALUE;
			for (Move move : board.getLegalMoves(isWhiteTurn)) {
				int currentValue = minimax(board, false, depth - 1, alpha, beta);
				maxValue = Math.max(currentValue, maxValue);
				alpha = Math.max(alpha, maxValue);
				if (beta <= alpha) {
					break;
				}
			}
			return maxValue;
		} else {
			int minValue = Integer.MAX_VALUE;
			for (Move move : board.getLegalMoves(isWhiteTurn)) {
				int currentValue = minimax(board, true, depth - 1, alpha, beta);
				minValue = Math.min(currentValue, minValue);
				beta = Math.min(beta, minValue);
				if (beta <= alpha) {
					break;
				}
			}
			return minValue;
		}
	}
//
//	public int min(Board board, int depth) {
//		if (depth == 0) {
//			return boardEvaluator.evaluate(board, depth);
//		}
//		int min = Integer.MAX_VALUE;
//		for (Move move : board.getLegalMoves(board.isWhiteTurn)) {
//			int currentValue = max(board, depth - 1);
//			if (currentValue <= min) {
//				min = currentValue;
//			}
//		}
//		return min;
//	}
//
//	public int max(Board board, int depth) {
//		if (depth == 0) {
//			return this.boardEvaluator.evaluate(board, depth);
//		}
//		int max = Integer.MIN_VALUE;
//		for (Move move : board.getLegalMoves(board.isWhiteTurn)) {
//			int currentValue = min(board, depth - 1);
//			if (currentValue >= max) {
//				max = currentValue;
//			}
//		}
//		return max;
//	}

}
