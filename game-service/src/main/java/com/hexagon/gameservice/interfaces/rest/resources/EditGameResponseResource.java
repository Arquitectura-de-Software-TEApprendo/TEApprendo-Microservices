package com.hexagon.gameservice.interfaces.rest.resources;

import com.hexagon.gameservice.shared.domain.model.valueobjects.Error;

import java.util.List;

public record EditGameResponseResource(
        GameResource success,
        List<Error> errors
) {}
