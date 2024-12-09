package com.hexagon.curricularservice.domain.projections;

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
public class TeachingUnitProjection {
    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String duration;

    @Column(length = 50)
    private String purpose;

    @Column(length = 150)
    private String status;

    @Column
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime updatedAt;

    public TeachingUnitProjection() {
    }

    public TeachingUnitProjection(Long id, String name, String duration, String purpose, String status, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.purpose = purpose;
        this.status = status;
        this.createdAt = createdAt;
    }
}
