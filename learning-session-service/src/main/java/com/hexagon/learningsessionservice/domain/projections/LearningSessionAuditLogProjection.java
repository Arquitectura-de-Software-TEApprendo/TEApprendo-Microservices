package com.hexagon.learningsessionservice.domain.projections;

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
public class LearningSessionAuditLogProjection {
    @Id
    @GeneratedValue
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long learningSessionId;

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

    public LearningSessionAuditLogProjection() {
    }

    public LearningSessionAuditLogProjection(Long learningSessionId, String duration, String beginning, String development, String conclusion, String status, LocalDateTime createdAt) {
        this.learningSessionId = learningSessionId;
        this.duration = duration;
        this.beginning = beginning;
        this.development = development;
        this.conclusion = conclusion;
        this.status = status;
        this.createdAt = createdAt;
    }

    public LearningSessionAuditLogProjection(LearningSessionAuditLogProjection view) {
        this.learningSessionId = view.getLearningSessionId();
        this.duration = view.getDuration();
        this.beginning = view.getBeginning();
        this.development = view.getDevelopment();
        this.conclusion = view.getConclusion();
        this.status = view.getStatus();
        this.createdAt = view.getCreatedAt();
    }
}
