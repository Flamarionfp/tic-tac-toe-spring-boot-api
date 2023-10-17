package com.flama.tictactoe.models;

public class NextPlayResponseModel {
    private int[] aiNextPlay = new int[]{};
    private String error;

    public int[] getAiNextPlay() {
        return aiNextPlay;
    }

    public void setAiNextPlay(int[] aiNextPlay) {
        this.aiNextPlay = aiNextPlay;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
