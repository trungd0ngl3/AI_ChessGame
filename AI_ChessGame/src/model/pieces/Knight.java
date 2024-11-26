package model.pieces;

import java.awt.image.BufferedImage;

public class Knight extends Piece {
	BufferedImage img;

	public Knight(int col, int row, boolean isWhite) {
		super(col, row, isWhite);
		name = "knight";
		value = 3;
		if (isWhite) {
			img = getImage("/pieces/white-knight.png");
		} else {
			img = getImage("/pieces/black-knight.png");
		}

	}

}
