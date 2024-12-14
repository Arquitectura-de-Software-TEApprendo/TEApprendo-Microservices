package com.hexagon.gameservice.domain.model.aggregates;

import com.hexagon.gameservice.domain.commands.EditGame;
import com.hexagon.gameservice.domain.commands.RegisterGame;
import com.hexagon.gameservice.domain.events.GameEdited;
import com.hexagon.gameservice.domain.events.GameRegistered;
import com.hexagon.gameservice.domain.model.valueobjects.GameStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Game {

    @AggregateIdentifier
    private Long id;
    private String name;
    private String type;
    private String difficulty;
    private String topic;
    private GameStatus status;

    public Game() {
    }

    @CommandHandler
    public Game(RegisterGame command) {
        GameRegistered event = new GameRegistered(
                command.getId(),
                command.getName(),
                command.getType(),
                command.getDifficulty(),
                command.getTopic(),
                command.getCreatedAt()
        );
        apply(event);
    }

    @CommandHandler
    public void handle(EditGame command) {
        GameEdited event = new GameEdited(
                command.getId(),
                command.getName(),
                command.getType(),
                command.getDifficulty(),
                command.getTopic(),
                command.getUpdatedAt()
        );
        apply(event);
    }

    @EventSourcingHandler
    protected void on(GameRegistered event) {
        id = event.getId();
        name = event.getName();
        type = event.getType();
        difficulty = event.getDifficulty();
        topic = event.getTopic();
        status = GameStatus.ACTIVE;
    }

    @EventSourcingHandler
    protected void on(GameEdited event) {
        name = event.getName();
        type = event.getType();
        difficulty = event.getDifficulty();
        topic = event.getTopic();
    }
}
