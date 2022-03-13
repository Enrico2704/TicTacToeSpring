package com.example.TicTacToe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


//class TicTacToeGameArray {
//    public CellStatus[][] gameTable = new CellStatus[3][3];
//    public Player currentPlayer; //o player 1 o player 2
//
//    public TicTacToeGameArray() {
//        for (int i = 0; i < 3; i++)
//            for (int j = 0; j < 3; j++)
//                gameTable[i][j] = CellStatus.E;
//        currentPlayer = Player.X;
//    }
//}

@RestController
public class TicTacToeMoveController {


    private final TicTacToeMoveRepository ticTacToeMoveRepository;

    public TicTacToeMoveController(TicTacToeMoveRepository ticTacToeMoveRepository) {
        this.ticTacToeMoveRepository = ticTacToeMoveRepository;


    }

    @GetMapping("/createNewGame")
    public TicTacToeMove createNewGame() {
        TicTacToeMove move = new TicTacToeMove();
        return ticTacToeMoveRepository.save(move);
    }

    @PostMapping("/makeMove/{i}/{j}")
    public TicTacToeMove makeMove(@PathVariable Integer i, @PathVariable Integer j) {
        var previous = ticTacToeMoveRepository.findTopByOrderByIdDesc();
        if (previous.isEmpty()) throw new IllegalArgumentException("Game not found");

        var newGame = new TicTacToeLogic(previous.get());

        if (!newGame.isMoveValid(i, j)) throw new IllegalArgumentException("Move not valid");
        if (newGame.isGameOver()) throw new IllegalArgumentException("Game is over");

//        if (!(newGame.isDraw() || newGame.getTheWinner().isPresent()))
        newGame.makeMove(i, j);

        return ticTacToeMoveRepository.save(new TicTacToeMove(newGame));
    }


}
