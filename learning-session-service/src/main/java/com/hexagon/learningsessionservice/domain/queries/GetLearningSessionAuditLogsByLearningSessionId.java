package com.hexagon.learningsessionservice.domain.queries;

import lombok.Value;

@Value
public class GetLearningSessionAuditLogsByLearningSessionId {
    private final Long id;

    public GetLearningSessionAuditLogsByLearningSessionId(Long id) {
        this.id = id;
    }
}
