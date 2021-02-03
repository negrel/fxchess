package dev.negrel.fxchess;

import org.jetbrains.annotations.NotNull;

public class Queen extends Piece {
	public Queen(@NotNull ChessBoard board, @NotNull Coord coord, @NotNull Color color) throws IllegalPositionException {
		super(board, coord, color);
	}

	@Override
	protected boolean isValidMove(Coord c) {
		int diffX = Math.abs(c.getX() - coord.getX());
		int diffY = Math.abs(c.getY() - coord.getY());

		if (diffX == 0 && diffY == 0)
			return false;

		return diffX == diffY || (diffX > 0 && diffY == 0) || (diffY > 0 && diffX == 0);
	}
}


