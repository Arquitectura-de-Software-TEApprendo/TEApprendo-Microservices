package com.hexagon.curricularservice.interfaces.rest.resources;

import com.hexagon.curricularservice.domain.projections.TeachingUnitAuditLogProjection;
import com.hexagon.curricularservice.shared.domain.model.valueobjects.Error;

import java.util.List;

public record TeachingUnitAuditLogResponseResource(
    List<TeachingUnitAuditLogProjection> success,
    List<Error> errors
) {}
