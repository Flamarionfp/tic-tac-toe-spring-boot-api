package com.flama.tictactoe.models;

public class VerifyValidPlayResponseModel extends ErrorResponseModel {
    private boolean isValidPlay;

    public boolean getIsValidPlay() {
        return isValidPlay;
    }

    public void setIsValidPlay(boolean isValidPlay) {
        this.isValidPlay = isValidPlay;
    }
}
