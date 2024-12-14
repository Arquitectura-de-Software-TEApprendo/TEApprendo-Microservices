package com.hexagon.gameservice.interfaces.rest.resources;

import com.hexagon.gameservice.domain.projections.GameProjection;
import com.hexagon.gameservice.shared.domain.model.valueobjects.Error;

import java.util.List;

public record GetGamesResponseResource(
    List<GameProjection> success,
    List<Error> errors
) {}
