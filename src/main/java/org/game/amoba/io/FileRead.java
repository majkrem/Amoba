package org.game.amoba.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.game.amoba.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileRead {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(FileRead.class);

    public FileRead() {
        LOGGER.debug("FileWrite inicializálva");
    }

    public Cell[][] readBoardAsCells(final String filename) throws IOException {
        final Cell[][] board = new Cell[10][10];

        try (BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
            for (int i = 0; i < 10; i++) {
                final String line = buffer.readLine();
                if (line == null) {
                    throw new IOException("A fájl túl rövid, nem tartalmaz " + 10 + " sort!");
                }
                for (int j = 0; j < 10; j++) {
                    final char character = line.charAt(j);
                    switch (character) {
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
            }
        }
        return board;
    }

}
