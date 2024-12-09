package com.hexagon.curricularservice.interfaces.rest.resources;

import com.hexagon.curricularservice.shared.domain.model.valueobjects.Error;

import java.util.List;

public record EditTeachingUnitResponseResource(
        TeachingUnitResource success,
        List<Error> errors
) {}
