package com.hexagon.learningsessionservice.application.queryhandlers;

import com.hexagon.learningsessionservice.domain.projections.LearningSessionAuditLogProjection;
import com.hexagon.learningsessionservice.domain.projections.LearningSessionProjection;
import com.hexagon.learningsessionservice.domain.queries.GetLearningSessionAuditLogsByLearningSessionId;
import com.hexagon.learningsessionservice.domain.queries.GetLearningSessions;
import com.hexagon.learningsessionservice.repositories.LearningSessionAuditLogRepository;
import com.hexagon.learningsessionservice.repositories.LearningSessionRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LearningSessionAggregateQueryHandler {
    private final LearningSessionRepository learningSessionRepository;
    private final LearningSessionAuditLogRepository learningSessionAuditRepository;


    public LearningSessionAggregateQueryHandler(LearningSessionRepository learningSessionRepository, LearningSessionAuditLogRepository learningSessionAuditRepository) {
        this.learningSessionRepository = learningSessionRepository;
        this.learningSessionAuditRepository = learningSessionAuditRepository;
    }

    @QueryHandler
    public List<LearningSessionAuditLogProjection> handle(GetLearningSessionAuditLogsByLearningSessionId query) {
        return this.learningSessionAuditRepository.getByLearningSessionId(query.getId());
    }

    @QueryHandler
    public List<LearningSessionProjection> handle(GetLearningSessions query) {
        return this.learningSessionRepository.getPaginated(query.getPage(), query.getLimit());
    }
}
