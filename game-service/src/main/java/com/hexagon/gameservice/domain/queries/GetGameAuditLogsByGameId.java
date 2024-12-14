package com.hexagon.gameservice.domain.queries;

import lombok.Value;

@Value
public class GetGameAuditLogsByGameId {
    private final Long id;

    public GetGameAuditLogsByGameId(Long id) {
        this.id = id;
    }
}
