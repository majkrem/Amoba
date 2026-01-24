package org.game.amoba;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Board {

    private final int row;
    private final int column;
    private final Cell[][] grid;
    private static final Logger LOGGER = LoggerFactory.getLogger(Board.class);


    public Board(final int row, final int column) {
        this.row = row;
        this.column = column;
        grid = new Cell[row][column];
        init();
    }

    private void init() {
        // System.out.println("Init\n");
        LOGGER.info("Init\n");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                grid[i][j] = Cell.EMPTY;
            }
        }
        // System.out.println("Init vege\n");
        LOGGER.info("Init vege\n");
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
        // System.out.print("--");
        LOGGER.info("--");
        for (int i = 0; i < column; i++) {
            // System.out.print((char) ('a' + i) + " ");
            final char output = (char) ('a' + i);
            LOGGER.info("{} ", output);
        }
        // System.out.println();
        LOGGER.info("\n");

        for (int j = 0; j < row; j++) {
            // System.out.print((j + 1) + " ");
            final int rowNumber = j + 1;
            LOGGER.info("{} ", rowNumber);
            for (int i = 0; i < column; i++) {
                // System.out.print(grid[j][i].getSymbol() + " ");
                final char output =  grid[j][i].getSymbol();
                LOGGER.info("{} ", output);
            }
            // System.out.println();
            LOGGER.info("\n");
        }
        // System.out.println();
        LOGGER.info("\n");
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

}
