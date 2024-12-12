package com.hexagon.learningsessionservice.domain.model.aggregates;

import com.hexagon.learningsessionservice.domain.commands.EditLearningSession;
import com.hexagon.learningsessionservice.domain.commands.RegisterLearningSession;
import com.hexagon.learningsessionservice.domain.events.LearningSessionEdited;
import com.hexagon.learningsessionservice.domain.events.LearningSessionRegistered;
import com.hexagon.learningsessionservice.domain.model.valueobjects.LearningSessionStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class LearningSession {

    @AggregateIdentifier
    private Long id;
    private String duration;
    private String beginning;
    private String development;
    private String conclusion;
    private LearningSessionStatus status;

    public LearningSession() {
    }

    @CommandHandler
    public LearningSession(RegisterLearningSession command) {
        LearningSessionRegistered event = new LearningSessionRegistered(
                command.getId(),
                command.getDuration(),
                command.getBeginning(),
                command.getDevelopment(),
                command.getConclusion(),
                command.getCreatedAt()
        );
        apply(event);
    }

    @CommandHandler
    public void handle(EditLearningSession command) {
        LearningSessionEdited event = new LearningSessionEdited(
                command.getId(),
                command.getDuration(),
                command.getBeginning(),
                command.getDevelopment(),
                command.getConclusion(),
                command.getUpdatedAt()
        );
        apply(event);
    }

    @EventSourcingHandler
    protected void on(LearningSessionRegistered event) {
        id = event.getId();
        duration = event.getDuration();
        beginning = event.getBeginning();
        development = event.getDevelopment();
        conclusion = event.getConclusion();
        status = LearningSessionStatus.ACTIVE;
    }

    @EventSourcingHandler
    protected void on(LearningSessionEdited event) {
        duration = event.getDuration();
        beginning = event.getBeginning();
        development = event.getDevelopment();
        conclusion = event.getConclusion();
    }
}
