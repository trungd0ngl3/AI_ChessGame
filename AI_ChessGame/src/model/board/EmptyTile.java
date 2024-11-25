package model.board;

import model.pieces.Piece;

public class EmptyTile extends Tile {

	public EmptyTile(int tileCoordinate) {
		super(tileCoordinate);
	}

	@Override
	public boolean isOccupied() {
		return false;
	}

	@Override
	public Piece getPiece() {
		return null;
	}

}
