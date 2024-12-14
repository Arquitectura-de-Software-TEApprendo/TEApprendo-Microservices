package com.hexagon.gameservice.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record RegisterGameResource (
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long id,
    String name,
    String type,
    String difficulty,
    String topic,

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    LocalDateTime createdAt
) {
    public RegisterGameResource withId(Long id) {
        LocalDateTime createdAt = LocalDateTime.now();
        return new RegisterGameResource(id, this.name, this.type, this.difficulty, this.topic, createdAt);
    }
}
