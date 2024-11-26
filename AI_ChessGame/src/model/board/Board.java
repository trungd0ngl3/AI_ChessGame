package model.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.pieces.*;

public class Board extends JPanel implements Runnable {
	final int MAX_COL = 8;
	final int MAX_ROW = 8;

	public static final int TILE_SIZE = 64;
	public static final int HALF_TILE_SIZE = TILE_SIZE / 2;

	private Thread gameThread;
	private MouseListener mouse;
	private String fenString = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
	private ArrayList<Piece> pieceList;

	private Piece selectedPiece;

	public Board() {
		mouse = new MouseListener(this);
		pieceList = new ArrayList<>();
		loadPiecePosByFEN(fenString);
		setPreferredSize(new Dimension(800, 512));
		setBackground(Color.black);
		addMouseMotionListener(mouse);
		addMouseListener(mouse);
	}

	public void runGame() {
		this.gameThread = new Thread(this);
		gameThread.start();
	}

	public Piece getPiece(int col, int row) {
		for (Piece piece : pieceList) {
			if (piece.getCol() == col && piece.getRow() == row) {
				return piece;
			}
		}
		return null;
	}

	public void makeMove(Move move) {
		move.getPiece().setCol(move.getNewCol());
		move.getPiece().setRow(move.getNewRow());
		move.getPiece().setxPos(move.getNewCol() * TILE_SIZE);
		move.getPiece().setyPos(move.getNewRow() * TILE_SIZE);
		pieceList.remove(move.getEnemy());
	}

	public boolean isValidMove(Move move) {
		if (isSameTeam(move.getPiece(), move.getEnemy())) {
			return false;
		} else
			return true;
	}

	private boolean isSameTeam(Piece p1, Piece p2) {
		if (p1 == null || p2 == null) {
			return false;
		}
		return p1.isWhite() == p2.isWhite();
	}

	private void loadPiecePosByFEN(String fenString) {
		String[] parts = fenString.split(" ");
		int col = 0;
		int row = 0;
		String currentRank = parts[0];
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
					pieceList.add(new Rook(col, row, isWhite));
					break;
				}
				case 'n': {
					pieceList.add(new Knight(col, row, isWhite));
					break;
				}
				case 'b': {
					pieceList.add(new Bishop(col, row, isWhite));
					break;
				}
				case 'q': {
					pieceList.add(new Queen(col, row, isWhite));
					break;
				}
				case 'k': {
					pieceList.add(new King(col, row, isWhite));
					break;
				}
				case 'p': {
					pieceList.add(new Pawn(col, row, isWhite));
					break;
				}
				}
				col++;
			}

		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// DRAW BOARD
		draw(g2);
		// DRAW CHESS PIECES
		for (Piece p : pieceList) {
			p.draw(g2);
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
	}

	// GAME LOOP
	@Override
	public void run() {
		double drawInterval = 1000000000 / 60;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if (delta >= 1) {
//				update();
				repaint();
				delta--;
			}
		}
	}

	public Piece getSelectedPiece() {
		return selectedPiece;
	}

	public void setSelectedPiece(Piece selectedPiece) {
		this.selectedPiece = selectedPiece;
	}

}
