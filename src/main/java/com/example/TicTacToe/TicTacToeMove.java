package com.example.TicTacToe;

import javax.persistence.*;

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
        this(new TicTacToeLogic());
    }

    public TicTacToeMove(TicTacToeLogic ticTacToeLogic) {
        this.gameTableSerialized = GameTableSerializer.serialize(ticTacToeLogic.gameTable);
        this.currentPlayer = ticTacToeLogic.currentPlayer;
    }

    public Long getId() {
        return id;
    }
}

