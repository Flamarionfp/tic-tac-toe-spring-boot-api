package com.flama.tictactoe.models;

public class VerifyGameoverResponseModel extends ErrorModel {
    private Character winner;
    private boolean gameover = false;

    public Character getWinner() {
        return winner;
    }

    public void setWinner(Character winner) {
        this.winner = winner;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }
}
