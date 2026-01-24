package org.game.amoba.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.game.amoba.Cell;

public class FileRead {

    private static final int SIZE = 10;

    public Cell[][] readBoardAsCells(String filename) throws IOException {
        Cell[][] board = new Cell[SIZE][SIZE];

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            for (int i = 0; i < SIZE; i++) {
                String line = br.readLine();
                if (line == null) {
                    throw new IOException("A fájl túl rövid, nem tartalmaz " + SIZE + " sort!");
                }
                for (int j = 0; j < SIZE; j++) {
                    char c = line.charAt(j);
                    System.out.print(c + " ");
                    switch (c) {
                        case 'X':
                            board[i][j] = Cell.PLAYERX;
                            break;
                        case 'O':
                            board[i][j] = Cell.PLAYERO;
                            break;
                        default:
                            board[i][j] = Cell.EMPTY;
                            break;
                    }

                }
                System.out.println();
            }
        }
        return board;
    }

}
