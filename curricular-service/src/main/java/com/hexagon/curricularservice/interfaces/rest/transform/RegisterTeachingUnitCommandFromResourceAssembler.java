package com.hexagon.curricularservice.interfaces.rest.transform;

import com.hexagon.curricularservice.domain.commands.RegisterTeachingUnit;
import com.hexagon.curricularservice.interfaces.rest.resources.RegisterTeachingUnitResource;

public class RegisterTeachingUnitCommandFromResourceAssembler {
    public static RegisterTeachingUnit toCommandFromResource(RegisterTeachingUnitResource resource) {
        return new RegisterTeachingUnit(
                resource.id(),
                resource.name(),
                resource.duration(),
                resource.purpose(),
                resource.createdAt());
    }
}
