package com.hexagon.curricularservice.interfaces.eventhandlers;

import com.hexagon.curricularservice.domain.events.TeachingUnitEdited;
import com.hexagon.curricularservice.domain.events.TeachingUnitRegistered;
import com.hexagon.curricularservice.domain.model.valueobjects.TeachingUnitStatus;
import com.hexagon.curricularservice.domain.projections.TeachingUnitAuditLogProjection;
import com.hexagon.curricularservice.repositories.TeachingUnitAuditLogRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TeachingUnitAuditLogProjectionsEventHandler {
    private final TeachingUnitAuditLogRepository teachingUnitAuditRepository;

    public TeachingUnitAuditLogProjectionsEventHandler(TeachingUnitAuditLogRepository teachingUnitAuditRepository) {
        this.teachingUnitAuditRepository = teachingUnitAuditRepository;
    }

    @EventHandler
    public void on(TeachingUnitRegistered event) {
        TeachingUnitAuditLogProjection view = new TeachingUnitAuditLogProjection(
                event.getId(),
                event.getName(),
                event.getDuration(),
                event.getPurpose(),
                TeachingUnitStatus.ACTIVE.name(),
                event.getCreatedAt());
        teachingUnitAuditRepository.save(view);
    }

    @EventHandler
    public void on(TeachingUnitEdited event) {
        Optional<TeachingUnitAuditLogProjection> viewOptional = teachingUnitAuditRepository.getLastByTeachingUnitId(event.getId());
        if (viewOptional.isPresent()) {
            TeachingUnitAuditLogProjection lastTeachingUnitAudit = viewOptional.get();
            TeachingUnitAuditLogProjection teachingUnitAudit = new TeachingUnitAuditLogProjection(lastTeachingUnitAudit);
            teachingUnitAudit.setName(event.getName());
            teachingUnitAudit.setDuration(event.getDuration());
            teachingUnitAudit.setPurpose(event.getPurpose());
            teachingUnitAudit.setCreatedAt(event.getUpdatedAt());
            teachingUnitAuditRepository.save(teachingUnitAudit);
        }
    }
}
