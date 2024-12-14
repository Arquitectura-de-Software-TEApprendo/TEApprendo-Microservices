package com.hexagon.gameservice.application.queryhandlers;

import com.hexagon.gameservice.domain.projections.GameAuditLogProjection;
import com.hexagon.gameservice.domain.projections.GameProjection;
import com.hexagon.gameservice.domain.queries.GetGameAuditLogsByGameId;
import com.hexagon.gameservice.domain.queries.GetGames;
import com.hexagon.gameservice.repositories.GameAuditLogRepository;
import com.hexagon.gameservice.repositories.GameRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameAggregateQueryHandler {
    private final GameRepository gameRepository;
    private final GameAuditLogRepository gameAuditRepository;


    public GameAggregateQueryHandler(GameRepository gameRepository, GameAuditLogRepository gameAuditRepository) {
        this.gameRepository = gameRepository;
        this.gameAuditRepository = gameAuditRepository;
    }

    @QueryHandler
    public List<GameAuditLogProjection> handle(GetGameAuditLogsByGameId query) {
        return this.gameAuditRepository.getByGameId(query.getId());
    }

    @QueryHandler
    public List<GameProjection> handle(GetGames query) {
        return this.gameRepository.getPaginated(query.getPage(), query.getLimit());
    }
}
