package dev.negrel.fxchess.engine;

import dev.negrel.fxchess.engine.board_exception.IllegalMoveException;
import dev.negrel.fxchess.engine.board_exception.IllegalPositionException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Game define a chess game.
 */
public class Game implements Serializable {
	public final ChessBoard board = new ChessBoard();
	private final ArrayList<String> moves;
	private final NotationParser parser;
	private int round;
	private Color player;

	public Game(NotationParser parser) {
		moves = new ArrayList<String>();
		this.parser = parser;
		player = Color.WHITE;
		round = 1;

		board.init();
	}

	public Game() {
		this(new ICCFNotationParser());
	}

	public int getRound() {
		return round;
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
	public void play(String rawMove) throws IllegalPositionException, IllegalMoveException, InvalidNotationException, OtherPlayerPieceException, SameColorException {
		Move move = parser.parseMove(rawMove);
		Move adaptedMove = adaptMove(move);
		playMove(adaptedMove);

		round++;
		player = round % 2 == 0 ? Color.BLACK : Color.WHITE;

		moves.add(rawMove);
	}

	private void playMove(Move m) throws IllegalPositionException, IllegalMoveException, OtherPlayerPieceException, SameColorException {
		Piece piece = (Piece) board.getMovable(m.getFrom());
		if (piece == null) {
			throw new IllegalMoveException(null, m.getFrom());
		}

		if (!piece.color.equals(player)) {
			throw new OtherPlayerPieceException(player, piece);
		}

		Piece destination = (Piece) board.getMovable(m.getTo());
		if (destination != null && destination.color.equals(player)) {
			throw new SameColorException(piece, m.getTo());
		}

		piece.move(m.getTo());
	}

	public ArrayList<String> getMoves() {
		return moves;
	}

	public void smartPrint() {
		this.board.smartPrint();
	}

	//	public boolean isCheck() {
	//		ArrayList<Piece> playerPieces = new ArrayList<>();
	//		King otherPlayerKing = null;
	//
	//		for (Case[] row : board.getCases()) {
	//			for (Case c : row) {
	//				if (!c.isOccupied())
	//					continue;
	//
	//				Piece piece = (Piece) c.getMovable();
	//				if (piece.color.equals(player))
	//					playerPieces.add(piece);
	//				else if (piece instanceof King)
	//					otherPlayerKing = (King) piece;
	//			}
	//		}
	//		assert otherPlayerKing != null;
	//
	//		for (Piece piece : playerPieces) {
	//			if (piece.canMove(otherPlayerKing.getCoord()))
	//				return true;
	//		}
	//
	//		return false;
	//	}
	//
	//	public boolean isCheckMate() {
	//		ArrayList<Piece> playerPieces = new ArrayList<>();
	//		King otherPlayerKing = null;
	//
	//		for (Case[] row : board.getCases()) {
	//			for (Case c : row) {
	//				if (!c.isOccupied())
	//					continue;
	//
	//				Piece piece = (Piece) c.getMovable();
	//				if (piece.color.equals(player))
	//					playerPieces.add(piece);
	//				else if (piece instanceof King)
	//					otherPlayerKing = (King) piece;
	//			}
	//		}
	//
	//		assert otherPlayerKing != null;
	//		ArrayList<Coord> otherPlayerKingPossibleMove = new ArrayList<>();
	//		for (int i = -1; i < 2; i++) {
	//			int x = otherPlayerKing.getCoord().getX();
	//			for (int j = -1; j < 2; j++) {
	//				int y = otherPlayerKing.getCoord().getY();
	//				Coord c = new Coord(x, y);
	//
	//				if (c.equals(otherPlayerKing.getCoord()))
	//					continue;
	//
	//				otherPlayerKingPossibleMove.add(c);
	//			}
	//		}
	//
	//		for (Coord possibleMove : otherPlayerKingPossibleMove) {
	//			for (Piece piece : playerPieces) {
	//				if (piece.canMove(possibleMove))
	//					return true;
	//			}
	//		}
	//
	//		return false;
	//	}
}
