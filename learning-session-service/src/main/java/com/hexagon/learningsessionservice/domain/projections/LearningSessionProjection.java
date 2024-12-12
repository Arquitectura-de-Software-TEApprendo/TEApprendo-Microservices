package com.hexagon.learningsessionservice.domain.projections;

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
public class LearningSessionProjection {
    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @Column(length = 50)
    private String duration;

    @Column(length = 250)
    private String beginning;

    @Column(length = 250)
    private String development;

    @Column(length = 250)
    private String conclusion;

    @Column(length = 8)
    private String status;

    @Column
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime updatedAt;

    public LearningSessionProjection() {
    }

    public LearningSessionProjection(Long id, String duration, String beginning, String development, String conclusion, String status, LocalDateTime createdAt) {
        this.id = id;
        this.duration = duration;
        this.beginning = beginning;
        this.development = development;
        this.conclusion = conclusion;
        this.status = status;
        this.createdAt = createdAt;
    }
}
