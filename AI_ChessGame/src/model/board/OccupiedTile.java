package model.board;

import model.pieces.Piece;

public class OccupiedTile extends Tile {

	private Piece pieceOnTile;
	
	public OccupiedTile(int tileCoordinate, Piece pieceOnTile) {
		super(tileCoordinate);
		this.pieceOnTile = pieceOnTile;
	}

	@Override
	public boolean isOccupied() {
		return true;
	}

	@Override
	public Piece getPiece() {
		return this.pieceOnTile;
	}

}
