package model.board;

import model.pieces.Piece;

public class Move {
	private int oldCol, oldRow;
	private int newCol, newRow;
	private Piece piece, alliance;
	public Move(int newCol, int newRow, Piece piece) {
		super();
		this.oldCol = piece.getCol();
		this.oldRow = piece.getRow();
		this.newCol = newCol;
		this.newRow = newRow;
		this.piece = piece;
	}
	
	
}
