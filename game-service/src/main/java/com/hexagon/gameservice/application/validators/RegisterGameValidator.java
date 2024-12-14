package com.hexagon.gameservice.application.validators;

import com.hexagon.gameservice.domain.commands.RegisterGame;
import com.hexagon.gameservice.shared.domain.model.valueobjects.Notification;
import org.springframework.stereotype.Component;

@Component
public class RegisterGameValidator {

    public RegisterGameValidator() {
    }

    public Notification validate(RegisterGame command) {
        Notification notification = new Notification();

        Long id = command.getId();
        if (id <= 0) notification.addError("Game id must be greater than 0");

        String name = command.getName().trim();
        if (name.isEmpty()) notification.addError("Game name is required");

        String type = command.getType().trim();
        if (type.isEmpty()) notification.addError("Game type is required");

        String difficulty = command.getDifficulty().trim();
        if (difficulty.isEmpty()) notification.addError("Game difficulty is required");

        String topic = command.getTopic().trim();
        if (type.isEmpty()) notification.addError("Game topic is required");

        return notification;
    }
}
