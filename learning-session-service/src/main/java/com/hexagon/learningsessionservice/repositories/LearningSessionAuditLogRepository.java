package com.hexagon.learningsessionservice.repositories;

import com.hexagon.learningsessionservice.domain.projections.LearningSessionAuditLogProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LearningSessionAuditLogRepository extends JpaRepository<LearningSessionAuditLogProjection, Long> {
    @Query(value = "SELECT * FROM learning_session_audit_log_projections WHERE learning_session_id = :learningSessionId ORDER BY created_at DESC LIMIT 1", nativeQuery = true)
    Optional<LearningSessionAuditLogProjection> getLastByLearningSessionId(@Param("learningSessionId") Long learningSessionId);

    @Query(value = "SELECT * FROM learning_session_audit_log_projections WHERE learning_session_id = :learningSessionId ORDER BY created_at", nativeQuery = true)
    List<LearningSessionAuditLogProjection> getByLearningSessionId(Long learningSessionId);
}
