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

    public void setCell(final int row, final int column, final Cell cell) {
        grid[row][column] = cell;
    }

    public Cell getCell(final int row, final int column) {
        return grid[row][column];
    }

    public boolean isIn(final int row, final int column) {
        return row >= 0 && row < this.row && column >= 0 && column < this.column;
    }

    public boolean isEmpty(final int row, final int column) {
        return grid[row][column] == Cell.EMPTY;
    }

    public void print() {
        System.out.print("--");
        for (int i = 0; i < column; i++) {
            System.out.print((char) ('a' + i) + " ");
            final char output = (char) ('a' + i);
        }
        System.out.println();

        for (int j = 0; j < row; j++) {
            System.out.print(j + " ");
            for (int i = 0; i < column; i++) {
                System.out.print(grid[j][i].getSymbol() + " ");
                final char output =  grid[j][i].getSymbol();
            }
            System.out.println();
        }
        System.out.println();

    }

}
