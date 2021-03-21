package dev.negrel.fxchess.gui;

import dev.negrel.fxchess.engine.Coord;
import dev.negrel.fxchess.engine.Game;
import dev.negrel.fxchess.engine.Move;
import dev.negrel.fxchess.engine.Piece;
import dev.negrel.fxchess.engine.board_exception.IllegalPositionException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
	ChessBoard canvas;
	Game game = new Game();
	Coord pickedCoord;
	private State state = State.PICK;
	private Coord checkCoord = null;

	public static void main(String[] args) {
		launch(args);
	}

	private void initialize(Stage stage) {
		// Create the canvas
		canvas = new ChessBoard(game.board, loadAssets());
		// Set the window size and min width
		stage.setWidth(720);
		stage.setMinWidth(480);

		// Fix the window aspect ratio
		stage.minHeightProperty().bind(stage.widthProperty());
		stage.maxHeightProperty().bind(stage.widthProperty());
	}

	@Override
	public void start(Stage stage) {
		initialize(stage);

		HBox hbox = new HBox(canvas);
		// Bind the canvas size to the window size.
		canvas.widthProperty().bind(hbox.widthProperty());
		canvas.heightProperty().bind(hbox.heightProperty());

		// Redraw on resize.
		canvas.heightProperty().addListener((observable, oldValue, newValue) -> {
			drawChessBoard();
		});
		// Handle players click.
		canvas.setOnMouseClicked(this::handleClick);

		stage.setScene(new Scene(hbox));
		stage.show();

		drawChessBoard();
	}

	private void handleClick(MouseEvent event) {
		// Depending on the state of the game
		// the click will be handled differently.
		switch (state) {
			// A player pick a chess piece.
			case PICK -> {
				// Register the coordinate of the selected piece.
				pickedCoord = canvas.adaptCoord(event.getX(), event.getY());
				// Try to get it.
				Piece p = null;
				try {
					p = (Piece) game.board.getMovable(pickedCoord);
				} catch (IllegalPositionException ignored) {
				}
				// If success, change state to move.
				if (p != null && p.color.equals(game.getPlayer()))
					state = State.MOVE;
				else // Otherwise, nothing happen.
					pickedCoord = null;
			}

			// A player tried to move the selected piece.
			case MOVE -> {
				Coord moveCoord = canvas.adaptCoord(event.getX(), event.getY());
				// Try to play the move.
				try {
					game.playMove(new Move(pickedCoord, moveCoord));
					// The player try to avoid a check mate.
					if (pickedCoord.equals(checkCoord)) {
						checkCoord = null;
					}
					// Player killed the opponent king.
					else if (moveCoord.equals(checkCoord)) {
						state = State.END_GAME;
						drawChessBoard();
						return;
					}
				}
				// Catch errors on move.
				catch (Exception ignored) {
				}

				// Check if this move put the
				// opponent in check.
				if (game.isCheck()) {
					checkCoord = game.getPlayerKing().getCoord();
					// If check and mate, end of game.
					if (game.isCheckMate())
						state = State.END_GAME;
				}
				state = State.PICK;
			}

			// Start another game.
			case END_GAME -> {
				game.init();
				this.checkCoord = null;
				state = State.PICK;
			}
		}

		// Update the canvas.
		drawChessBoard();
	}

	private void drawChessBoard() {
		canvas.drawChessBoard();

		// If check, draw the king case in orange.
		if (checkCoord != null) {
			canvas.drawCase(checkCoord, Color.ORANGE);
		}

		switch (state) {
			case PICK:
				break;

			// Draw possibles moves.
			case MOVE:
				Piece p = null;
				try {
					p = (Piece) game.board.getMovable(pickedCoord);
				} catch (IllegalPositionException ignored) {
				}

				if (p == null)
					return;

				List<Coord> moves = p.legalMove();
				if (moves.size() == 0) {
					state = State.PICK;
					break;
				}

				for (Coord move : moves) {
					canvas.drawCase(move, Color.GREEN);
				}
				break;

			// Draw opponent dead king case in red.
			case END_GAME:
				assert checkCoord != null;
				canvas.drawCase(checkCoord, Color.RED);
				break;

			default:
				throw new IllegalStateException("Unexpected value: " + state);
		}
	}

	private Image[][] loadAssets() {
		Image[][] pieces = new Image[2][6];
		try {
			Image imagePieces = new Image("file:src/main/resources/img/ChessPieces.png", false);
			int pieceHeight = ((int) imagePieces.getHeight()) / 2;
			int pieceWidth = ((int) imagePieces.getWidth()) / 6;

			PixelReader pixReader = imagePieces.getPixelReader();
			for (int i = 0; i < 6; i++) {
				pieces[0][i] = new WritableImage(pixReader, i * pieceWidth, 0, pieceWidth, pieceHeight);
				pieces[1][i] = new WritableImage(pixReader, i * pieceWidth, pieceHeight, pieceWidth, pieceHeight);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pieces;
	}


	private enum State {
		PICK,
		MOVE,
		END_GAME,
	}
}
