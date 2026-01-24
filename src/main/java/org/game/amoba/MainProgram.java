package org.game.amoba;

public class MainProgram {
    public static void main(String[] args) {
        final GameRules game = new GameRules(10, 10);
        game.start();
    }
}