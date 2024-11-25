package model.pieces;

import java.awt.image.BufferedImage;

public class Knight extends Piece {
	BufferedImage img;
	public Knight(int col, int row, boolean isWhite) {
		super(col, row, isWhite);
		if (isWhite) {
			img = getImage("/pieces/white-knight.png");
		} else {
			img = getImage("/pieces/black-knight.png");
		}

	}

	private final static int[] moves = { -17, -15, -10, -6, 6, 10, 15, 17 };

}
