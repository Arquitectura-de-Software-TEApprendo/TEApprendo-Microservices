package com.hexagon.learningsessionservice.interfaces.eventhandlers;

import com.hexagon.learningsessionservice.domain.events.LearningSessionEdited;
import com.hexagon.learningsessionservice.domain.events.LearningSessionRegistered;
import com.hexagon.learningsessionservice.domain.model.valueobjects.LearningSessionStatus;
import com.hexagon.learningsessionservice.domain.projections.LearningSessionAuditLogProjection;
import com.hexagon.learningsessionservice.repositories.LearningSessionAuditLogRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LearningSessionAuditLogProjectionsEventHandler {
    private final LearningSessionAuditLogRepository learningSessionAuditRepository;

    public LearningSessionAuditLogProjectionsEventHandler(LearningSessionAuditLogRepository learningSessionAuditRepository) {
        this.learningSessionAuditRepository = learningSessionAuditRepository;
    }

    @EventHandler
    public void on(LearningSessionRegistered event) {
        LearningSessionAuditLogProjection view = new LearningSessionAuditLogProjection(
                event.getId(),
                event.getDuration(),
                event.getBeginning(),
                event.getDevelopment(),
                event.getConclusion(),
                LearningSessionStatus.ACTIVE.name(),
                event.getCreatedAt());
        learningSessionAuditRepository.save(view);
    }

    @EventHandler
    public void on(LearningSessionEdited event) {
        Optional<LearningSessionAuditLogProjection> viewOptional = learningSessionAuditRepository.getLastByLearningSessionId(event.getId());
        if (viewOptional.isPresent()) {
            LearningSessionAuditLogProjection lastLearningSessionAudit = viewOptional.get();
            LearningSessionAuditLogProjection learningSessionAudit = new LearningSessionAuditLogProjection(lastLearningSessionAudit);
            learningSessionAudit.setDuration(event.getDuration());
            learningSessionAudit.setBeginning(event.getBeginning());
            learningSessionAudit.setDevelopment(event.getDevelopment());
            learningSessionAudit.setConclusion(event.getConclusion());
            learningSessionAudit.setCreatedAt(event.getUpdatedAt());
            learningSessionAuditRepository.save(learningSessionAudit);
        }
    }
}
