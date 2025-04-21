package cinema.core.service;

import cinema.core.model.Reservation;
import java.util.List;

public interface ReservationService {
    Reservation addReservation(Reservation reservation);

    Reservation updateReservation(Long id, Reservation reservation);

    void deleteReservation(Long id);

    List<Reservation> getAllReservations();

    Reservation getReservationById(Long id);
}
