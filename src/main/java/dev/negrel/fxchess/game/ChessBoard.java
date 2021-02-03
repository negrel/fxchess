package dev.negrel.fxchess.game;

import dev.negrel.fxchess.game.board_exception.IllegalPositionException;
import org.jetbrains.annotations.NotNull;

import java.util.Vector;

/**
 * ChessBoard define a chessboard that contains Piece object.
 * This object is responsible from holding the chess piece.
 */
public class ChessBoard {
	private final int[][] cases;

	/**
	 * Instantiate a new ChessBoard.
	 */
	public ChessBoard() {
		cases = new int[8][8];
	}

	private void checkCoord(@NotNull Coord coord) throws IllegalPositionException {
		if (coord.getX() < 0 || coord.getX() >= 8 ||
			coord.getY() < 0 || coord.getY() >= 8)
			throw new IllegalPositionException(coord);

	}

	private int getCase(@NotNull Coord coord) throws IllegalPositionException {
		checkCoord(coord);
		return cases[coord.getY()][coord.getX()];
	}

	private void setCase(@NotNull Coord coord, int n) throws IllegalPositionException {
		checkCoord(coord);

		cases[coord.getY()][coord.getX()] = n;
	}

	/**
	 * Check that the case at the given coordinate is not occupied.
	 * @param coord The coordinate of the case to check.
	 * @return true if the case is free or false otherwise.
	 * @throws IllegalPositionException if the given coordinate is out of the chessboard.
	 */
	public boolean isOccupied(Coord coord) throws IllegalPositionException {
		return getCase(coord) == 1;
	}

	/**
	 * Set the occupation of the case at the given coordinate.
	 * @param coord The coordinate of the case to update.
	 * @param in Whether or not the case is occupied.
	 * @throws IllegalPositionException if the given coordinate is out of the chessboard.
	 */
	public void setOccupation(Coord coord, boolean in) throws IllegalPositionException {
		int n = in ? 1 : 0;
		setCase(coord, n);
	}

	/**
	 * Print the chessboard to the standard output.
	 */
	public void smartPrint() {
		System.out.println(this.toString());
	}

	/**
	 * Convert the chessboard to a String.
	 * @return the String representation of this.
	 */
	public String toString() {
		LineBuilder builder = new LineBuilder(8);

		for (int i = 0; i < cases.length; i++) {
			int[] row = cases[i];

			for (int c : row) {
				builder.appendString(7 - i, String.valueOf(c).concat(" | "));
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
