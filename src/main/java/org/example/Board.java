package org.example;

public class Board {

    private final int row;
    private final int column;
    private final Cell[][] grid;

    public Board(final int row, final int column) {
        this.row = row;
        this.column = column;
        grid = new Cell[row][column];
        init();
    }

    private void init() {
        System.out.println("Init\n");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                grid[i][j] = Cell.EMPTY;
            }
        }
        System.out.println("Init vege\n");
    }

}
