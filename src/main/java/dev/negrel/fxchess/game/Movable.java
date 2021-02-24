package dev.negrel.fxchess.game;

import dev.negrel.fxchess.game.board_exception.IllegalMoveException;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;

/**
 * Movable is a generic interface that defines any movable object.
 */
public interface Movable {
		void move(Coord pos) throws IllegalMoveException, IllegalPositionException;
}
