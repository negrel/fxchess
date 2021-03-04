package dev.negrel.fxchess.game;

import dev.negrel.fxchess.game.board_exception.IllegalMoveException;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Piece is a generic class that define a chess Piece.
 * All the pieces defined in the piece package inherit from
 * this class.
 */
public abstract class Piece implements Movable, Serializable {
	/**
	 * The color of this piece (white or black).
	 */
	public final Color color;
	/**
	 * The chess board on which this piece is.
	 */
	protected final ChessBoard board;
	/**
	 * The coordinate of this piece on the chessboard.
	 */
	protected Coord coord;

	/**
	 * Instantiate a new Piece object.
	 *
	 * @param board The chess board on which this piece is.
	 * @param coord The coordinate of this piece on the chessboard.
	 * @param color The color of this piece (white or black).
	 * @throws IllegalPositionException if the given coordinate is out of the chessboard.
	 */
	public Piece(@NotNull ChessBoard board, @NotNull Coord coord, @NotNull Color color) throws IllegalPositionException {
		this.board = board;
		this.color = color;
		this.coord = coord;

		this.board.setOccupation(coord, this);
	}

	/**
	 * @return the coordinate of this piece on the chessboard.
	 * @see Coord
	 * @see ChessBoard
	 */
	public Coord getCoord() {
		return coord;
	}

	/**
	 * Chek whether the given coordinate is a valid move for the piece.
	 *
	 * @param destination The destination of the piece.
	 * @return true if the move is valid or false otherwise.
	 */
	protected abstract boolean isValidMove(Coord destination);

	protected abstract char toChar();

	/**
	 * Move the piece to the given position
	 *
	 * @param destination The destination of the piece.
	 * @throws IllegalMoveException     if the destination is an invalid move for this piece.
	 * @throws IllegalPositionException if the destination is out of the chessboard.
	 */
	public void move(Coord destination) throws IllegalMoveException, IllegalPositionException {
		if (!isValidMove(destination) || !isValidPath(destination)) {
			throw new IllegalMoveException(this, destination);
		}

		board.setOccupation(coord, null);
		board.setOccupation(destination, this);
		coord = destination;
	}

	protected boolean isValidPath(Coord destination) {
		Iterator<Case> it = this.board.getIterator(this.coord, destination);

		while (it.hasNext()) {
			Case c = it.next();

			if (it.hasNext() && c.isOccupied())
				return false;
		}

		return true;
	}


	@Override
	public String toString() {
		String color = "\33".concat(this.color.equals(Color.WHITE) ? "[30;107m" : "[97;40m");
		return color.concat(String.valueOf(toChar())).concat("\033[39;m");
	}

}
