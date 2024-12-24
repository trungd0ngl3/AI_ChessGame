package model.board;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.pieces.Piece;

public class MouseListener extends MouseAdapter {
	private Board board;

	public MouseListener(Board board) {
		this.board = board;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int col = e.getX() / board.TILE_SIZE;
		int row = e.getY() / board.TILE_SIZE;
		Piece pieceXY = board.getPiece(col, row);
		if (pieceXY != null) {
			board.setSelectedPiece(pieceXY);
			board.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int col = e.getX() / board.TILE_SIZE;
		int row = e.getY() / board.TILE_SIZE;
		Piece selectedPiece = board.getSelectedPiece();
		if (selectedPiece != null) {
			Move move = new Move(board, col, row, selectedPiece);
			if (board.isValidMove(move)) {
				board.makeMove(move);
			} else {
				selectedPiece.setxPos(selectedPiece.getCol() * board.TILE_SIZE);
				selectedPiece.setyPos(selectedPiece.getRow() * board.TILE_SIZE);
			}
		}
		board.repaint();
		board.setSelectedPiece(null);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (board.getSelectedPiece() != null) {
			board.getSelectedPiece().setxPos(e.getX() - board.HALF_TILE_SIZE);
			board.getSelectedPiece().setyPos(e.getY() - board.HALF_TILE_SIZE);
			board.repaint();
		}

	}

}
