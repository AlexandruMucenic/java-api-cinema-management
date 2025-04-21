package cinema.web.dto;

import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReservationsDTO {
    private List<ReservationDTO> reservations;
}