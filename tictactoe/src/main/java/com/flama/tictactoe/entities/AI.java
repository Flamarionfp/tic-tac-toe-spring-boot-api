package com.flama.tictactoe.entities;

import utils.Array;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

public class AI extends Player {
    Random random = new Random();

    public AI(char symbol) {
        super(symbol);
    }

    private Character getOponentSymbol() {
        return getSymbol() == 'x' ? 'o' : 'x';
    }
    private boolean isCritical(Character[] matchedSymbols) {
        return matchedSymbols.length == Board.DIMENSIONS - 1;
    }

    private int getCriticalPosition(Object[] currentPositions) {
        return Array.findIndex(currentPositions, Objects::isNull);
    }

    private static List<Character> getDiagonalCharacters(Character[][] positions, boolean verifyMainDiagonal) {
        List<Character> diagonalPos = new ArrayList<>();

        if (verifyMainDiagonal) {
            for (int i = 0; i < positions.length; i++) {
                diagonalPos.add(positions[i][i]);
            }
        } else {
            for (int i = 0; i < positions.length; i++) {
                for (int j = 0; j < positions[i].length; j++) {
                    if (i + j == positions.length - 1) {
                        diagonalPos.add(positions[i][j]);
                    }
                }
            }
        }
        return diagonalPos;
    }

    private int[] getCriticalDiagonal(Character[][] positions, Character symbolToCompare, boolean verifyMainDiagonal) {
        List<Character> diagonalPos = getDiagonalCharacters(positions, verifyMainDiagonal);

        int count = 0;
        int emptyPosition = -1;

        for (int i = 0; i < diagonalPos.size(); i++) {
            if (diagonalPos.get(i) == symbolToCompare) {
                count++;
            } else if (diagonalPos.get(i) == null) {
                emptyPosition = i;
            }
        }

        if (count == Board.DIMENSIONS - 1 && emptyPosition != -1) {
            if (verifyMainDiagonal || emptyPosition == 1) {
                return new int[]{emptyPosition, emptyPosition};
            } else if (emptyPosition == 2) {
                return new int[]{emptyPosition, 0};
            } else {
                return new int[]{emptyPosition, 2};
            }
        }

        return new int[]{};
    }


    private int[] handleGetCriticalDiagonal(Character[][] positions, Character symbolToCompare) {
        int[] criticalDiagonal = new int[]{};

        for (int i = 0; i < Board.DIMENSIONS - 1; i++) {
            criticalDiagonal =  getCriticalDiagonal(positions, symbolToCompare, i == 0);

            if (criticalDiagonal.length > 0) {
                return criticalDiagonal;
            }
        }

        return criticalDiagonal;
    }

    private int[] handleGetCriticalHorizontal(Character[][] positions, Character symbolToCompare) {
        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < positions[i].length; j++) {
                Stream<Character> matchedSymbolsStream = Arrays.stream(positions[i]).filter(symbol -> symbol == symbolToCompare);
                Character[] matchedSymbols = matchedSymbolsStream.toArray(Character[]::new);

                if (isCritical(matchedSymbols)) {
                    int columnToPlay = getCriticalPosition(positions[i]);

                    return columnToPlay == -1 ? new int[]{} : new int[]{i, columnToPlay};
                }
            }
        }

        return new int[]{};
    }

    private int[] handleGetCriticalVertical(Character[][] positions, Character symbolToCompare) {
        List<Character> verticalPos = new ArrayList<>();

        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < positions[i].length; j++) {
                verticalPos.add(positions[j][i]);
            }

            Stream<Character> matchedSymbolsStream = verticalPos.stream().filter(symbol -> symbol == symbolToCompare);
            Character[] matchedSymbols = matchedSymbolsStream.toArray(Character[]::new);

            if (isCritical(matchedSymbols)) {
                int rowToPlay = getCriticalPosition(verticalPos.toArray());

                return rowToPlay == -1 ? new int[]{} : new int[]{rowToPlay, i};
            } else {
                verticalPos.clear();
            }
        }

        return new int[]{};
    }

    private Callable<int[]>[] getVerifyFunctions(Character[][] positions) {
        return (Callable<int[]>[]) new Callable[]{
                () -> handleGetCriticalHorizontal(positions, getSymbol()),
                () -> handleGetCriticalVertical(positions, getSymbol()),
                () -> handleGetCriticalDiagonal(positions, getSymbol()),
                () -> handleGetCriticalHorizontal(positions, getOponentSymbol()),
                () -> handleGetCriticalVertical(positions, getOponentSymbol()),
                () -> handleGetCriticalDiagonal(positions, getOponentSymbol()),
        };
    }

    public int[] getBetterPositionToPlay(Board board) throws Exception {
        int i = 0;
        int[] positionToPlay = new int[]{};

        while (positionToPlay.length == 0) {
            Callable<int[]>[] verifyFunctions = getVerifyFunctions(board.positions);

            positionToPlay = verifyFunctions[i].call();
            i++;

            if (positionToPlay.length == 0 && i == verifyFunctions.length) {
                do {
                    int randomRow = random.nextInt(Board.DIMENSIONS);
                    int randomColumn = random.nextInt(Board.DIMENSIONS);

                    positionToPlay = new int[]{randomRow, randomColumn};
                } while (board.isFilledPosition(positionToPlay[0], positionToPlay[1]));
            }
        }

        return positionToPlay;
    }
}