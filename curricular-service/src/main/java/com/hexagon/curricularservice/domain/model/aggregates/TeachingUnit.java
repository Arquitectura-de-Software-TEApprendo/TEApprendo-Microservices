package com.hexagon.curricularservice.domain.model.aggregates;

import com.hexagon.curricularservice.domain.commands.EditTeachingUnit;
import com.hexagon.curricularservice.domain.commands.RegisterTeachingUnit;
import com.hexagon.curricularservice.domain.events.TeachingUnitEdited;
import com.hexagon.curricularservice.domain.events.TeachingUnitRegistered;
import com.hexagon.curricularservice.domain.model.valueobjects.TeachingUnitStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class TeachingUnit {

    @AggregateIdentifier
    private Long id;
    private String name;
    private String duration;
    private String purpose;
    private TeachingUnitStatus status;

    public TeachingUnit() {
    }

    @CommandHandler
    public TeachingUnit(RegisterTeachingUnit command) {
        TeachingUnitRegistered event = new TeachingUnitRegistered(
                command.getId(),
                command.getName(),
                command.getDuration(),
                command.getPurpose(),
                command.getCreatedAt()
        );
        apply(event);
    }

    @CommandHandler
    public void handle(EditTeachingUnit command) {
        TeachingUnitEdited event = new TeachingUnitEdited(
                command.getId(),
                command.getName(),
                command.getDuration(),
                command.getPurpose(),
                command.getUpdatedAt()
        );
        apply(event);
    }

    @EventSourcingHandler
    protected void on(TeachingUnitRegistered event) {
        id = event.getId();
        name = event.getName();
        duration = event.getDuration();
        purpose = event.getPurpose();
        status = TeachingUnitStatus.ACTIVE;
    }

    @EventSourcingHandler
    protected void on(TeachingUnitEdited event) {
        name = event.getName();
        duration = event.getDuration();
        purpose = event.getPurpose();
    }
}
