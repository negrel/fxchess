package dev.negrel.fxchess.game.piece;

import dev.negrel.fxchess.game.ChessBoard;
import dev.negrel.fxchess.game.Color;
import dev.negrel.fxchess.game.Coord;
import dev.negrel.fxchess.game.Piece;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * King defines the king chess piece.
 *
 * @see <a href="https://www.chess.com/terms/chess-pieces#king">https://www.chess.com/terms/chess-pieces#king</a>
 */
public class King extends Piece {
	public King(@NotNull ChessBoard board, @NotNull Coord coord, @NotNull Color color) throws IllegalPositionException {
		super(board, coord, color);
	}

	@Override
	protected boolean isValidMove(@NotNull Coord destination) {
		int diffX = Math.abs(destination.getX() - coord.getX());
		int diffY = Math.abs(destination.getY() - coord.getY());

		if (diffX == 0 && diffY == 0)
			return false;

		return diffX <= 1 && diffY <= 1;
	}

	public char toChar() {
		return 'K';
	}

	@Override
	public List<Coord> legalMove() {
		List<Coord> result = new ArrayList<>();

		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (i == 0 && j == 0)
					continue;

				Coord c = new Coord(coord.getX() + j, coord.getY() + i);
				if (isLegalMove(c))
					result.add(c);
			}
		}

		return result;
	}
}


