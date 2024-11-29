package model.pieces;

import java.awt.image.BufferedImage;

import model.board.Board;

public class Pawn extends Piece {
	BufferedImage img;

	public Pawn(Board board,int col, int row, boolean isWhite) {
		super(board, col, row, isWhite);
		name = "pawn";
		value = 1;
		if (isWhite) {
			img = getImage("/pieces/white-pawn.png");
		} else {
			img = getImage("/pieces/black-pawn.png");
		}

	}

	@Override
	public boolean isValidMovement(int newCol, int newRow) {
		return true;
	}

	public boolean isCollideWithPiece(int newCol, int newRow) {
		// TODO Auto-generated method stub
		return false;
	}


}