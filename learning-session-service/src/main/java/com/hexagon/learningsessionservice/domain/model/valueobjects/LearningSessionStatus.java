package com.hexagon.learningsessionservice.domain.model.valueobjects;

public enum LearningSessionStatus {
    ACTIVE (1),
    INACTIVE(0);

    private int id;

    LearningSessionStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
