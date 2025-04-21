package cinema.web.dto;

import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MoviesDTO {
    private List<MovieDTO> movies;
}