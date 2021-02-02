package dev.negrel.fxchess;

import org.jetbrains.annotations.NotNull;

public class King {
	public final Color color;
	private final ChessBoard board;
	private Coord coord;

	public King(@NotNull ChessBoard board, @NotNull Coord coord, @NotNull Color color) {
		this.board = board;
		this.color = color;
		this.coord = coord;
	}

	public Coord getCoord() {
		return coord;
	}

	public void move(Coord c) {
		this.coord = c;
	}

	public String toString() {
		return "\uD83E\uDD34";
	}
}
