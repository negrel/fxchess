package dev.negrel.fxchess.game;

/**
 * Coord define a 2D coordinate.
 */
public class Coord {
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
	 * @param x The coordinate on the X axis.
	 * @param y The coordinate on the Y axis.
	 */
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Coord() {
		this(0, 0);
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

	public String toString() {
		return "{ " + x + ", " + y + " }";
	}
}
