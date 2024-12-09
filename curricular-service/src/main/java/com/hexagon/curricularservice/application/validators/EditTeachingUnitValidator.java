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

        String firstName = command.getName().trim();
        if (firstName.isEmpty()) notification.addError("Teaching Unit name is required");

        String lastName = command.getDuration().trim();
        if (lastName.isEmpty()) notification.addError("Teaching Unit duration is required");

        String dni = command.getPurpose().trim();
        if (dni.isEmpty()) notification.addError("Teaching Unit purpose is required");

        return notification;
    }
}
