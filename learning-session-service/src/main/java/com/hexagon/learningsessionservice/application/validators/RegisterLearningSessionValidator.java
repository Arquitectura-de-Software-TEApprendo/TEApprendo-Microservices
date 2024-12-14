package com.hexagon.learningsessionservice.application.validators;

import com.hexagon.learningsessionservice.domain.commands.RegisterLearningSession;
import com.hexagon.learningsessionservice.shared.domain.model.valueobjects.Notification;
import org.springframework.stereotype.Component;

@Component
public class RegisterLearningSessionValidator {

    public RegisterLearningSessionValidator() {
    }

    public Notification validate(RegisterLearningSession command) {
        Notification notification = new Notification();

        Long id = command.getId();
        if (id <= 0) notification.addError("Learning Session id must be greater than 0");

        String duration = command.getDuration().trim();
        if (duration.isEmpty()) notification.addError("Learning Session duration is required");

        String beginning = command.getBeginning().trim();
        if (beginning.isEmpty()) notification.addError("Learning Session beginning is required");

        String development = command.getDevelopment().trim();
        if (development.isEmpty()) notification.addError("Learning Session development is required");

        String conclusion = command.getConclusion().trim();
        if (conclusion.isEmpty()) notification.addError("Learning Session conclusion is required");

        return notification;
    }
}
