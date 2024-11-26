package model.pieces;

import java.awt.image.BufferedImage;
public class Bishop extends Piece {
	BufferedImage img;
	public Bishop(int col, int row, boolean isWhite) {
		super(col, row, isWhite);
		name = "bishop";
		value = 3;
		if (isWhite) {
			img = getImage("/pieces/white-bishop.png");
		} else {
			img = getImage("/pieces/black-bishop.png");
		}

	}

}
