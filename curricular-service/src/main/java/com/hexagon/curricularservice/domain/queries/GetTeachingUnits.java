package com.hexagon.curricularservice.domain.queries;

import lombok.Value;

@Value
public class GetTeachingUnits {
    private final Integer page;
    private final Integer limit;

    public GetTeachingUnits(Integer page, Integer limit) {
        page = page <= 0 ? 1 : page;
        limit = (limit <= 0 || limit > 100) ? 100 : limit;
        this.page = page;
        this.limit = limit;
    }
}
