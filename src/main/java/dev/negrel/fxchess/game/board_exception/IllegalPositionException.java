package dev.negrel.fxchess.game.board_exception;

import dev.negrel.fxchess.game.Coord;

/**
 * IllegalMoveException are thrown when a coordinate is not between 0 and 8.
 */
public class IllegalPositionException extends Exception {
	public IllegalPositionException(Coord c) {
		super(c.toString());
	}
}
