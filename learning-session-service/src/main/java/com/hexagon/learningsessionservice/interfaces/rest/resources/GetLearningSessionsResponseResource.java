package com.hexagon.learningsessionservice.interfaces.rest.resources;

import com.hexagon.learningsessionservice.domain.projections.LearningSessionProjection;
import com.hexagon.learningsessionservice.shared.domain.model.valueobjects.Error;

import java.util.List;

public record GetLearningSessionsResponseResource(
    List<LearningSessionProjection> success,
    List<Error> errors
) {}
