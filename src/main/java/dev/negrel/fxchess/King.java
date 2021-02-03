package dev.negrel.fxchess;

import org.jetbrains.annotations.NotNull;

public class King extends Piece {
	public King(@NotNull ChessBoard board, @NotNull Coord coord, @NotNull Color color) throws IllegalPositionException {
		super(board, coord, color);
	}

	@Override
	protected boolean isValidMove(Coord c) {
		int diffX = Math.abs(c.getX() - coord.getX());
		int diffY = Math.abs(c.getY() - coord.getY());

		if (diffX == 0 && diffY == 0)
			return false;

		return diffX <= 1 && diffY <= 1;
	}
}


