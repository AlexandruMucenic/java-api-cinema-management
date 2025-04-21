package cinema.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cinema.core.model.Reservation;
import cinema.core.service.ReservationService;
import cinema.web.converter.ReservationConverter;
import cinema.web.dto.ReservationDTO;
import cinema.web.dto.ReservationsDTO;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private static final Logger log = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationConverter reservationConverter;

    @GetMapping
    public ReservationsDTO getReservations() {
        log.trace("getReservations");
        List<Reservation> list = reservationService.getAllReservations();
        List<ReservationDTO> dtos = list.stream()
                .map(reservationConverter::convertModelToDto)
                .collect(Collectors.toList());
        return new ReservationsDTO(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable("id") Long id) {
        log.trace("getReservationById id={}", id);
        Reservation reservation = reservationService.getReservationById(id);
        ReservationDTO dto = reservationConverter.convertModelToDto(reservation);
        log.trace("getReservationById result={}", dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> saveReservation(@RequestBody ReservationDTO dto) {
        log.trace("saveReservation dto={}", dto);
        Reservation created = reservationService.addReservation(
                reservationConverter.convertDtoToModel(dto));
        ReservationDTO result = reservationConverter.convertModelToDto(created);
        Map<String, String> resp = new HashMap<>();
        resp.put("message", "Reservation created successfully");
        resp.put("id", result.getId().toString());
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(
            @PathVariable("id") Long id,
            @RequestBody ReservationDTO dto) {

        log.trace("updateReservation id={}, dto={}", id, dto);
        Reservation updated = reservationService.updateReservation(
                id,
                reservationConverter.convertDtoToModel(dto));
        ReservationDTO result = reservationConverter.convertModelToDto(updated);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id) {
        log.trace("deleteReservation id={}", id);
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}