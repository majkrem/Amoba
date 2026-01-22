package org.example;

public enum Cell {

    EMPTY('.'),
    X('X'),
    O('O');

    private final char symbol;

    Cell(final char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return  symbol;
    }
}
