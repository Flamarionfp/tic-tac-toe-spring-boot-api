package com.flama.tictactoe.models;

public class VerifyValidPlayResponseModel extends ErrorModel {
    private boolean isValidPlay;

    public boolean getIsValidPlay() {
        return isValidPlay;
    }

    public void setIsValidPlay(boolean isValidPlay) {
        this.isValidPlay = isValidPlay;
    }
}
