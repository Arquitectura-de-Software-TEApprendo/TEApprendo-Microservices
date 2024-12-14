package com.hexagon.gameservice.domain.model.valueobjects;

public enum GameStatus {
    ACTIVE (1),
    INACTIVE(0);

    private int id;

    GameStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
