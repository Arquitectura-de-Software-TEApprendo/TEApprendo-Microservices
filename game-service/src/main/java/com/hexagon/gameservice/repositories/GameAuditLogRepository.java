package com.hexagon.gameservice.repositories;

import com.hexagon.gameservice.domain.projections.GameAuditLogProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameAuditLogRepository extends JpaRepository<GameAuditLogProjection, Long> {
    @Query(value = "SELECT * FROM game_audit_log_projections WHERE game_id = :gameId ORDER BY created_at DESC LIMIT 1", nativeQuery = true)
    Optional<GameAuditLogProjection> getLastByGameId(@Param("gameId") Long gameId);

    @Query(value = "SELECT * FROM game_audit_log_projections WHERE game_id = :gameId ORDER BY created_at", nativeQuery = true)
    List<GameAuditLogProjection> getByGameId(Long gameId);
}
