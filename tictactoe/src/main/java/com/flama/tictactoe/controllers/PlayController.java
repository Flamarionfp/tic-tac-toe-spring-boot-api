package com.flama.tictactoe.controllers;

import com.flama.tictactoe.entities.Board;
import com.flama.tictactoe.models.VerifyValidPlayRequestModel;
import com.flama.tictactoe.models.VerifyValidPlayResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/play")
public class PlayController {
    @PostMapping("/verify")
    public ResponseEntity<VerifyValidPlayResponseModel> verifyValidPlay(@RequestBody VerifyValidPlayRequestModel requestBody) {
        var response = new VerifyValidPlayResponseModel();

        try {
            Character[][] positions = requestBody.getBoard();
            Board board = new Board(positions);

            boolean isFilledPosition = board.isFilledPosition(requestBody.getRow(), requestBody.getColumn());
            response.setIsValidPlay(!isFilledPosition);

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setError(e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }
}
