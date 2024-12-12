package com.hexagon.learningsessionservice.repositories;

import com.hexagon.learningsessionservice.domain.projections.LearningSessionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningSessionRepository extends JpaRepository<LearningSessionProjection, Long> {
    @Query(value = "SELECT * FROM get_learning_sessions(:pageNumber, :pageSize)", nativeQuery = true)
    List<LearningSessionProjection> getPaginated(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);

    @Query(value = "SELECT * FROM learning_session_projections WHERE id = :id", nativeQuery = true)
    LearningSessionProjection getById(Long id);
}
