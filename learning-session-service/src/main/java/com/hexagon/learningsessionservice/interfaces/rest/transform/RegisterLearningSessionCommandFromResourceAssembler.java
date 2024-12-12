package com.hexagon.learningsessionservice.interfaces.rest.transform;

import com.hexagon.learningsessionservice.domain.commands.RegisterLearningSession;
import com.hexagon.learningsessionservice.interfaces.rest.resources.RegisterLearningSessionResource;

public class RegisterLearningSessionCommandFromResourceAssembler {
    public static RegisterLearningSession toCommandFromResource(RegisterLearningSessionResource resource) {
        return new RegisterLearningSession(
                resource.id(),
                resource.name(),
                resource.duration(),
                resource.purpose(),
                resource.createdAt());
    }
}
