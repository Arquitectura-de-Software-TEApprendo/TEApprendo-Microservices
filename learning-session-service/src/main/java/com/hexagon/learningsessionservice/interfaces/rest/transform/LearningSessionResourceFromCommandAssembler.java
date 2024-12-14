package com.hexagon.learningsessionservice.interfaces.rest.transform;

import com.hexagon.learningsessionservice.domain.commands.EditLearningSession;
import com.hexagon.learningsessionservice.domain.commands.RegisterLearningSession;
import com.hexagon.learningsessionservice.interfaces.rest.resources.LearningSessionResource;

public class LearningSessionResourceFromCommandAssembler {
    public static LearningSessionResource toResourceFromRegisterLearningSession(RegisterLearningSession command) {
        return new LearningSessionResource(
                command.getId(),
                command.getDuration(),
                command.getBeginning(),
                command.getDevelopment(),
                command.getConclusion());
    }

    public static LearningSessionResource toResourceFromEditLearningSession(EditLearningSession command) {
        return new LearningSessionResource(
                command.getId(),
                command.getDuration(),
                command.getBeginning(),
                command.getDevelopment(),
                command.getConclusion());
    }
}
