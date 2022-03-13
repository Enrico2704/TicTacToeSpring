package com.example.TicTacToe;

import java.util.Arrays;
import java.util.Optional;

class InvalidTicTacToeInput extends RuntimeException {
    InvalidTicTacToeInput(String msg) {
        super(msg);

    }
}

public class TicTacToeLogic {


    CellStatus[][] gameTable = new CellStatus[3][3];
    Player currentPlayer; //o player 1 o player 2


    TicTacToeLogic() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                gameTable[i][j] = CellStatus.E;
        currentPlayer = Player.X;
    }

    public TicTacToeLogic(TicTacToeMove ticTacToeMove) {
        this.gameTable = GameTableSerializer.deserialize(ticTacToeMove.gameTableSerialized);
        this.currentPlayer = ticTacToeMove.currentPlayer;
    }

    static private Optional<Player> getWinner(CellStatus c) {
        return Optional.of(c == CellStatus.X ? Player.X : Player.O);
    }

    public void makeMove(int i, int j) {
        if ((i < 0 || i > 2) && (j < 0 || j > 2)) {
            throw new InvalidTicTacToeInput("Out of Bounds");
        }
        if (gameTable[i][j] != CellStatus.E) {
            throw new InvalidTicTacToeInput("Position already used");
        }
        gameTable[i][j] = (currentPlayer == Player.X) ? CellStatus.X : CellStatus.O;
        currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;

    }


    public Optional<Player> getTheWinner() {

        if (gameTable[0][0] != CellStatus.E && gameTable[0][0] == gameTable[0][1] && gameTable[0][1] == gameTable[0][2]) {
            return getWinner(gameTable[0][0]);
        }
        if (gameTable[1][0] == gameTable[1][1] && gameTable[1][1] == gameTable[1][2]) {
            return getWinner(gameTable[1][0]);
        }
        if (gameTable[2][0] != CellStatus.E && gameTable[2][0] == gameTable[2][1] && gameTable[2][1] == gameTable[2][2]) {
            return getWinner(gameTable[2][0]);
        }
        if (gameTable[0][0] != CellStatus.E && gameTable[0][0] == gameTable[1][0] && gameTable[1][0] == gameTable[2][0]) {
            return getWinner(gameTable[0][0]);
        }
        if (gameTable[0][1] != CellStatus.E && gameTable[0][1] == gameTable[1][1] && gameTable[1][1] == gameTable[2][1]) {
            return getWinner(gameTable[0][1]);
        }
        if (gameTable[0][2] != CellStatus.E && gameTable[0][2] == gameTable[1][2] && gameTable[1][2] == gameTable[2][2]) {
            return getWinner(gameTable[0][2]);
        }
        if (gameTable[0][0] != CellStatus.E && gameTable[0][0] == gameTable[1][1] && gameTable[1][1] == gameTable[2][2]) {
            return getWinner(gameTable[0][0]);
        }
        if (gameTable[0][2] != CellStatus.E && gameTable[0][2] == gameTable[1][1] && gameTable[1][1] == gameTable[2][0]) {
            return getWinner(gameTable[0][2]);
        }
        return Optional.empty();
    }

    public boolean isDraw() {
        return Arrays.stream(gameTable).flatMap(Arrays::stream).allMatch(cell -> cell != CellStatus.E.E);
    }

    public boolean isGameOver() {
        return Arrays.stream(gameTable)
                .allMatch(r -> Arrays.stream(r).allMatch(c -> c != CellStatus.E));

    }

    public void makeaMove(int i, int j) {
        gameTable[i][j] = currentPlayer == Player.X ? CellStatus.X : CellStatus.O;
        currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
    }

    public boolean isMoveValid(int i, int j) {
        return gameTable[i][j] == CellStatus.E;
    }

}
