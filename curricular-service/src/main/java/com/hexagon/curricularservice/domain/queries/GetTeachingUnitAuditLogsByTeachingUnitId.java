package com.hexagon.curricularservice.domain.queries;

import lombok.Value;

@Value
public class GetTeachingUnitAuditLogsByTeachingUnitId {
    private final Long id;

    public GetTeachingUnitAuditLogsByTeachingUnitId(Long id) {
        this.id = id;
    }
}
