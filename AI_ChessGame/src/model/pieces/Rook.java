package model.pieces;

import java.awt.image.BufferedImage;

public class Rook extends Piece {
	BufferedImage img;

	public Rook(int col, int row, boolean isWhite) {
		super(col, row, isWhite);
		if (isWhite) {
			img = getImage("/pieces/white-rook.png");
		} else {
			img = getImage("/pieces/black-rook.png");
		}

	}

}
