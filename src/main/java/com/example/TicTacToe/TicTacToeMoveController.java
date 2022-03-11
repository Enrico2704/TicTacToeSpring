package com.example.TicTacToe;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


class TicTacToeGameArray {
    public CellStatus[][] gameTable = new CellStatus[3][3];
    public Player currentPlayer; //o player 1 o player 2

    public TicTacToeGameArray() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                gameTable[i][j] = CellStatus.E;
        currentPlayer = Player.X;
    }
}

@RestController
public class TicTacToeMoveController {


    private final TicTacToeMoveRepository ticTacToeMoveRepository;

    public TicTacToeMoveController(TicTacToeMoveRepository ticTacToeMoveRepository) {
        this.ticTacToeMoveRepository = ticTacToeMoveRepository;


    }

    @PostMapping("/createNewGame")
    public TicTacToeMove createNewGame() {
        TicTacToeMove move = new TicTacToeMove();
        return ticTacToeMoveRepository.save(move);
    }

    @PostMapping("/makeMove")
    public TicTacToeMove makeMove(@RequestParam int i, @RequestParam int j) {
        var move = ticTacToeMoveRepository.findTopByOrderByIdAsc().get();
        return ticTacToeMoveRepository.save(move);
    }


}
