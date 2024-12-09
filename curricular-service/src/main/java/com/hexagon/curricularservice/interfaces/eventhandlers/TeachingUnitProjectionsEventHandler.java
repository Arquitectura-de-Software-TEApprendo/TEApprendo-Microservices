package com.hexagon.curricularservice.interfaces.eventhandlers;

import com.hexagon.curricularservice.domain.events.TeachingUnitEdited;
import com.hexagon.curricularservice.domain.events.TeachingUnitRegistered;
import com.hexagon.curricularservice.domain.model.valueobjects.TeachingUnitStatus;
import com.hexagon.curricularservice.domain.projections.TeachingUnitProjection;
import com.hexagon.curricularservice.repositories.TeachingUnitRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TeachingUnitProjectionsEventHandler {
    private final TeachingUnitRepository teachingUnitRepository;

    public TeachingUnitProjectionsEventHandler(TeachingUnitRepository teachingUnitRepository) {
        this.teachingUnitRepository = teachingUnitRepository;
    }

    @EventHandler
    public void on(TeachingUnitRegistered event) {
        TeachingUnitProjection teachingUnit = new TeachingUnitProjection(
                event.getId(),
                event.getName(),
                event.getDuration(),
                event.getPurpose(),
                TeachingUnitStatus.ACTIVE.name(),
                event.getCreatedAt());
        teachingUnitRepository.save(teachingUnit);
    }

    @EventHandler
    public void on(TeachingUnitEdited event) {
        Optional<TeachingUnitProjection> viewOptional = teachingUnitRepository.findById(event.getId());
        if (viewOptional.isPresent()) {
            TeachingUnitProjection view = viewOptional.get();
            view.setName(event.getName());
            view.setDuration(event.getDuration());
            view.setPurpose(event.getPurpose());
            view.setUpdatedAt(event.getUpdatedAt());
            teachingUnitRepository.save(view);
        }
    }
}
