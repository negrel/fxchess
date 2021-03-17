package dev.negrel.fxchess.engine;

import dev.negrel.fxchess.engine.board_exception.IllegalMoveException;
import dev.negrel.fxchess.engine.board_exception.IllegalPositionException;

import java.io.Serializable;

/*
 * Movable is a generic interface that defines any movable object.
 */
public interface Movable extends Serializable {
	void move(Coord pos) throws IllegalMoveException, IllegalPositionException;
}
