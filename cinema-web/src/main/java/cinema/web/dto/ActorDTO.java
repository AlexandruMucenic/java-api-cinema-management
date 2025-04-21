package cinema.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ActorDTO extends BaseDto {
    private String ssn;
    private String name;
    private Integer age;
    private String nationality;
    private Integer awardsCount;
}