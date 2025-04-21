package cinema.core.service;

import cinema.core.model.Actor;

import java.util.List;

public interface ActorService {
    Actor addActor(Actor actor);

    void deleteActor(Long id);

    Actor updateActor(Long id, Actor actor);

    List<Actor> getAllActors();

    Actor getActorById(Long id);
}
