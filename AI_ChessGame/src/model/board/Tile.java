package model.board;

import java.util.HashMap;
import java.util.Map;

import model.pieces.Piece;

public abstract class Tile {
	protected int tileCoordinate;

	private static Map<Integer, EmptyTile> EMPTY_TILES = createEmptTiles();

	public Tile(int tileCoordinate) {
		this.tileCoordinate = tileCoordinate;
	}

	protected static Map<Integer, EmptyTile> createEmptTiles() {

		Map<Integer, EmptyTile> emptyTileMap = new HashMap<Integer, EmptyTile>();

		for (int i = 0; i < 64; i++) {
			emptyTileMap.put(i, new EmptyTile(i));
		}

		return emptyTileMap;
	}

	public Tile createTile(int tileCoordinate, Piece piece) {
		if (piece != null) {
			return new OccupiedTile(tileCoordinate, piece);
		}
		else
			return EMPTY_TILES.get(tileCoordinate);

	}

	public abstract boolean isOccupied();

	public abstract Piece getPiece();
}
