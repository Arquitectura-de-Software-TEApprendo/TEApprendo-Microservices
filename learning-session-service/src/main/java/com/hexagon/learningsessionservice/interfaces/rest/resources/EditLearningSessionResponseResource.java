package com.hexagon.learningsessionservice.interfaces.rest.resources;

import com.hexagon.learningsessionservice.shared.domain.model.valueobjects.Error;

import java.util.List;

public record EditLearningSessionResponseResource(
        LearningSessionResource success,
        List<Error> errors
) {}
