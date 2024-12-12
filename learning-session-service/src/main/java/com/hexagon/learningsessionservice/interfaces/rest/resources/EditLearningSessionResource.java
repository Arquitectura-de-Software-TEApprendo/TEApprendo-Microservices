package com.hexagon.learningsessionservice.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record EditLearningSessionResource(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        Long id,
        String duration,
        String beginning,
        String development,
        String conclusion,

        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        LocalDateTime updatedAt
) {
    public EditLearningSessionResource withId(Long id) {
        LocalDateTime updatedAt = LocalDateTime.now();
        return new EditLearningSessionResource(id, this.duration, this.beginning, this.development, this.conclusion, updatedAt);
    }
}
