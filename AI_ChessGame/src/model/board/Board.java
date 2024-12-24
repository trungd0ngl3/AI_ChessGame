package model.board;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import model.board.ai.MiniMax;
import model.pieces.*;

public class Board extends JPanel {
	public final static int MAX_COL = 8;
	public final static int MAX_ROW = 8;

	public static final int TILE_SIZE = 64;
	public static final int HALF_TILE_SIZE = TILE_SIZE / 2;

	static final int WHITE = 1;
	static final int BLACK = 0;

	public boolean isWhiteTurn = true;

	int currentColor = -1;

	private MouseListener mouse;
	public CheckChecker checker;
	private MiniMax minimax;
	private String fenString = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
	private ArrayList<Piece> pieceList;

	public boolean status = true;

	private Piece selectedPiece = new King(this, 10, 10, true);

	private int enPassantTile = -1;

	public Board() {
		mouse = new MouseListener(this);
		pieceList = new ArrayList<>();
		checker = new CheckChecker(this);
		minimax = new MiniMax();

		loadPiecePosByFEN(fenString);
		setPreferredSize(new Dimension(800, 512));
		setBackground(Color.black);
		addMouseMotionListener(mouse);
		addMouseListener(mouse);
	}

	public void changeTurn() {
		status = false;
		minimax.execute(this, 1);
		this.isWhiteTurn = !isWhiteTurn;
	}

	public Piece getPiece(int col, int row) {
		for (Piece piece : pieceList) {
			if (piece.getCol() == col && piece.getRow() == row) {
				return piece;
			}
		}
		return null;
	}

	public List<Move> getLegalMoves(boolean currentTurn) {
		List<Move> legalMoves = new ArrayList<>();
		for (Piece p : pieceList) {
			for (int c = 0; c < 8; c++) {
				for (int r = 0; r < 8; r++) {
					if (p.getCol() == c && p.getRow() == r) {
						continue;
					}
					Move move = new Move(this, c, r, p);
					if (isValidMove(move) && p.isWhite() == currentTurn) {
						legalMoves.add(move);
					}
				}
			}
		}
		return legalMoves;
	}

	// make move
	public void makeMove(Move move) {
		if (move.getPiece().getName().equals("pawn")) {
			movePawn(move);
		} else if (move.getPiece().getName().equals("king")) {
			moveKing(move);
		}
		move.getPiece().setCol(move.getNewCol());
		move.getPiece().setRow(move.getNewRow());

		move.getPiece().setxPos(move.getNewCol() * TILE_SIZE);
		move.getPiece().setyPos(move.getNewRow() * TILE_SIZE);

		move.getPiece().isFirstMove = false;
		pieceList.remove(move.getEnemy());
		changeTurn();
		repaint();
	}

	public void remakeMove(Move move) {

	}

	public boolean isGameOver() {
		return true;
	}

	// make king's move
	private void moveKing(Move move) {
		if (Math.abs(move.getPiece().getCol() - move.getNewCol()) == 2) {
			Piece rook;
			if (move.getPiece().getCol() < move.getNewCol()) {
				rook = getPiece(7, move.getPiece().getRow());
				rook.setCol(5);
			} else {
				rook = getPiece(0, move.getPiece().getRow());
				rook.setCol(3);
			}
			rook.setxPos(rook.getCol() * TILE_SIZE);
		}
	}

	// make pawn's movement (en passant & promotion)
	private void movePawn(Move move) {
		int colorIndex;
		if (move.getPiece().isWhite()) {
			colorIndex = 1;
		} else
			colorIndex = -1;

		if (getTileNum(move.getNewCol(), move.getNewRow()) == enPassantTile) {
			move.setEnemy(getPiece(move.getNewCol(), move.getNewRow() + colorIndex));
		}
		if (Math.abs(move.getPiece().getRow() - move.getNewRow()) == 2) {
			this.enPassantTile = getTileNum(move.getNewCol(), move.getNewRow() + colorIndex);
		} else {
			this.enPassantTile = -1;
		}
	}

	public int getTileNum(int col, int row) {
		return row * MAX_ROW + col;
	}

	// check if the move is valid
	public boolean isValidMove(Move move) {
		if (!canMove(move)) {
			return false;
		}
		if (move.getNewCol() >= MAX_COL || move.getNewRow() >= MAX_ROW) {
			return false;
		}
		if (isSameTeam(move.getPiece(), move.getEnemy())) {
			return false;
		}
		if (!move.getPiece().isValidMovement(move.getNewCol(), move.getNewRow())) {
			return false;
		}
		if (move.getPiece().isCollideWithPiece(move.getNewCol(), move.getNewRow())) {
			return false;
		}
		if (checker.isKingChecked(move)) {
			return false;
		}
		return true;
	}

	// check player's turn can move or not
	private boolean canMove(Move move) {
		return (isWhiteTurn == selectedPiece.isWhite() || !isWhiteTurn == !selectedPiece.isWhite());
	}

	// check 2 pieces is the same team or not
	public boolean isSameTeam(Piece p1, Piece p2) {
		if (p1 == null || p2 == null) {
			return false;
		}
		return p1.isWhite() == p2.isWhite();
	}

	public Piece findKing(boolean isWhite) {
		for (Piece p : pieceList) {
			if (p.getName().equals("king") && isWhite == p.isWhite()) {
				return p;
			}
		}
		return null;
	}

	// load pieces positon by FEN String
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
					pieceList.add(new Rook(this, col, row, isWhite));
					break;
				}
				case 'n': {
					pieceList.add(new Knight(this, col, row, isWhite));
					break;
				}
				case 'b': {
					pieceList.add(new Bishop(this, col, row, isWhite));
					break;
				}
				case 'q': {
					pieceList.add(new Queen(this, col, row, isWhite));
					break;
				}
				case 'k': {
					pieceList.add(new King(this, col, row, isWhite));
					break;
				}
				case 'p': {
					pieceList.add(new Pawn(this, col, row, isWhite));
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
		// DRAW VALID MOVEMENT
		drawValidMovement(g2);
		// DRAW CHESS PIECES
		for (Piece p : pieceList) {
			p.draw(g2);
		}
	}

	private void drawValidMovement(Graphics2D g2) {
		if (selectedPiece != null) {
			for (int c = 0; c < MAX_ROW; c++) {
				for (int r = 0; r < MAX_COL; r++) {
					if (isValidMove(new Move(this, c, r, selectedPiece))) {
						g2.setColor(Color.green);
						g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
						g2.fillRect(c * Board.TILE_SIZE, r * Board.TILE_SIZE, 65, 65);
						g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
					}

				}
			}
		}
	}

	// DRAW CHESS BOARD
	public void draw(Graphics2D g2) {
		for (int row = 0; row < MAX_ROW; row++) {
			for (int col = 0; col < MAX_COL; col++) {
				if ((row + col) % 2 == 0) {
					g2.setColor(Color.decode("#FFFACD"));
				} else {
					g2.setColor(Color.decode("#7A6758"));
				}
				g2.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			}
		}

	}

	public int getEnPassantTile() {
		return enPassantTile;
	}

	public void setEnPassantTile(int enPassantTile) {
		this.enPassantTile = enPassantTile;
	}

	public Piece getSelectedPiece() {
		return selectedPiece;
	}

	public void setSelectedPiece(Piece selectedPiece) {
		this.selectedPiece = selectedPiece;
	}

	public CheckChecker getChecker() {
		return checker;
	}

	public void setChecker(CheckChecker checker) {
		this.checker = checker;
	}

	public ArrayList<Piece> getPieceList() {
		return pieceList;
	}

}
