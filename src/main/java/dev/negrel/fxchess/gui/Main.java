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
	Coord pressedCoord;
	private State state = State.PICK;

	public static void main(String[] args) {
		launch(args);
	}

	private void initialize(Stage stage) {
		canvas = new ChessBoard(game.board, loadAssets());
		stage.setWidth(720);
		stage.setMinWidth(480);

		stage.minHeightProperty().bind(stage.widthProperty());
		stage.maxHeightProperty().bind(stage.widthProperty());
	}

	@Override
	public void start(Stage stage) {
		initialize(stage);

		HBox hbox = new HBox(canvas);
		canvas.widthProperty().bind(hbox.widthProperty());
		canvas.heightProperty().bind(hbox.heightProperty());
		canvas.heightProperty().addListener((observable, oldValue, newValue) -> {
			drawChessBoard();
		});
		canvas.setOnMouseClicked(this::handleClick);

		stage.setScene(new Scene(hbox));
		stage.show();

		drawChessBoard();
	}

	private void handleClick(MouseEvent event) {
		switch (state) {
			case PICK -> {
				pressedCoord = canvas.adaptCoord(event.getX(), event.getY());
				Piece p = null;
				try {
					p = (Piece) game.board.getMovable(pressedCoord);
				} catch (IllegalPositionException ignored) {
				}

				if (p != null && p.color.equals(game.getPlayer()))
					state = State.MOVE;
				else
					pressedCoord = null;
			}
			case MOVE -> {
				Coord releasedCoord = canvas.adaptCoord(event.getX(), event.getY());
				try {
					game.playMove(new Move(pressedCoord, releasedCoord));
				} catch (Exception ignored) {
				}

				pressedCoord = null;
				state = State.PICK;
			}
		}

		drawChessBoard();
	}

	private void drawChessBoard() {
		canvas.drawChessBoard();

		switch (state) {
			case PICK:
				break;
			case MOVE:
				Piece p = null;
				try {
					p = (Piece) game.board.getMovable(pressedCoord);
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
					canvas.drawCaseAt(move, Color.GREEN);
				}
				break;

			case CHECK:
				break;
			case CHECK_MATE:
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
		CHECK,
		CHECK_MATE,
	}
}
