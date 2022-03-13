package com.example.TicTacToe;

import java.util.Arrays;
import java.util.stream.Collectors;

public class GameTableSerializer {
    public static CellStatus[][] deserialize(String serializedBoard) {
        return Arrays.stream(serializedBoard.split(";"))
                .map(r -> Arrays.stream(r.split(",")).map(CellStatus::valueOf).toArray(CellStatus[]::new))
                .toArray(CellStatus[][]::new);
    }

    public static String serialize(CellStatus[][] board) {
        return Arrays.stream(board)
                .map(r -> Arrays.stream(r).map(CellStatus::toString).collect(Collectors.joining(",")))
                .collect(Collectors.joining(";"));
    }
}
