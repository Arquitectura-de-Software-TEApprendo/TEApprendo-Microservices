package com.hexagon.curricularservice.repositories;

import com.hexagon.curricularservice.domain.projections.TeachingUnitAuditLogProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeachingUnitAuditLogRepository extends JpaRepository<TeachingUnitAuditLogProjection, Long> {
    @Query(value = "SELECT * FROM teaching_unit_audit_log_projections WHERE teaching_unit_id = :teachingUnitId ORDER BY created_at DESC LIMIT 1", nativeQuery = true)
    Optional<TeachingUnitAuditLogProjection> getLastByTeachingUnitId(@Param("teachingUnitId") Long teachingUnitId);

    @Query(value = "SELECT * FROM teaching_unit_audit_log_projections WHERE teaching_unit_id = :teachingUnitId ORDER BY created_at", nativeQuery = true)
    List<TeachingUnitAuditLogProjection> getByTeachingUnitId(Long teachingUnitId);
}
