package dev.negrel.fxchess;

import org.jetbrains.annotations.NotNull;

import java.util.Vector;

public class ChessBoard {
	int[][] cases;

	public ChessBoard() {
		cases = new int[8][8];
	}

	private int getCase(@NotNull Coord coord) {
		return cases[coord.getY()][coord.getX()];
	}

	private void setCase(@NotNull Coord coord, int n) {
		cases[coord.getY()][coord.getX()] = n;
	}

	public boolean isOccupied(Coord coord) {
		return getCase(coord) == 1;
	}

	public void setOccupation(Coord coord, boolean in) {
		int n = 0;
		if (in) n = 1;

		setCase(coord, n);
	}

	public void smartPrint() {
		System.out.println(this.toString());
	}

	public String toString() {
		LineBuilder builder = new LineBuilder(8);

		for (int i = 0; i < cases.length; i++) {
			int[] row = cases[i];

			for (int c : row) {
				builder.appendString(i, String.valueOf(c).concat(" | "));
			}
		}

		for (int i = 0; i < cases.length; i++) {
			builder.prependString(i, String.valueOf(8 - i).concat(" │ "));
		}
		builder.appendLine("──┼────────────────────────────────");
		builder.appendLine("0 │ 1   2   3   4   5   6   7   8");

		return builder.toString();
	}

	private class LineBuilder extends Vector<StringBuffer> {
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
