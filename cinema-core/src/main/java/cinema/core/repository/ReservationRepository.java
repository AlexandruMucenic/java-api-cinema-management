package cinema.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cinema.core.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
