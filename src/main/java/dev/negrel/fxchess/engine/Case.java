package dev.negrel.fxchess.engine;

import java.io.Serializable;

/**
 * Case defines a case on the Chessboard.
 */
public class Case implements Serializable {
	private Movable movable;

	Case(Movable m) {
		this.movable = m;
	}

	Case() {
		this(null);
	}

	/**
	 * Check that this Case don't contain a Movable.
	 *
	 * @return true if the case is free or false otherwise.
	 */
	public boolean isOccupied() {
		return this.movable != null;
	}

	/**
	 * @return the Movable present on this Case.
	 */
	public Movable getMovable() {
		return this.movable;
	}

	/**
	 * Sets the movable present on this Case.
	 *
	 * @param m is the Movable to place on this Case.
	 */
	public void setMovable(Movable m) {
		this.movable = m;
	}
}
