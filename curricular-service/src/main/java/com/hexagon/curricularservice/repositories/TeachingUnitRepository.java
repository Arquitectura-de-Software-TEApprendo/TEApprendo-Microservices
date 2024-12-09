package com.hexagon.curricularservice.repositories;

import com.hexagon.curricularservice.domain.projections.TeachingUnitProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachingUnitRepository extends JpaRepository<TeachingUnitProjection, Long> {
    @Query(value = "SELECT * FROM get_teaching_units(:pageNumber, :pageSize)", nativeQuery = true)
    List<TeachingUnitProjection> getPaginated(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);

    @Query(value = "SELECT * FROM teaching_unit_projections WHERE id = :id", nativeQuery = true)
    TeachingUnitProjection getById(Long id);
}
