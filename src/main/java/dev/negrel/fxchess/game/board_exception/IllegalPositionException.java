package dev.negrel.fxchess.game.board_exception;

import dev.negrel.fxchess.game.Coord;

public class IllegalPositionException extends Exception {
	public IllegalPositionException(Coord c) {
		super(c.toString());
	}
}
