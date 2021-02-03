package dev.negrel.fxchess.game.board_exception;

import dev.negrel.fxchess.game.Coord;
import dev.negrel.fxchess.game.Piece;

/**
 * IllegalMoveException are thrown when a piece try to do an illegal move.
 * For example, a pawn that try to go backward will throw an IllegalMoveException.
 */
public class IllegalMoveException extends Exception {
	public IllegalMoveException(Piece p, Coord c) {
		super(String.format("Piece %s at %s can't move at %s", p, p.getCoord().toString(), c.toString()));
	}
}
