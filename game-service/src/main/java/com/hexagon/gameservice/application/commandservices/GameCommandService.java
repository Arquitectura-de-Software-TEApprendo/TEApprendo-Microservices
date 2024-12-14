package com.hexagon.gameservice.application.commandservices;

import com.hexagon.gameservice.application.validators.EditGameValidator;
import com.hexagon.gameservice.application.validators.RegisterGameValidator;
import com.hexagon.gameservice.domain.commands.EditGame;
import com.hexagon.gameservice.domain.commands.RegisterGame;
import com.hexagon.gameservice.shared.domain.model.valueobjects.Notification;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

@Component
public class GameCommandService {
    private final CommandGateway commandGateway;
    private final RegisterGameValidator registerGameValidator;
    private final EditGameValidator editGameValidator;

    public GameCommandService(CommandGateway commandGateway, RegisterGameValidator registerGameValidator, EditGameValidator editGameValidator) {
        this.commandGateway = commandGateway;
        this.registerGameValidator = registerGameValidator;
        this.editGameValidator = editGameValidator;
    }

    public Notification register(RegisterGame command) throws Exception {
        Notification notification = this.registerGameValidator.validate(command);
        if (notification.hasErrors()) return notification;
        commandGateway.sendAndWait(command);
        return notification;
    }

    public Notification edit(EditGame command) throws Exception {
        Notification notification = this.editGameValidator.validate(command);
        if (notification.hasErrors()) return notification;
        commandGateway.sendAndWait(command);
        return notification;
    }
}
