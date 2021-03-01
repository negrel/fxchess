package dev.negrel.fxchess.game;

import dev.negrel.fxchess.game.board_exception.IllegalMoveException;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;

import java.util.Stack;

public class Game {
	private final Stack<Move> moves;
	private final NotationParser parser;
	private final ChessBoard board = new ChessBoard();

	public Game(NotationParser parser) {
		moves = new Stack<Move>();
		this.parser = parser;
		board.init();
	}

	public Game() {
		this(new ICCFNotationParser());
	}

	private Coord adaptCoord(Coord c) {
		return new Coord(c.getX() - 1, c.getY() - 1);
	}

	private Move adaptMove(Move m) {
		return new Move(adaptCoord(m.getFrom()), adaptCoord(m.getTo()));
	}

	/**
	 * Plays the given chess Move.
	 *
	 * @param rawMove A valid chess move for the NotationParser used by this.
	 * @throws IllegalPositionException if the move destination coordinates is out of board.
	 * @throws IllegalMoveException     if the piece to move can't do this move.
	 */
	public void play(String rawMove) throws IllegalPositionException, IllegalMoveException, InvalidNotationException {
		Move m = parser.parseMove(rawMove);
		m = adaptMove(m);

		playMove(m);
	}

	/**
	 * Cancel the last played move.
	 */
	public void unplay() {
		Move m = moves.pop();

		try {
			Movable piece = board.getMovable(m.getTo());
			if (piece == null) {
				throw new IllegalMoveException(null, m.getTo());
			}

			piece.move(m.getFrom());
		} catch (IllegalPositionException | IllegalMoveException ignored) {
		}
	}

	private void playMove(Move m) throws IllegalPositionException, IllegalMoveException {
		Movable piece = board.getMovable(m.getFrom());
		if (piece == null) {
			throw new IllegalMoveException(null, m.getFrom());
		}

		piece.move(m.getTo());
		moves.push(m);
	}

	public void smartPrint() {
		this.board.smartPrint();
	}

}
