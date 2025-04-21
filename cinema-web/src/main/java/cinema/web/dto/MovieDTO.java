package cinema.web.dto;

import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MovieDTO extends BaseDto {
    private String title;
    private Integer releaseYear;
    private List<Long> actorIds;
}