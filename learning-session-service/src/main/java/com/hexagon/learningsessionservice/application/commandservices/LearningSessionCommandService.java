package com.hexagon.learningsessionservice.application.commandservices;

import com.hexagon.learningsessionservice.application.validators.EditLearningSessionValidator;
import com.hexagon.learningsessionservice.application.validators.RegisterLearningSessionValidator;
import com.hexagon.learningsessionservice.domain.commands.EditLearningSession;
import com.hexagon.learningsessionservice.domain.commands.RegisterLearningSession;
import com.hexagon.learningsessionservice.shared.domain.model.valueobjects.Notification;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

@Component
public class LearningSessionCommandService {
    private final CommandGateway commandGateway;
    private final RegisterLearningSessionValidator registerLearningSessionValidator;
    private final EditLearningSessionValidator editLearningSessionValidator;

    public LearningSessionCommandService(CommandGateway commandGateway, RegisterLearningSessionValidator registerLearningSessionValidator, EditLearningSessionValidator editLearningSessionValidator) {
        this.commandGateway = commandGateway;
        this.registerLearningSessionValidator = registerLearningSessionValidator;
        this.editLearningSessionValidator = editLearningSessionValidator;
    }

    public Notification register(RegisterLearningSession command) throws Exception {
        Notification notification = this.registerLearningSessionValidator.validate(command);
        if (notification.hasErrors()) return notification;
        commandGateway.sendAndWait(command);
        return notification;
    }

    public Notification edit(EditLearningSession command) throws Exception {
        Notification notification = this.editLearningSessionValidator.validate(command);
        if (notification.hasErrors()) return notification;
        commandGateway.sendAndWait(command);
        return notification;
    }
}
