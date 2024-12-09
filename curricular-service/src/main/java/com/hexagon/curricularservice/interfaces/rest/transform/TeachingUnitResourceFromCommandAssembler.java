package com.hexagon.curricularservice.interfaces.rest.transform;

import com.hexagon.curricularservice.domain.commands.EditTeachingUnit;
import com.hexagon.curricularservice.domain.commands.RegisterTeachingUnit;
import com.hexagon.curricularservice.interfaces.rest.resources.TeachingUnitResource;

public class TeachingUnitResourceFromCommandAssembler {
    public static TeachingUnitResource toResourceFromRegisterTeachingUnit(RegisterTeachingUnit command) {
        return new TeachingUnitResource(
            command.getId(),
            command.getName(),
            command.getDuration(),
            command.getPurpose());
    }

    public static TeachingUnitResource toResourceFromEditTeachingUnit(EditTeachingUnit command) {
        return new TeachingUnitResource(
                command.getId(),
                command.getName(),
                command.getDuration(),
                command.getPurpose());
    }
}
