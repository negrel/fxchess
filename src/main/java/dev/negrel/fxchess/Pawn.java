package dev.negrel.fxchess;

import org.jetbrains.annotations.NotNull;

public class Pawn extends Piece {
	public Pawn(@NotNull ChessBoard board, @NotNull Coord coord, @NotNull Color color) throws IllegalPositionException {
		super(board, coord, color);
	}

	@Override
	protected boolean isValidMove(Coord c) {
		int diffX = c.getX() - coord.getX();
		int diffY = c.getY() - coord.getY();

		int y = (color == Color.BLACK ? 1 : -1);
		return diffX == 0 && diffY == y;
	}
}


