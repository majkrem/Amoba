package org.game.amoba;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;

import org.game.amoba.io.FileRead;
import org.game.amoba.io.FileWrite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class GameRules {

    private final Board board;
    private final Player human;
    private final Player computer;
    private Player currentPlayer;
    private static final Logger LOGGER = LoggerFactory.getLogger(GameRules.class);

    public GameRules(final int row, final int column) {
        board = new Board(row, column);
        human = new Player("Human", Cell.PLAYERX);
        computer = new Player("Computer", Cell.PLAYERO);
        currentPlayer = human;
    }

    public void start() {
        final int midRow = boardRow() / 2;
        final int midColumn = boardColumn() / 2;
        board.setCell(midRow, midColumn, Cell.PLAYERO);

        final Scanner scanner = new Scanner(System.in);

        board.print();
        while (true) {

            if (currentPlayer == human) {
                humanMove(scanner);
            } else {
                computerMove();
            }
            board.print();

            // System.out.println("check win\n");
            if (checkWin(currentPlayer.getSymbol())) {
                board.print();
                // System.out.println(currentPlayer.getName() + " nyert!");
                final String output = currentPlayer.getName();
                LOGGER.info("{} nyert!\n", output);
                break;
            }
            // System.out.println("after check win\n");

            switchPlayer();
        }
    }

    private void humanMove(final Scanner scanner) {
        boolean moveMade = false;
        while (!moveMade) {
            // System.out.println("Lepes (p1. b3): ");
            LOGGER.info("Lepes (p1, b3 | save | load): ");
            String input = scanner.nextLine();
            LOGGER.info("{}\n", input);

            if ("save".equals(input)) {
                saveGame();
                moveMade = true;
            }
            if ("load".equals(input)) {
                loadGame();
                board.print();
                LOGGER.info("Lepes (p3, b1 | save | load): ");
                input = scanner.nextLine();
                LOGGER.info("{}\n", input);
            }

            // System.out.println("Human move\n");

            if (!moveMade) {
                final int humanColumn = input.charAt(0) - 'a';
                final int humanRow = Integer.parseInt(input.substring(1)) - 1;

                if (isValidMove(humanRow, humanColumn)) {
                    board.setCell(humanRow, humanColumn, Cell.PLAYERX);
                    // System.out.println("Human lepett\n");
                    final char humanRowOutput = (char) ('a' + humanRow);
                    final int humanColumnOutput = humanColumn + 1;
                    LOGGER.info("Ember lepett: {}{}\n", humanRowOutput, humanColumnOutput);
                    moveMade = true;
                }
                // System.out.println("Ervenytelen lepes!");
                LOGGER.info("Ervenytelen lepes!\n");
            }
        }
    }

    private void computerMove() {
        // System.out.println("Computer move\n");
        final Random random = new Random();
        int computerR;
        int computerC;
        do {
            computerR = random.nextInt(boardRow());
            computerC = random.nextInt(boardColumn());
            // System.out.println(computerR + " " + computerR + "\n");
        } while (!isValidMove(computerR, computerC));

        board.setCell(computerR, computerC, Cell.PLAYERO);
        // System.out.println("Gep letett: " + (char) ('a' + computerC) + (computerR + 1));
        final char outputC = (char) ('a' + computerC);
        final int outputR = computerR + 1;
        LOGGER.info("Gep letett: {}{}\n", outputC, outputR);
    }

    private boolean isValidMove(final int row, final int column) {
        boolean isValid = false;

        if (board.isIn(row, column) && board.isEmpty(row, column)) {
            for (int rr = -1; rr <= 1; rr++) {
                for (int cc = -1; cc <= 1; cc++) {
                    if (rr == 0 && cc == 0) {
                        continue;
                    }
                    final int newR = row + rr;
                    final int newC = column + cc;
                    // System.out.println(rr + "  - " + cc + "\n");
                    if (board.isIn(newR, newC) && !board.isEmpty(newR, newC)) {
                        // System.out.println("Yes\n");
                        isValid = true;
                        break;
                    }
                }
                if (isValid) {
                    break;
                }
            }
        }
        return isValid;
    }

    private boolean checkWin(final Cell symbol) {
        final int[][] dirs = {
                {1, 0}, {0, 1}, {1, 1}, {1, -1}
        };

        boolean won = false;
        for (int r = 0; r < boardRow(); r++) {
            for (int c = 0; c < boardColumn(); c++) {
                // System.out.println(r + " " + c + "\n");
                if (board.getCell(r, c) == symbol) {
                    for (final int[] d : dirs) {
                        if (count(symbol, r, c, d[0], d[1]) >= 5) {
                            won = true;
                        }
                    }
                }
            }
        }
        return won;
    }

    private int count(final Cell symbol, final int newRow, final int newColumn, final int dirRow, final int dirColumn) {
        int cnt = 0;
        int rowProcess = newRow;
        int columnProcess = newColumn;
        while (board.isIn(rowProcess, columnProcess) && board.getCell(rowProcess, columnProcess) == symbol) {
            cnt++;
            rowProcess = rowProcess + dirRow;
            columnProcess = columnProcess + dirColumn;
        }
        return cnt;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == human) ? computer : human;
    }

    private void saveGame() {
        try {
            final FileWrite writer = new FileWrite();
            writer.write(board, Path.of("board.txt"), currentPlayer.getSymbol().getSymbol());
            LOGGER.info("Jatek elmentve\n");
        } catch (IOException e) {
            LOGGER.error("Mentes hiba: ", e);
        }
    }

    private void loadGame() {
        try {
            // Board newBoard = new Board(boardRow(), boardColumn());
            final FileRead read = new FileRead();
            final Cell[][] loadedBoard = read.readBoardAsCells("board.txt");

            for (int i = 0; i < boardRow(); i++) {
                for (int j = 0; j < boardColumn(); j++) {
                    board.setCell(i, j, loadedBoard[i][j]);
                }
            }
            LOGGER.info("Jatek betoltve\n");
        } catch (IOException e) {
            LOGGER.error("Olvasasi hiba ", e);
        }
    }

    private int boardRow() {
        return board.getRow();
    }

    private int boardColumn() {
        return board.getColumn();
    }

}
