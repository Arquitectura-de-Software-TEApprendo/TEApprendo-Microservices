package com.hexagon.curricularservice.application.queryhandlers;

import com.hexagon.curricularservice.domain.projections.TeachingUnitAuditLogProjection;
import com.hexagon.curricularservice.domain.projections.TeachingUnitProjection;
import com.hexagon.curricularservice.domain.queries.GetTeachingUnitAuditLogsByTeachingUnitId;
import com.hexagon.curricularservice.domain.queries.GetTeachingUnits;
import com.hexagon.curricularservice.repositories.TeachingUnitAuditLogRepository;
import com.hexagon.curricularservice.repositories.TeachingUnitRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeachingUnitAggregateQueryHandler {
    private final TeachingUnitRepository teachingUnitRepository;
    private final TeachingUnitAuditLogRepository teachingUnitAuditRepository;


    public TeachingUnitAggregateQueryHandler(TeachingUnitRepository teachingUnitRepository, TeachingUnitAuditLogRepository teachingUnitAuditRepository) {
        this.teachingUnitRepository = teachingUnitRepository;
        this.teachingUnitAuditRepository = teachingUnitAuditRepository;
    }

    @QueryHandler
    public List<TeachingUnitAuditLogProjection> handle(GetTeachingUnitAuditLogsByTeachingUnitId query) {
        return this.teachingUnitAuditRepository.getByTeachingUnitId(query.getId());
    }

    @QueryHandler
    public List<TeachingUnitProjection> handle(GetTeachingUnits query) {
        return this.teachingUnitRepository.getPaginated(query.getPage(), query.getLimit());
    }
}
