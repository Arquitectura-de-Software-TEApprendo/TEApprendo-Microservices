package com.hexagon.learningsessionservice.application.validators;

import com.hexagon.learningsessionservice.domain.commands.EditLearningSession;
import com.hexagon.learningsessionservice.shared.domain.model.valueobjects.Notification;
import org.springframework.stereotype.Component;

@Component
public class EditLearningSessionValidator {

    public EditLearningSessionValidator() {
    }

    public Notification validate(EditLearningSession command) {
        Notification notification = new Notification();

        Long id = command.getId();
        if (id <= 0) notification.addError("Learning Session id must be greater than 0");

        String firstName = command.getName().trim();
        if (firstName.isEmpty()) notification.addError("Learning Session name is required");

        String lastName = command.getDuration().trim();
        if (lastName.isEmpty()) notification.addError("Learning Session duration is required");

        String dni = command.getPurpose().trim();
        if (dni.isEmpty()) notification.addError("Learning Session purpose is required");

        return notification;
    }
}
