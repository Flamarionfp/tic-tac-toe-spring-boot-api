package com.flama.tictactoe.models;

public class NextPlayModel extends ErrorModel {
    private int[] aiNextPlay = new int[]{};

    public int[] getAiNextPlay() {
        return aiNextPlay;
    }

    public void setAiNextPlay(int[] aiNextPlay) {
        this.aiNextPlay = aiNextPlay;
    }

}
