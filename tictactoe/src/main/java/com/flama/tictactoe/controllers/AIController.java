package com.flama.tictactoe.controllers;

import com.flama.tictactoe.entities.AI;
import com.flama.tictactoe.entities.Board;
import com.flama.tictactoe.models.NextPlayRequestModel;
import com.flama.tictactoe.models.NextPlayModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")

public class AIController {
    @PostMapping("/next-play")
    public ResponseEntity<NextPlayModel> nextPlay(@RequestBody NextPlayRequestModel requestBody) {
       Character[][] currentBoardPositions = requestBody.getBoard();

       Board board = new Board(currentBoardPositions);
       AI cpu = new AI(requestBody.getIaSymbol());
       var response = new NextPlayModel();

        try {
           int[] positionToPlay = cpu.getBetterPositionToPlay(board);
            response.setAiNextPlay(positionToPlay);

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setError(e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }
}