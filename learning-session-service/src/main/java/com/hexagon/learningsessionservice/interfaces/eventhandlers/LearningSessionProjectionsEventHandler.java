package com.hexagon.learningsessionservice.interfaces.eventhandlers;

import com.hexagon.learningsessionservice.domain.events.LearningSessionEdited;
import com.hexagon.learningsessionservice.domain.events.LearningSessionRegistered;
import com.hexagon.learningsessionservice.domain.model.valueobjects.LearningSessionStatus;
import com.hexagon.learningsessionservice.domain.projections.LearningSessionProjection;
import com.hexagon.learningsessionservice.repositories.LearningSessionRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LearningSessionProjectionsEventHandler {
    private final LearningSessionRepository learningSessionRepository;

    public LearningSessionProjectionsEventHandler(LearningSessionRepository learningSessionRepository) {
        this.learningSessionRepository = learningSessionRepository;
    }

    @EventHandler
    public void on(LearningSessionRegistered event) {
        LearningSessionProjection learningSession = new LearningSessionProjection(
                event.getId(),
                event.getDuration(),
                event.getBeginning(),
                event.getDevelopment(),
                event.getConclusion(),
                LearningSessionStatus.ACTIVE.name(),
                event.getCreatedAt());
        learningSessionRepository.save(learningSession);
    }

    @EventHandler
    public void on(LearningSessionEdited event) {
        Optional<LearningSessionProjection> viewOptional = learningSessionRepository.findById(event.getId());
        if (viewOptional.isPresent()) {
            LearningSessionProjection view = viewOptional.get();
            view.setDuration(event.getDuration());
            view.setBeginning(event.getBeginning());
            view.setDevelopment(event.getDevelopment());
            view.setConclusion(event.getConclusion());
            view.setUpdatedAt(event.getUpdatedAt());
            learningSessionRepository.save(view);
        }
    }
}
