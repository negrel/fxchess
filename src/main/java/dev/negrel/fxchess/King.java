package dev.negrel.fxchess;

import org.jetbrains.annotations.NotNull;

public class King {
	Coord position;
	Color color;
	ChessBoard board;

	public King(@NotNull ChessBoard board, @NotNull Coord position, @NotNull Color color) {
		this.board = board;
		this.color = color;
		this.position = position;
	}

	public void move(Coord c) {
		this.position = c;
	}

	public String toString() {
		return "\uD83E\uDD34";
	}
}
