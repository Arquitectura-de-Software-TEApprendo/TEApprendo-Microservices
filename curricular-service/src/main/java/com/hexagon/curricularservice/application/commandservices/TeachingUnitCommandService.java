package com.hexagon.curricularservice.application.commandservices;

import com.hexagon.curricularservice.application.validators.EditTeachingUnitValidator;
import com.hexagon.curricularservice.application.validators.RegisterTeachingUnitValidator;
import com.hexagon.curricularservice.domain.commands.EditTeachingUnit;
import com.hexagon.curricularservice.domain.commands.RegisterTeachingUnit;
import com.hexagon.curricularservice.shared.domain.model.valueobjects.Notification;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

@Component
public class TeachingUnitCommandService {
    private final CommandGateway commandGateway;
    private final RegisterTeachingUnitValidator registerTeachingUnitValidator;
    private final EditTeachingUnitValidator editTeachingUnitValidator;

    public TeachingUnitCommandService(CommandGateway commandGateway, RegisterTeachingUnitValidator registerTeachingUnitValidator, EditTeachingUnitValidator editTeachingUnitValidator) {
        this.commandGateway = commandGateway;
        this.registerTeachingUnitValidator = registerTeachingUnitValidator;
        this.editTeachingUnitValidator = editTeachingUnitValidator;
    }

    public Notification register(RegisterTeachingUnit command) throws Exception {
        Notification notification = this.registerTeachingUnitValidator.validate(command);
        if (notification.hasErrors()) return notification;
        commandGateway.sendAndWait(command);
        return notification;
    }

    public Notification edit(EditTeachingUnit command) throws Exception {
        Notification notification = this.editTeachingUnitValidator.validate(command);
        if (notification.hasErrors()) return notification;
        commandGateway.sendAndWait(command);
        return notification;
    }
}
