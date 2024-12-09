package com.hexagon.curricularservice.interfaces.rest.transform;

import com.hexagon.curricularservice.domain.commands.EditTeachingUnit;
import com.hexagon.curricularservice.interfaces.rest.resources.EditTeachingUnitResource;

public class EditTeachingUnitCommandFromResourceAssembler {
    public static EditTeachingUnit toCommandFromResource(EditTeachingUnitResource resource) {
        return new EditTeachingUnit(
            resource.id(),
            resource.name(),
            resource.duration(),
            resource.purpose(),
            resource.updatedAt());
    }
}
