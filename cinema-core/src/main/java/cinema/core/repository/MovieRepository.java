package cinema.core.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import cinema.core.model.Movie;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT DISTINCT m FROM Movie m LEFT JOIN FETCH m.actors")
    List<Movie> findAllWithActors();

    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.actors WHERE m.id = :id")
    Optional<Movie> findByIdWithActors(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM movie_actors WHERE actor_id = :actorId", nativeQuery = true)
    void deleteActorAssociations(@Param("actorId") Long actorId);
}