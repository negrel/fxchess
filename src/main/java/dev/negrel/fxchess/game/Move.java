package dev.negrel.fxchess.game;

import dev.negrel.fxchess.game.board_exception.IllegalMoveException;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;
import org.jetbrains.annotations.NotNull;

/*
 * Move defines a chess move.
 */
public class Move {
	private final Coord from;
	private final Coord to;

	public Move(@NotNull Coord from, @NotNull Coord to) {
		this.from = from;
		this.to = to;
	}

	protected void reversePlayOn(ChessBoard board) throws IllegalPositionException, IllegalMoveException {
		board.getMovable(to).move(from);
	}

	protected void playOn(ChessBoard board) throws IllegalPositionException, IllegalMoveException {
		board.getMovable(from).move(to);
	}
}
