package com.hexagon.learningsessionservice.interfaces.rest.resources;

import com.hexagon.learningsessionservice.domain.projections.LearningSessionAuditLogProjection;
import com.hexagon.learningsessionservice.shared.domain.model.valueobjects.Error;

import java.util.List;

public record LearningSessionAuditLogResponseResource(
    List<LearningSessionAuditLogProjection> success,
    List<Error> errors
) {}
