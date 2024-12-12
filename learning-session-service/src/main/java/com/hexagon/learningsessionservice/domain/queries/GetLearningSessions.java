package com.hexagon.learningsessionservice.domain.queries;

import lombok.Value;

@Value
public class GetLearningSessions {
    private final Integer page;
    private final Integer limit;

    public GetLearningSessions(Integer page, Integer limit) {
        page = page <= 0 ? 1 : page;
        limit = (limit <= 0 || limit > 100) ? 100 : limit;
        this.page = page;
        this.limit = limit;
    }
}
