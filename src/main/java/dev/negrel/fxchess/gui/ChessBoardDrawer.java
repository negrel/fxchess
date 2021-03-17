package dev.negrel.fxchess.gui;

import dev.negrel.fxchess.engine.ChessBoard;
import dev.negrel.fxchess.engine.Coord;
import dev.negrel.fxchess.engine.Piece;
import dev.negrel.fxchess.engine.board_exception.IllegalPositionException;
import dev.negrel.fxchess.engine.piece.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public class ChessBoardDrawer {
	private static double getCaseWidth(@NotNull GraphicsContext ctx) {
		return ctx.getCanvas().getWidth() / 8.0;
	}

	private static double getCaseHeight(@NotNull GraphicsContext ctx) {
		return ctx.getCanvas().getHeight() / 8.0;
	}

	private static Image getImageOf(@NotNull Piece p, Image[][] images) {
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

	public static void drawChessBoard(@NotNull GraphicsContext ctx, @NotNull ChessBoard board, Image[][] images) {
		double caseWidth = ChessBoardDrawer.getCaseWidth(ctx);
		double caseHeight = ChessBoardDrawer.getCaseHeight(ctx);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Color color = i % 2 != j % 2 ? Color.WHITE : Color.GREY;
				ctx.setFill(color);
				ctx.fillRect(j * caseWidth, i * caseHeight, caseWidth, caseHeight);

				Coord coord = new Coord(j, i);
				try {
					Piece p = (Piece) board.getMovable(coord);
					if (p == null)
						continue;

					Image img = ChessBoardDrawer.getImageOf(p, images);
					double ratio = img.getWidth() / img.getHeight();
					double width = caseWidth * ratio;
					double x = coord.getX() * caseWidth + caseWidth / 2 - width / 2;
					ctx.drawImage(img, x, coord.getY() * caseHeight, width, caseHeight);
				} catch (IllegalPositionException ignored) {
				}
			}
		}
	}
}
