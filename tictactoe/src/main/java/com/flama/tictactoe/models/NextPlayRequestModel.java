package com.flama.tictactoe.models;

public class NextPlayRequestModel {
    private Character iaSymbol;
    private Character[][] board;

    public Character getIaSymbol() {
        return iaSymbol;
    }

    public void setIaSymbol(Character iaSymbol) {
        this.iaSymbol = iaSymbol;
    }

    public Character[][] getBoard() {
        return board;
    }

    public void setBoard(Character[][] board) {
        this.board = board;
    }
}
