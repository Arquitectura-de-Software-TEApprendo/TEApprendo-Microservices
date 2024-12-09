package com.hexagon.curricularservice.interfaces.rest.resources;

import com.hexagon.curricularservice.domain.projections.TeachingUnitProjection;
import com.hexagon.curricularservice.shared.domain.model.valueobjects.Error;

import java.util.List;

public record GetTeachingUnitsResponseResource(
    List<TeachingUnitProjection> success,
    List<Error> errors
) {}
