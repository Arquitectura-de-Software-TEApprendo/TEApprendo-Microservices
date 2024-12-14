package com.hexagon.learningsessionservice.interfaces.rest.transform;

import com.hexagon.learningsessionservice.domain.commands.RegisterLearningSession;
import com.hexagon.learningsessionservice.interfaces.rest.resources.RegisterLearningSessionResource;

public class RegisterLearningSessionCommandFromResourceAssembler {
    public static RegisterLearningSession toCommandFromResource(RegisterLearningSessionResource resource) {
        return new RegisterLearningSession(
                resource.id(),
                resource.duration(),
                resource.beginning(),
                resource.development(),
                resource.conclusion(),
                resource.createdAt());
    }
}
