package cinema.web.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ActorsDTO {
    private List<ActorDTO> actors;
}
