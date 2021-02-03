package dev.negrel.fxchess;

import org.jetbrains.annotations.NotNull;

public class Knight extends Piece {
	public Knight(@NotNull ChessBoard board, @NotNull Coord coord, @NotNull Color color) throws IllegalPositionException {
		super(board, coord, color);
	}

	@Override
	protected boolean isValidMove(Coord c) {
		int diffX = Math.abs(c.getX() - coord.getX());
		int diffY = Math.abs(c.getY() - coord.getY());

		return (diffX == 2 && diffY == 1) || (diffY == 2 && diffX == 1);
	}
}


