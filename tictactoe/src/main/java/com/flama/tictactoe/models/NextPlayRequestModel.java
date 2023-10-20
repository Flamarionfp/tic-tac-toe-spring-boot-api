package com.flama.tictactoe.models;

public class NextPlayRequestModel extends BoardModel {
    private Character iaSymbol;

    public Character getIaSymbol() {
        return iaSymbol;
    }

    public void setIaSymbol(Character iaSymbol) {
        this.iaSymbol = iaSymbol;
    }
}
