package org.game.amoba;

public enum Cell {

    EMPTY('.'),
    PLAYERX('X'),
    PLAYERO('O');

    private final char symbol;

    Cell(final char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return  symbol;
    }

}
