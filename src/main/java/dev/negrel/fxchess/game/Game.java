package dev.negrel.fxchess.game;

import dev.negrel.fxchess.game.board_exception.IllegalMoveException;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;

import java.util.ArrayList;

public class Game {
	private final ArrayList<Move> moves;
	private final NotationParser parser;
	private final ChessBoard board = new ChessBoard();

	public Game(NotationParser parser) {
		moves = new ArrayList<Move>();
		this.parser = parser;
		board.init();
	}

	public Game() {
		this(new ICCFNotationParser());
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
		playMove(m);
	}

	private void playMove(Move m) throws IllegalPositionException, IllegalMoveException {
		m.playOn(board);
		moves.add(m);
	}

}
