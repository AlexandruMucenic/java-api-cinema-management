package cinema.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cinema.core.model.Reservation;
import cinema.core.model.Movie;
import cinema.web.dto.ReservationDTO;
import cinema.core.service.MovieService;

@Component
public class ReservationConverter extends BaseConverter<Reservation, ReservationDTO> {

    @Autowired
    private MovieService movieService;

    @Override
    public Reservation convertDtoToModel(ReservationDTO dto) {
        Reservation reservation = Reservation.builder()
                .customerName(dto.getCustomerName())
                .price(dto.getPrice())
                .date(dto.getDate())
                .hour(dto.getHour())
                .build();
        reservation.setId(dto.getId());
        Movie movie = movieService.getMovieById(dto.getMovieId());
        reservation.setMovie(movie);
        return reservation;
    }

    @Override
    public ReservationDTO convertModelToDto(Reservation reservation) {
        ReservationDTO dto = ReservationDTO.builder()
                .customerName(reservation.getCustomerName())
                .price(reservation.getPrice())
                .date(reservation.getDate())
                .hour(reservation.getHour())
                .movieId(reservation.getMovie().getId())
                .build();
        dto.setId(reservation.getId());
        return dto;
    }
}