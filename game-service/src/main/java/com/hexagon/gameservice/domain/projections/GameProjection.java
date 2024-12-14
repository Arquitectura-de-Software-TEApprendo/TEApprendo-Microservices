package com.hexagon.gameservice.domain.projections;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class GameProjection {
    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

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

    @Column(nullable = true)
    private LocalDateTime updatedAt;

    public GameProjection() {
    }

    public GameProjection(Long id, String name, String type, String difficulty, String topic, String status, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.difficulty = difficulty;
        this.topic = topic;
        this.status = status;
        this.createdAt = createdAt;
    }
}
