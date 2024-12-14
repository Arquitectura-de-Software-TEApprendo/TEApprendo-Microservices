package com.hexagon.gameservice.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;

public record GameResource (
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Long id,
    String name,
    String type,
    String difficulty,
    String topic
) {}
