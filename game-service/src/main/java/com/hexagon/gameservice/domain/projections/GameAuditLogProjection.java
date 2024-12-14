package com.hexagon.gameservice.domain.projections;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class GameAuditLogProjection {
    @Id
    @GeneratedValue
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long gameId;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String type;

    @Column(length = 50)
    private String difficulty;

    @Column(length = 80)
    private String topic;

    @Column(length = 8)
    private String status;

    @Column
    private LocalDateTime createdAt;

    public GameAuditLogProjection() {
    }

    public GameAuditLogProjection(Long gameId, String name, String type, String difficulty, String topic, String status, LocalDateTime createdAt) {
        this.gameId = gameId;
        this.name = name;
        this.type = type;
        this.difficulty = difficulty;
        this.topic = topic;
        this.status = status;
        this.createdAt = createdAt;
    }

    public GameAuditLogProjection(GameAuditLogProjection view) {
        this.gameId = view.getGameId();
        this.name = view.getName();
        this.type = view.getType();
        this.difficulty = view.getDifficulty();
        this.topic = view.getTopic();
        this.status = view.getStatus();
        this.createdAt = view.getCreatedAt();
    }
}
