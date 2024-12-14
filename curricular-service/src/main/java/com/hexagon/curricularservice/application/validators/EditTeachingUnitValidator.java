package com.hexagon.curricularservice.application.validators;

import com.hexagon.curricularservice.domain.commands.EditTeachingUnit;
import com.hexagon.curricularservice.shared.domain.model.valueobjects.Notification;
import org.springframework.stereotype.Component;

@Component
public class EditTeachingUnitValidator {

    public EditTeachingUnitValidator() {
    }

    public Notification validate(EditTeachingUnit command) {
        Notification notification = new Notification();

        Long id = command.getId();
        if (id <= 0) notification.addError("Teaching Unit id must be greater than 0");

        String name = command.getName().trim();
        if (name.isEmpty()) notification.addError("Teaching Unit name is required");

        String duration = command.getDuration().trim();
        if (duration.isEmpty()) notification.addError("Teaching Unit duration is required");

        String purpose = command.getPurpose().trim();
        if (purpose.isEmpty()) notification.addError("Teaching Unit purpose is required");

        return notification;
    }
}
