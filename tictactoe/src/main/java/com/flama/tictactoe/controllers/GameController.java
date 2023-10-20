package com.flama.tictactoe.controllers;

import com.flama.tictactoe.entities.Board;
import com.flama.tictactoe.entities.Game;
import com.flama.tictactoe.entities.Player;
import com.flama.tictactoe.models.VerifyGameoverRequestModel;
import com.flama.tictactoe.models.VerifyGameoverResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {
    @PostMapping("verify-game-over")
    public ResponseEntity<VerifyGameoverResponseModel> verifyGameover(@RequestBody VerifyGameoverRequestModel requestBody) {
        var response = new VerifyGameoverResponseModel();

        try {
            Board board = new Board(requestBody.getBoard());
            Game game = new Game(board);
            Player currentPlayer = new Player(requestBody.getCurrentPlayerSymbol());
            game.verifyGameOver(currentPlayer);
            Player winner = game.getWinner();

            if (winner != null) {
                Character winnerSymbol = winner.getSymbol();
                response.setWinner(winnerSymbol);
            }

            response.setGameover(game.isGameover());

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setError(e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }
}
