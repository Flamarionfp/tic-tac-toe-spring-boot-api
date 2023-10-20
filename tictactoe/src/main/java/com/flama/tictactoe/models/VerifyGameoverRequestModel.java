package com.flama.tictactoe.models;

public class VerifyGameoverRequestModel extends BoardModel {
    private Character currentPlayerSymbol;

    public Character getCurrentPlayerSymbol() {
        return currentPlayerSymbol;
    }

    public void setCurrentPlayerSymbol(Character currentPlayerSymbol) {
        this.currentPlayerSymbol = currentPlayerSymbol;
    }
}
