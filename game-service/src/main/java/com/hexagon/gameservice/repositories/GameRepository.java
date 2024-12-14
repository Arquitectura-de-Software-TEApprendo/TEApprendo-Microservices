package com.hexagon.gameservice.repositories;

import com.hexagon.gameservice.domain.projections.GameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameProjection, Long> {
    @Query(value = "SELECT * FROM get_games(:pageNumber, :pageSize)", nativeQuery = true)
    List<GameProjection> getPaginated(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);

    @Query(value = "SELECT * FROM game_projections WHERE id = :id", nativeQuery = true)
    GameProjection getById(Long id);
}
