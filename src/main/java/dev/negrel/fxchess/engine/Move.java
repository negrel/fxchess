package dev.negrel.fxchess.engine;

import org.jetbrains.annotations.NotNull;

/*
 * Move defines a chess move.
 */
public class Move {
	private final Coord from;
	private final Coord to;

	public Move(@NotNull Coord from, @NotNull Coord to) {
		this.from = from;
		this.to = to;
	}

	public Coord getFrom() {
		return from;
	}

	public Coord getTo() {
		return to;
	}

	public boolean equals(@NotNull Move other) {
		return this.from.equals(other.from) && this.to.equals(other.to);
	}
}
