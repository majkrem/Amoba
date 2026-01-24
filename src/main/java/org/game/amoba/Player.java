package org.game.amoba;

public class Player {

    private final String name;
    private final Cell symbol;

    public Player(final String name, final Cell symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public Cell getSymbol() {
        return symbol;
    }

}
