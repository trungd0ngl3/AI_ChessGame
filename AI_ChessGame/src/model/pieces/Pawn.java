package model.pieces;

import java.awt.image.BufferedImage;

public class Pawn extends Piece {
	BufferedImage img;

	public Pawn(int col, int row, boolean isWhite) {
		super(col, row, isWhite);
		if (isWhite) {
			img = getImage("/pieces/white-pawn.png");
		} else {
			img = getImage("/pieces/black-pawn.png");
		}

	}

}