package model.pieces;


public class Knight extends Piece {
	public Knight(int col, int row, boolean isWhite) {
		super(col, row, isWhite);
	}

	private final static int[] moves = { -17, -15, -10, -6, 6, 10, 15, 17 };

}
