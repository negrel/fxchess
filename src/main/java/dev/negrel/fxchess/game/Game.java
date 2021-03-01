package dev.negrel.fxchess.game;

import dev.negrel.fxchess.game.board_exception.IllegalMoveException;
import dev.negrel.fxchess.game.board_exception.IllegalPositionException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
	private final ArrayList<String> moves;
	private final NotationParser parser;
	private final ChessBoard board = new ChessBoard();

	public Game(NotationParser parser) {
		moves = new ArrayList<String>();
		this.parser = parser;
		board.init();
	}

	public Game() {
		this(new ICCFNotationParser());
	}

	/**
	 * Write the played moves of this Game to a file.
	 *
	 * @param filename the name of the file.
	 * @throws IOException if we can't write to the file.
	 */
	public void writeToFile(String filename) throws IOException {
		FileWriter fileWriter = new FileWriter(filename);
		PrintWriter printWriter = new PrintWriter(fileWriter);

		for (String rawMove : getMoves()) {
			printWriter.println(rawMove);
		}
		printWriter.flush();

		printWriter.close();
		fileWriter.close();
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

		moves.add(rawMove);
	}

	private void playMove(Move m) throws IllegalPositionException, IllegalMoveException {
		Movable piece = board.getMovable(m.getFrom());
		if (piece == null) {
			throw new IllegalMoveException(null, m.getFrom());
		}

		piece.move(m.getTo());
	}

	public ArrayList<String> getMoves() {
		return moves;
	}

	public void smartPrint() {
		this.board.smartPrint();
	}
}
