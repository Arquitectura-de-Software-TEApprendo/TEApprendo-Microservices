package com.hexagon.gameservice.application.queryservices;

import com.hexagon.gameservice.domain.projections.GameAuditLogProjection;
import com.hexagon.gameservice.domain.projections.GameProjection;
import com.hexagon.gameservice.domain.queries.GetGameAuditLogsByGameId;
import com.hexagon.gameservice.domain.queries.GetGames;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameQueryService {
    private final QueryGateway queryGateway;

    public GameQueryService(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    public List<GameAuditLogProjection> getAuditLogsByGameId(Long gameId) throws Exception {
        var query = new GetGameAuditLogsByGameId(gameId);
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(GameAuditLogProjection.class)).join();
    }

    public List<GameProjection> getGames(Integer page, Integer limit) throws Exception {
        var query = new GetGames(page, limit);
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(GameProjection.class)).join();
    }
}
