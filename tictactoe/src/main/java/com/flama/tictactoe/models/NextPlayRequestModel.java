package com.flama.tictactoe.models;

public class NextPlayRequestModel extends BoardRequestModel {
    private Character iaSymbol;

    public Character getIaSymbol() {
        return iaSymbol;
    }

    public void setIaSymbol(Character iaSymbol) {
        this.iaSymbol = iaSymbol;
    }
}
