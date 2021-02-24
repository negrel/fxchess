package dev.negrel.fxchess.game;

import dev.negrel.fxchess.game.board_exception.IllegalMoveException;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 * ChessBoard defines a chessboard that contains Piece object.
 * This object is responsible from holding the chess piece.
 */
public class ChessBoard {
	private static final ChessBoard singleton = new ChessBoard();

	private final Case[][] cases;
	private final ArrayList<Move> moves;

	/**
	 * Instantiates a new ChessBoard.
	 */
	private ChessBoard() {
		moves = new ArrayList<Move>();

		cases = new Case[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				cases[i][j] = new Case();
			}
		}
	}

	/**
	 * @return The ChessBoard singleton.
	 */
	public static ChessBoard getInstance() {
		return ChessBoard.singleton;
	}

	/**
	 * Plays the given chess Move.
	 *
	 * @param m The chess move.
	 * @throws IllegalPositionException if the move destination coordinates is out of board.
	 * @throws IllegalMoveException     if the piece to move can't do this move.
	 */
	public void play(Move m) throws IllegalPositionException, IllegalMoveException {
		m.playOn(this);
		moves.add(m);
	}

	public Iterator<Case> getIterator(@NotNull Coord from, @NotNull Coord to) {
		int diffX = Math.abs(to.getX() - from.getX());
		int diffY = Math.abs(to.getY() - from.getY());

		if (diffX == 0 && diffY == 0)
			return null;

		// Only diagonal, horizontal and vertical line are allowed
		if (!(diffX == diffY || (diffX > 0 && diffY == 0) || (diffY > 0 && diffX == 0)))
			return null;

		// Collect the Movable.
		ArrayList<Case> l = new ArrayList<Case>();
		for (int i = from.getX(); i < to.getX(); i++) {
			for (int j = from.getY(); j < to.getY(); j++) {
				try {
					Case c = getCase(new Coord(i, j));
					l.add(c);
				} catch (IllegalPositionException ignored) {
				}
			}
		}

		return l.iterator();
	}

	private void checkCoord(@NotNull Coord coord) throws IllegalPositionException {
		if (coord.getX() < 0 || coord.getX() >= 8 ||
			coord.getY() < 0 || coord.getY() >= 8)
			throw new IllegalPositionException(coord);

	}

	private Case getCase(@NotNull Coord coord) throws IllegalPositionException {
		checkCoord(coord);
		return cases[coord.getY()][coord.getX()];
	}

	public Movable getMovable(@NotNull Coord coord) throws IllegalPositionException {
		return getCase(coord).getMovable();
	}

	private void setMovable(@NotNull Coord coord, Movable m) throws IllegalPositionException {
		getCase(coord).setMovable(m);
	}

	/**
	 * Checks that the case at the given coordinate is not occupied.
	 *
	 * @param coord The coordinate of the case to check.
	 * @return true if the case is free or false otherwise.
	 * @throws IllegalPositionException if the given coordinate is out of the chessboard.
	 */
	public boolean isOccupied(Coord coord) throws IllegalPositionException {
		return getCase(coord).isOccupied();
	}

	/**
	 * Sets the occupation of the case at the given coordinate.
	 *
	 * @param coord The coordinate of the Case to update.
	 * @param m     The Movable to place on the Case.
	 * @throws IllegalPositionException if the given coordinate is out of the chessboard.
	 */
	public void setOccupation(Coord coord, Movable m) throws IllegalPositionException {
		setMovable(coord, m);
	}

	/**
	 * Prints the chessboard to the standard output.
	 */
	public void smartPrint() {
		System.out.println(this.toString());
	}

	/**
	 * Converts the chessboard to a String.
	 *
	 * @return the String representation of this.
	 */
	public String toString() {
		LineBuilder builder = new LineBuilder(8);

		for (int i = 0; i < cases.length; i++) {
			Case[] row = cases[i];

			for (Case c : row) {
				Movable m = c.getMovable();
				String p = m != null ? m.toString() : " ";
				builder.appendString(7 - i, p.concat(" | "));
			}
		}

		for (int i = 0; i < cases.length; i++) {
			builder.prependString(i, String.valueOf(8 - i).concat(" │ "));
		}
		builder.appendLine("──┼────────────────────────────────");
		builder.appendLine("0 │ 1   2   3   4   5   6   7   8");

		return builder.toString();
	}

	private static class LineBuilder extends Vector<StringBuffer> {
		public LineBuilder(int lineCount) {
			super(lineCount);

			for (int i = 0; i < lineCount; i++) {
				this.add(new StringBuffer());
			}
		}

		public void prependLine(String line) {
			insertElementAt(new StringBuffer(line), 0);
		}

		public void appendLine(String line) {
			addElement(new StringBuffer(line));
		}

		public void prependChar(int line, char c) {
			get(line).insert(0, c);
		}

		public void appendChar(int line, char c) {
			get(line).append(c);
		}

		public void setChar(int line, int pos, char c) {
			get(line).setCharAt(pos, c);
		}

		public void prependString(int line, String s) {
			for (int i = s.length() - 1; i >= 0; i--) {
				char c = s.charAt(i);
				prependChar(line, c);
			}
		}

		public void appendString(int line, String s) {
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				appendChar(line, c);
			}
		}

		@Override
		public synchronized String toString() {
			StringBuilder builder = new StringBuilder();

			for (StringBuffer line : this) {
				builder.append(line.toString().concat("\n"));
			}

			return builder.toString();
		}
	}
}
