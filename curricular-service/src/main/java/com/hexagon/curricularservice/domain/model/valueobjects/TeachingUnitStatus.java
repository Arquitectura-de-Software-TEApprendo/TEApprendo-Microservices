package com.hexagon.curricularservice.domain.model.valueobjects;

public enum TeachingUnitStatus {
    ACTIVE (1),
    INACTIVE(0);

    private int id;

    TeachingUnitStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
