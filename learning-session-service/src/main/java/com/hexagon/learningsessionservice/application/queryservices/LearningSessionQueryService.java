package com.hexagon.learningsessionservice.application.queryservices;

import com.hexagon.learningsessionservice.domain.projections.LearningSessionAuditLogProjection;
import com.hexagon.learningsessionservice.domain.projections.LearningSessionProjection;
import com.hexagon.learningsessionservice.domain.queries.GetLearningSessionAuditLogsByLearningSessionId;
import com.hexagon.learningsessionservice.domain.queries.GetLearningSessions;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LearningSessionQueryService {
    private final QueryGateway queryGateway;

    public LearningSessionQueryService(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    public List<LearningSessionAuditLogProjection> getAuditLogsByLearningSessionId(Long learningSessionId) throws Exception {
        var query = new GetLearningSessionAuditLogsByLearningSessionId(learningSessionId);
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(LearningSessionAuditLogProjection.class)).join();
    }

    public List<LearningSessionProjection> getLearningSessions(Integer page, Integer limit) throws Exception {
        var query = new GetLearningSessions(page, limit);
        return queryGateway.query(query, ResponseTypes.multipleInstancesOf(LearningSessionProjection.class)).join();
    }
}
