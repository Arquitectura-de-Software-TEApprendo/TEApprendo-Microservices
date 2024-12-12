package com.hexagon.learningsessionservice.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;

public record LearningSessionResource(
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        Long id,
        String duration,
        String beginning,
        String development,
        String conclusion
) {
}
