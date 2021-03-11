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
 * Knight defines the knight chess piece.
 *
 * @see <a href="https://www.chess.com/terms/chess-pieces#knight">https://www.chess.com/terms/chess-pieces#knight</a>
 */
public class Knight extends Piece {
	public Knight(@NotNull ChessBoard board, @NotNull Coord coord, @NotNull Color color) throws IllegalPositionException {
		super(board, coord, color);
	}

	@Override
	public boolean isLegalMove(@NotNull Coord destination) {
		return isValidMove(destination) && isLegalDestination(destination);
	}

	@Override
	protected boolean isValidMove(@NotNull Coord destination) {
		int diffX = Math.abs(destination.getX() - coord.getX());
		int diffY = Math.abs(destination.getY() - coord.getY());

		return (diffX == 2 && diffY == 1) || (diffY == 2 && diffX == 1);
	}

	public char toChar() {
		return 'k';
	}

	@Override
	public List<Coord> legalMove() {
		List<Coord> result = new ArrayList<>();
		Coord[] coords = {
			new Coord(coord.getX() - 2, coord.getY() - 1),
			new Coord(coord.getX() - 2, coord.getY() + 1),
			new Coord(coord.getX() - 1, coord.getY() - 2),
			new Coord(coord.getX() - 1, coord.getY() + 2),
			new Coord(coord.getX() + 1, coord.getY() - 2),
			new Coord(coord.getX() + 1, coord.getY() + 2),
			new Coord(coord.getX() + 2, coord.getY() - 1),
			new Coord(coord.getX() + 2, coord.getY() + 1),
		};

		for (Coord coord : coords) {
			if (isLegalMove(coord))
				result.add(coord);
		}

		return result;
	}
}


