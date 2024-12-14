package com.hexagon.learningsessionservice.interfaces.rest.transform;

import com.hexagon.learningsessionservice.domain.commands.EditLearningSession;
import com.hexagon.learningsessionservice.interfaces.rest.resources.EditLearningSessionResource;

public class EditLearningSessionCommandFromResourceAssembler {
    public static EditLearningSession toCommandFromResource(EditLearningSessionResource resource) {
        return new EditLearningSession(
                resource.id(),
                resource.duration(),
                resource.beginning(),
                resource.development(),
                resource.conclusion(),
                resource.updatedAt());
    }
}
