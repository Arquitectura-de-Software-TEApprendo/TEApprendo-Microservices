package com.hexagon.curricularservice.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record EditTeachingUnitResource (
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long id,
    String name,
    String duration,
    String purpose,

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    LocalDateTime updatedAt
) {
    public EditTeachingUnitResource withId(Long id) {
        LocalDateTime updatedAt = LocalDateTime.now();
        return new EditTeachingUnitResource(id, this.name, this.duration, this.purpose, updatedAt);
    }
}
