package cinema.web.converter;

import org.springframework.stereotype.Component;
import cinema.core.model.Actor;
import cinema.web.dto.ActorDTO;


@Component
public class ActorConverter extends BaseConverter<Actor, ActorDTO> {
    @Override
    public Actor convertDtoToModel(ActorDTO dto) {
        Actor actor = Actor.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .nationality(dto.getNationality())
                .awardsCount(dto.getAwardsCount())
                .ssn(dto.getSsn())
                .build();

        actor.setId(dto.getId());
        return actor;
    }

    @Override
    public ActorDTO convertModelToDto(Actor actor) {
        ActorDTO dto = ActorDTO.builder()
                .name(actor.getName())
                .age(actor.getAge())
                .nationality(actor.getNationality())
                .awardsCount(actor.getAwardsCount())
                .ssn(actor.getSsn())
                .build();
        dto.setId(actor.getId());
        return dto;
    }
}
