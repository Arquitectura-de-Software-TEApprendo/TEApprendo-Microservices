package com.hexagon.gameservice.interfaces.rest.resources;

import com.hexagon.gameservice.domain.projections.GameAuditLogProjection;
import com.hexagon.gameservice.shared.domain.model.valueobjects.Error;

import java.util.List;

public record GameAuditLogResponseResource(
    List<GameAuditLogProjection> success,
    List<Error> errors
) {}
