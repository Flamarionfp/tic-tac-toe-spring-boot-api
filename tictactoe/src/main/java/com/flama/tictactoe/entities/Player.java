package com.flama.tictactoe.entities;
public class Player {
    private final Character symbol;

    public Player(Character symbol) {
        this.symbol = symbol;
    }

    public Character getSymbol() {
        return symbol;
    }
}