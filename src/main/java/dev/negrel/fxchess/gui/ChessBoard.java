package dev.negrel.fxchess.gui;

import dev.negrel.fxchess.engine.Coord;
import dev.negrel.fxchess.engine.Piece;
import dev.negrel.fxchess.engine.board_exception.IllegalPositionException;
import dev.negrel.fxchess.engine.piece.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public class ChessBoard extends Canvas {
	private final GraphicsContext ctx = this.getGraphicsContext2D();
	private final dev.negrel.fxchess.engine.ChessBoard board;
	private final Image[][] images;
	private Coord selectedCoord;

	ChessBoard(@NotNull dev.negrel.fxchess.engine.ChessBoard board, @NotNull Image[][] images) {
		this.board = board;
		this.images = images;
	}

	private Image getImageOf(@NotNull Piece p) {
		int color = p.color.equals(dev.negrel.fxchess.engine.Color.BLACK) ? 1 : 0;

		int index = 0;

		if (p instanceof Pawn) {
			index = 0;
		} else if (p instanceof Rook) {
			index = 1;
		} else if (p instanceof Knight) {
			index = 2;
		} else if (p instanceof Bishop) {
			index = 3;
		} else if (p instanceof Queen) {
			index = 4;
		} else if (p instanceof King) {
			index = 5;
		} else {
			System.out.print("ERROR on piece");
			System.out.println(p);
			System.exit(1);
		}

		if (color == 1) {
			index = 5 - index;
		}

		return images[color][index];
	}

	public void drawChessBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Coord coord = new Coord(i, j);
				Color color = coord.getX() % 2 != coord.getY() % 2 ? Color.WHITE : Color.GREY;
				drawCaseAt(coord, color);
			}
		}
	}

	public Coord adaptCoord(double x, double y) {
		x = x / getCaseWidth();
		y = y / getCaseHeight();

		return new Coord(x, y);
	}

	public void drawCase(double x, double y, @NotNull Color color) {
		this.drawCaseAt(adaptCoord(x, y), color);
	}

	public void drawCaseAt(@NotNull Coord coord, @NotNull Color color) {
		GraphicsContext ctx = this.getGraphicsContext2D();

		double caseWidth = getCaseWidth();
		double caseHeight = getCaseHeight();

		ctx.setFill(color);
		ctx.fillRect(coord.getX() * caseWidth, coord.getY() * caseHeight, caseWidth, caseHeight);

		Piece p = null;
		try {
			p = (Piece) board.getMovable(coord);
		} catch (IllegalPositionException ignored) {
		}

		if (p == null)
			return;

		Image img = getImageOf(p);
		double ratio = img.getWidth() / img.getHeight();
		double width = caseWidth * ratio;
		double x = coord.getX() * caseWidth + caseWidth / 2 - width / 2;
		ctx.drawImage(img, x, coord.getY() * caseHeight, width, caseHeight);
	}

	private double getCaseHeight() {
		return this.getHeight() / 8.0;
	}

	private double getCaseWidth() {
		return this.getWidth() / 8.0;
	}


}
