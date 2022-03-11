package com.example.TicTacToe;

import javax.persistence.*;
import java.util.Arrays;
import java.util.stream.Collectors;

enum CellStatus {E, X, O}

enum Player {X, O}

@Entity()
public class TicTacToeMove {
    public String gameTableSerialized;
    public Player currentPlayer;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public TicTacToeMove() {
        this.currentPlayer = Player.X;
        this.gameTableSerialized = "E,E,E;E,E,E;E,E,E";
    }

    public TicTacToeMove(TicTacToeLogic ticTacToeLogic) {
        this.gameTableSerialized = Arrays.stream(ticTacToeLogic.gameTable)
                .map(r -> Arrays.stream(r).map(CellStatus::toString).collect(Collectors.joining(",")))
                .collect(Collectors.joining(";"));
        this.currentPlayer = ticTacToeLogic.currentPlayer;
    }

    public Long getId() {
        return id;
    }
}

