package model.pieces;

public class Bishop extends Piece {
	public Bishop(int col, int row, boolean isWhite) {
		super(col, row, isWhite);
		if (isWhite) {
			img = getImage("/pieces/white-bishop.png");
		} else {
			img = getImage("/pieces/black-bishop.png");
		}

	}

}
