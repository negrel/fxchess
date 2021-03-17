package dev.negrel.fxchess.gui;

import dev.negrel.fxchess.engine.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
	Canvas canvas = new Canvas();
	Game game = new Game();
	Image pieces[][] = new Image[2][6];

	double oldx = 0, oldy = 0;

	public static void main(String[] args) {
		launch(args);
	}

	private void initialize(Stage stage) {
		stage.setWidth(720);
		stage.setMinWidth(480);

		stage.minHeightProperty().bind(stage.widthProperty());
		stage.maxHeightProperty().bind(stage.widthProperty());

		this.loadAssets();
	}

	@Override
	public void start(Stage stage) {
		initialize(stage);

		HBox hbox = new HBox(canvas);
		canvas.widthProperty().bind(hbox.widthProperty());
		canvas.heightProperty().bind(hbox.heightProperty());

		GraphicsContext ctx = canvas.getGraphicsContext2D();
		canvas.heightProperty().addListener((observable, oldValue, newValue) -> {
			drawBoard(ctx);
		});
		canvas.setOnMousePressed((MouseEvent event) -> {
			double x = event.getX();
			double y = event.getY();


		});

		canvas.setOnMouseClicked(e -> {
			drawBoard(ctx);
			ctx.strokeLine(oldx, oldy, e.getX(), e.getY());
			oldx = e.getX();
			oldy = e.getY();
		});

		stage.setScene(new Scene(hbox));
		stage.show();

		drawBoard(ctx);
	}

	private void drawBoard(GraphicsContext ctx) {
		ChessBoardDrawer.drawChessBoard(ctx, game.board, pieces);
	}

	private void loadAssets() {
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
	}
}
