package model.pieces;

import java.awt.image.BufferedImage;

public class Queen extends Piece {
	BufferedImage img;

	public Queen(int col, int row, boolean isWhite) {
		super(col, row, isWhite);
		name = "queen";
		value = 9;
		if (isWhite) {
			img = getImage("/pieces/white-queen.png");
		} else {
			img = getImage("/pieces/black-queen.png");
		}

	}

}
