package org.game.amoba.io;

import org.game.amoba.Board;
import org.game.amoba.Cell;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileWriter {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(FileWriter.class);

    public void write(Board board, Path path, final char turn) throws IOException {
        LOGGER.info("Pálya mentése ide: {}\n", path);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (int row = 0; row < board.getRow(); row++) {
                writeRow(board, writer, row);
            }
            writeTurn(writer, turn);
        }
    }

    private void writeRow(Board board, BufferedWriter writer, int row)
            throws IOException {

        for (int col = 0; col < board.getColumn(); col++) {
            Cell cell = board.getCell(row, col);
            writer.write(cellToChar(cell));
            writer.write(' ');
        }
        writer.newLine();
    }

    private void writeTurn(BufferedWriter writer,final char turn) throws IOException {
        writer.write(turn);
        writer.newLine();
    }

    private char cellToChar(Cell cell) {
        return switch (cell) {
            case PLAYERX -> 'X';
            case PLAYERO -> 'O';
            case EMPTY -> '.';
        };
    }
}
