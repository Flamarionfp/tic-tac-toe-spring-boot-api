package com.flama.tictactoe.entities;

public class Game {
    private final Board board;
    private Player winner;

    private boolean gameover;

    public Game (Board board) {
        this.board = board;
    }

    private boolean hasWinner() {
        return board.mainDiagonalFilled() || board.secondaryDiagonalFilled() || board.horizontalFilled() || board.verticalFilled();
    }

    public void verifyGameOver(Player currentPlayer) {
        if (hasWinner()) {
            setWinner(currentPlayer);
            setGameover();
        }

        if (board.isAllFilled()) {
            setGameover();
        }
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover() {
        this.gameover = true;
    }
}
