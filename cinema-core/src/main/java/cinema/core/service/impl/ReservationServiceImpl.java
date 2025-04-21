package cinema.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cinema.core.model.Reservation;
import cinema.core.repository.ReservationRepository;
import cinema.core.service.ReservationService;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private static final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation addReservation(Reservation reservation) {
        log.trace("addReservation: {}", reservation);
        Reservation result = reservationRepository.save(reservation);
        log.trace("addReservation result: {}", result);
        return result;
    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {
        log.trace("updateReservation id={}, reservation={}", id, reservation);
        Reservation existing = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation with ID " + id + " not found"));
        existing.setCustomerName(reservation.getCustomerName());
        existing.setPrice(reservation.getPrice());
        existing.setDate(reservation.getDate());
        existing.setHour(reservation.getHour());
        existing.setMovie(reservation.getMovie());
        Reservation result = reservationRepository.save(existing);
        log.trace("updateReservation result: {}", result);
        return result;
    }

    @Override
    public void deleteReservation(Long id) {
        log.trace("deleteReservation id={}", id);
        reservationRepository.deleteById(id);
        log.trace("deleteReservation done for id={}", id);
    }

    @Override
    public List<Reservation> getAllReservations() {
        log.trace("getAllReservations");
        List<Reservation> results = reservationRepository.findAll();
        log.trace("getAllReservations size={}", results.size());
        return results;
    }

    @Override
    public Reservation getReservationById(Long id) {
        log.trace("getReservationById reservation id={}", id);
        return reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation with ID " + id + " not found"));
    }
}
