package cinema.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cinema.core.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
