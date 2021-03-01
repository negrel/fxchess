package dev.negrel.fxchess.game;

import java.io.Serializable;

/*
 * NotationParser is a generic interface for chess notation parser.
 */
public interface NotationParser extends Serializable {
	Move parseMove(String rawMove) throws InvalidNotationException;
}
