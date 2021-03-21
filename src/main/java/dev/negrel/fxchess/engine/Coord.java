package dev.negrel.fxchess.engine;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Coord define a 2D coordinate.
 */
public class Coord implements Serializable {
	/**
	 * The coordinate on the X axis.
	 */
	private final int x;
	/**
	 * The coordinate on the Y axis.
	 */
	private final int y;

	/**
	 * Instantiate a new Coord object.
	 *
	 * @param x The coordinate on the X axis.
	 * @param y The coordinate on the Y axis.
	 */
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Instantiate a new Coord object.
	 *
	 * @param x The coordinate on the X axis.
	 * @param y The coordinate on the Y axis.
	 */
	public Coord(double x, double y) {
		this((int) x, (int) y);
	}

	public Coord() {
		this(0, 0);
	}

	private static ArrayList<Coord> getVLine(int x, int yStart, int yEnd) {
		ArrayList<Coord> l = new ArrayList<>();
		int inc = Integer.compare(yEnd, yStart);
		for (int i = yStart; i != yEnd; i += inc) {
			l.add(new Coord(x, i));
		}

		return l;
	}

	private static ArrayList<Coord> getHLine(int y, int xStart, int xEnd) {
		ArrayList<Coord> l = new ArrayList<>();
		int inc = Integer.compare(xEnd, xStart);
		for (int i = xStart; i != xEnd; i += inc) {
			l.add(new Coord(i, y));
		}

		return l;
	}

	private static ArrayList<Coord> getDiagonal(@NotNull Coord from, @NotNull Coord to) {
		ArrayList<Coord> l = new ArrayList<>();

		int diffX = to.getX() - from.getX();
		int incX = Integer.compare(diffX, 0);

		int diffY = to.getY() - from.getY();
		int incY = Integer.compare(diffY, 0);

		for (int i = 0; i != diffX; i += incX) {
			Coord c = new Coord(from.getX() + i, from.getY() + (i / incX * incY));
			l.add(c);
		}

		return l;
	}

	/**
	 * @param from the start coordinates
	 * @param to   the destination coordinates
	 * @return a list of the coordinate between from and to.
	 */
	public static ArrayList<Coord> getCoordsBetween(@NotNull Coord from, @NotNull Coord to) {
		int diffX = to.getX() - from.getX();
		int diffY = to.getY() - from.getY();
		ArrayList<Coord> l = new ArrayList<>();

		if (diffX == 0 && diffY == 0)
			return l;

		// Only diagonal, horizontal and vertical line are allowed
		if ((Math.abs(diffX) != Math.abs(diffY) && diffX != 0 && diffY != 0))
			return null;

		// Collect the Movable.
		if (diffX == 0 && diffY != 0) {
			l = getVLine(from.getX(), from.getY(), to.getY());
		} else if (diffY == 0 && diffX != 0) {
			l = getHLine(from.getY(), from.getX(), to.getX());
		} else if (Math.abs(diffX) == Math.abs(diffY)) {
			l = getDiagonal(from, to);
		}

		return l;
	}

	/**
	 * @param rawX the X coordinates
	 * @param rawY the Y coordinates
	 * @return a Coord object based on the given inputs.
	 */
	public static Coord fromString(@NotNull String rawX, @NotNull String rawY) {
		return new Coord(Integer.parseInt(rawX), Integer.parseInt(rawY));
	}

	/**
	 * @return the coordinate on the X axis.
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the coordinate on the Y axis.
	 */
	public int getY() {
		return y;
	}

	public boolean equals(@NotNull Coord other) {
		return this.getX() == other.getX() && this.getY() == other.getY();
	}

	public String toString() {
		return "{ " + x + ", " + y + " }";
	}
}
