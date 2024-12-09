package com.hexagon.curricularservice.domain.projections;

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
public class TeachingUnitAuditLogProjection {
    @Id
    @GeneratedValue
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long teachingUnitId;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String duration;

    @Column(length = 150)
    private String purpose;

    @Column(length = 8)
    private String status;

    @Column
    private LocalDateTime createdAt;

    public TeachingUnitAuditLogProjection() {
    }

    public TeachingUnitAuditLogProjection(Long teachingUnitId, String name, String duration, String purpose, String status, LocalDateTime createdAt) {
        this.teachingUnitId = teachingUnitId;
        this.name = name;
        this.duration = duration;
        this.purpose = purpose;
        this.status = status;
        this.createdAt = createdAt;
    }

    public TeachingUnitAuditLogProjection(TeachingUnitAuditLogProjection view) {
        this.teachingUnitId = view.getTeachingUnitId();
        this.name = view.getName();
        this.duration = view.getDuration();
        this.purpose = view.getPurpose();
        this.status = view.getStatus();
        this.createdAt = view.getCreatedAt();
    }
}
