package model.board;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import model.pieces.*;

public class Board {
	final int MAX_COL = 8;
	final int MAX_ROW = 8;
	public static final int TILE_SIZE = 64;
	public static final int HALF_TILE_SIZE = TILE_SIZE / 2;

	private String fenString = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
	ArrayList<Piece> pieceList = new ArrayList<>();

	private void loadPiecePosByFEN(String fenString) {
		String[] ranks = fenString.split(" ");
		int col = 0;
		int row = 0;
		String currentRank = ranks[0];
		for (int i = 0; i < currentRank.length(); i++) {
			char ch = currentRank.charAt(i);
			if (ch == '/') {
				row++;
				col = 0;
			} else if (Character.isDigit(ch)) {
				col += Character.getNumericValue(ch);
			} else {
				boolean isWhite = Character.isUpperCase(ch);
				char pieceChar = Character.toLowerCase(ch);

				switch (pieceChar) {
				case 'r': {
					pieceList.add(new Rook(row, col, isWhite));
					break;
				}
				case 'n': {
					pieceList.add(new Knight(row, col, isWhite));
					break;
				}
				case 'b': {
					pieceList.add(new Bishop(row, col, isWhite));
					break;
				}
				case 'q': {
					pieceList.add(new Queen(row, col, isWhite));
					break;
				}
				case 'k': {
					pieceList.add(new King(row, col, isWhite));
					break;
				}
				case 'p': {
					pieceList.add(new Pawn(row, col, isWhite));
					break;
				}
				}
				col++;
			}

		}

	}

	public void draw(Graphics2D g2) {
		// DRAW CHESS BOARD
		for (int row = 0; row < MAX_ROW; row++) {
			for (int col = 0; col < MAX_COL; col++) {
				if ((row + col) % 2 == 0) {
					g2.setColor(Color.decode("#FFFACD"));
				} else {
					g2.setColor(Color.decode("#779556"));
				}
				g2.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			}
		}

		loadPiecePosByFEN(fenString);
//		Piece p1 = new Bishop(0, 1, true);
//		Piece p2 = new Bishop(0, 5, false);
//		pieceList.add(p1);
//		pieceList.add(p2);
		// DRAW CHESS PIECES
		for (Piece p : pieceList) {
			p.draw(g2);
		}

	}
}
