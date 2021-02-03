package dev.negrel.fxchess.game;

import dev.negrel.fxchess.game.board_exception.IllegalMoveException;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;
import org.jetbrains.annotations.NotNull;

public abstract class Piece {
	public final Color color;
	protected final ChessBoard board;
	protected Coord coord;


	public Piece(@NotNull ChessBoard board, @NotNull Coord coord, @NotNull Color color) throws IllegalPositionException {
		this.board = board;
		this.color = color;
		this.coord = coord;

		this.board.setOccupation(coord, true);
	}

	public Coord getCoord() {
		return coord;
	}

	protected abstract boolean isValidMove(Coord c);

	public void move(Coord c) throws IllegalMoveException, IllegalPositionException {
		if (!isValidMove(c)) {
			throw new IllegalMoveException(this, c);
		}

		board.setOccupation(coord, false);
		board.setOccupation(c, true);
		coord = c;
	}
}
