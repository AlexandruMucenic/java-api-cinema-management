package cinema.web.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ReservationDTO extends BaseDto {
    private String customerName;
    private BigDecimal price;
    private LocalDate date;
    private LocalTime hour;
    private Long movieId;
}
