package com.hexagon.gameservice.interfaces.eventhandlers;

import com.hexagon.gameservice.domain.events.GameEdited;
import com.hexagon.gameservice.domain.events.GameRegistered;
import com.hexagon.gameservice.domain.model.valueobjects.GameStatus;
import com.hexagon.gameservice.domain.projections.GameAuditLogProjection;
import com.hexagon.gameservice.repositories.GameAuditLogRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GameAuditLogProjectionsEventHandler {
    private final GameAuditLogRepository gameAuditRepository;

    public GameAuditLogProjectionsEventHandler(GameAuditLogRepository gameAuditRepository) {
        this.gameAuditRepository = gameAuditRepository;
    }

    @EventHandler
    public void on(GameRegistered event) {
        GameAuditLogProjection view = new GameAuditLogProjection(
                event.getId(),
                event.getName(),
                event.getType(),
                event.getDifficulty(),
                event.getTopic(),
                GameStatus.ACTIVE.name(),
                event.getCreatedAt());
        gameAuditRepository.save(view);
    }

    @EventHandler
    public void on(GameEdited event) {
        Optional<GameAuditLogProjection> viewOptional = gameAuditRepository.getLastByGameId(event.getId());
        if (viewOptional.isPresent()) {
            GameAuditLogProjection lastGameAudit = viewOptional.get();
            GameAuditLogProjection gameAudit = new GameAuditLogProjection(lastGameAudit);
            gameAudit.setName(event.getName());
            gameAudit.setType(event.getType());
            gameAudit.setDifficulty(event.getDifficulty());
            gameAudit.setTopic(event.getTopic());
            gameAudit.setCreatedAt(event.getUpdatedAt());
            gameAuditRepository.save(gameAudit);
        }
    }
}
