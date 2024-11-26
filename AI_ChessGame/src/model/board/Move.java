package model.board;

import model.pieces.Piece;

public class Move {
	private int oldCol, oldRow;
	private int newCol, newRow;
	private Piece piece, enemy;

	public Move(Board board, int newCol, int newRow, Piece piece) {
		super();
		this.oldCol = piece.getCol();
		this.oldRow = piece.getRow();
		this.newCol = newCol;
		this.newRow = newRow;
		this.piece = piece;
		this.enemy = board.getPiece(newCol, newRow);
	}

	public int getOldCol() {
		return oldCol;
	}

	public void setOldCol(int oldCol) {
		this.oldCol = oldCol;
	}

	public int getOldRow() {
		return oldRow;
	}

	public void setOldRow(int oldRow) {
		this.oldRow = oldRow;
	}

	public int getNewCol() {
		return newCol;
	}

	public void setNewCol(int newCol) {
		this.newCol = newCol;
	}

	public int getNewRow() {
		return newRow;
	}

	public void setNewRow(int newRow) {
		this.newRow = newRow;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public Piece getEnemy() {
		return enemy;
	}

	public void setEnemy(Piece enemy) {
		this.enemy = enemy;
	}
	
}
