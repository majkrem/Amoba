package org.example;

import java.util.Random;
import java.util.Scanner;

public class Game {

    private final Board board;
    private final Player human;
    private final Player computer;
    private Player currentPlayer;

    public Game(final int row, final int column) {
        board = new Board(row, column);
        human = new Player("Human", Cell.X);
        computer = new Player("Computer", Cell.O);
        currentPlayer = human;
    }

    public void start() {
        final int midRow = 10 / 2;
        final int midColumn = 10 / 2;
        board.setCell(midRow, midColumn, Cell.X);

        final Scanner scanner = new Scanner(System.in);

        while (true) {
            board.print();

            if (currentPlayer == human) {
                humanMove(scanner);
            } else {
                computerMove();
            }

            if (checkWin(currentPlayer.getSymbol())) {
                board.print();
                System.out.println(currentPlayer.getName() + " nyert!");
                break;
            }

            switchPlayer();
        }
    }

    private void humanMove(final Scanner scanner) {
        while (true) {
            System.out.println("Lepes (p1. b3): ");
            final String input = scanner.nextLine();

            final int humanColumn = input.charAt(0) - 'a';
            final int humanRow = Integer.parseInt(input.substring(1)) - 1;

            if (isValidMove(humanRow, humanColumn)) {
                board.setCell(humanRow, humanColumn, Cell.X);
                return;
            }
            System.out.println("Ervenytelen lepes!");
        }
    }

    private void computerMove() {
        final Random random = new Random();
        int computerR;
        int computerC;
        do {
            computerR = random.nextInt(10);
            computerC = random.nextInt(10 );
        } while (!isValidMove(computerR, computerC));

        board.setCell(computerR, computerC, Cell.O);
        System.out.println("Gep letett: " + (char) ('a' + c) + (r + 1));
        final char outputC = (char) ('a' + computerC);
        final int outputR = computerR + 1;
    }

    private boolean isValidMove(final int row, final int column) {
        if (!board.isIn(row, column) || !board.isEmpty(row, column)) {
            return false;
        }

        for (int rr = -1; rr <= 1; rr++) {
            for (int cc = -1; cc <= 1; cc++) {
                if (rr == 0 && cc == 0) {
                    continue;
                }
                final int newR = row + rr;
                final int newC = column + cc;
                if (board.isIn(newR, newC) && board.isEmpty(newR, newC)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkWin(final Cell symbol) {
        final int[][] dirs = {
                {1, 0}, {0, 1}, {1, 1}, {1, -1}
        };

        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (board.getCell(r, c) == symbol) {
                    for (final int[] d : dirs) {
                        if (count(symbol, r, c, d[0], d[1]) >= 5) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private int count(final Cell symbol, final int newRow, final int newColumn, final int dirRow, final int dirColumn) {
        int cnt = 0;
        int rowProcess = newRow;
        int columnProcess = newColumn;
        while (board.isIn(rowProcess, columnProcess) && board.getCell(rowProcess, columnProcess) == symbol) {
            cnt++;
            rowProcess = newRow + dirRow;
            columnProcess = newColumn + dirColumn;
        }
        return cnt;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == human) ? computer : human;
    }

}
