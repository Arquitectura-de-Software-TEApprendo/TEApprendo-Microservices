package com.hexagon.gameservice.interfaces.eventhandlers;

import com.hexagon.gameservice.domain.events.GameEdited;
import com.hexagon.gameservice.domain.events.GameRegistered;
import com.hexagon.gameservice.domain.model.valueobjects.GameStatus;
import com.hexagon.gameservice.domain.projections.GameProjection;
import com.hexagon.gameservice.repositories.GameRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GameProjectionsEventHandler {
    private final GameRepository gameRepository;

    public GameProjectionsEventHandler(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @EventHandler
    public void on(GameRegistered event) {
        GameProjection game = new GameProjection(
                event.getId(),
                event.getName(),
                event.getType(),
                event.getDifficulty(),
                event.getTopic(),
                GameStatus.ACTIVE.name(),
                event.getCreatedAt());
        gameRepository.save(game);
    }

    @EventHandler
    public void on(GameEdited event) {
        Optional<GameProjection> viewOptional = gameRepository.findById(event.getId());
        if (viewOptional.isPresent()) {
            GameProjection view = viewOptional.get();
            view.setName(event.getName());
            view.setType(event.getType());
            view.setDifficulty(event.getDifficulty());
            view.setTopic(event.getTopic());
            view.setUpdatedAt(event.getUpdatedAt());
            gameRepository.save(view);
        }
    }
}
