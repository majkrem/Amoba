package org.game.amoba.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.game.amoba.Board;
import org.game.amoba.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileWrite {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(FileWrite.class);

    public FileWrite() {
        LOGGER.debug("FileWrite inicializálva");
    }

    public void write(final Board board, final Path path, final char turn) throws IOException {
        LOGGER.info("Pálya mentése ide: {}\n", path);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (int row = 0; row < board.getRow(); row++) {
                writeRow(board, writer, row);
            }
            writeTurn(writer, turn);
        }
    }

    private void writeRow(final Board board, final BufferedWriter writer, final int row)
            throws IOException {

        for (int col = 0; col < board.getColumn(); col++) {
            final Cell cell = board.getCell(row, col);
            writer.write(cellToChar(cell));
            // writer.write(' ');
        }
        writer.newLine();
    }

    private void writeTurn(final BufferedWriter writer, final char turn) throws IOException {
        writer.write(turn);
        writer.newLine();
    }

    private char cellToChar(final Cell cell) {
        return switch (cell) {
            case PLAYERX -> 'X';
            case PLAYERO -> 'O';
            case EMPTY -> '.';
        };
    }
}
