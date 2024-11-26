package model.pieces;

import java.awt.image.BufferedImage;

public class King extends Piece {
	BufferedImage img;

	public King(int col, int row, boolean isWhite) {
		super(col, row, isWhite);
		name = "king";
		value = 999;
		if (isWhite) {
			img = getImage("/pieces/white-king.png");
		} else {
			img = getImage("/pieces/black-king.png");
		}

	}

}
