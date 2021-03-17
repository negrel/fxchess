package dev.negrel.fxchess.engine.board_exception;

import dev.negrel.fxchess.engine.Coord;

/**
 * IllegalMoveException are thrown when a coordinate is not between 0 and 8.
 */
public class IllegalPositionException extends Exception {
	public IllegalPositionException(Coord c) {
		super(c.toString());
	}
}
