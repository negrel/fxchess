package dev.negrel.fxchess.engine;

import dev.negrel.fxchess.engine.board_exception.IllegalMoveException;
import dev.negrel.fxchess.engine.board_exception.IllegalPositionException;
import dev.negrel.fxchess.engine.piece.King;

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
		this.init();
	}

	public Game() {
		this(new ICCFNotationParser());
	}

	public int getRound() {
		return round;
	}

	public Color getPlayer() {
		return player;
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

		moves.add(rawMove);
	}

	public void playMove(Move m) throws IllegalPositionException, IllegalMoveException, OtherPlayerPieceException, SameColorException {
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

		round++;
		player = round % 2 == 0 ? Color.BLACK : Color.WHITE;
	}

	public ArrayList<String> getMoves() {
		return moves;
	}

	public void smartPrint() {
		this.board.smartPrint();
	}

	public boolean isCheck() {
		ArrayList<Piece> playerPieces = getPieces();
		King otherPlayerKing = (King) playerPieces.stream()
			.filter(p -> p.color.equals(player) && p instanceof King).findFirst().get();

		for (Piece piece : playerPieces) {
			if (piece.isLegalMove(otherPlayerKing.getCoord()))
				return true;
		}

		return false;
	}

	public King getPlayerKing() {
		ArrayList<Piece> playerPieces = getPieces();
		return (King) playerPieces.stream()
			.filter(p -> p.color.equals(player) && p instanceof King).findFirst().get();
	}


	private ArrayList<Piece> getPieces() {
		ArrayList<Piece> playersPieces = new ArrayList<>();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece piece = null;
				try {
					piece = (Piece) board.getMovable(new Coord(j, i));
				} catch (IllegalPositionException ignored) {
				}
				if (piece == null)
					continue;

				playersPieces.add(piece);
			}
		}

		return playersPieces;
	}

	public boolean isCheckMate() {
		ArrayList<Piece> playerPieces = getPieces();
		King otherPlayerKing = (King) playerPieces.stream()
			.filter(p -> !p.color.equals(player) && p instanceof King).findFirst().get();

		for (Coord move : otherPlayerKing.legalMove()) {
			boolean canMove = true;

			for (Piece piece : playerPieces) {
				if (piece.isLegalMove(move)) {
					canMove = false;
					break;
				}
			}

			if (canMove)
				return false;
		}

		return false;
	}

	public void init() {
		player = Color.WHITE;
		round = 1;

		board.init();
	}
}
