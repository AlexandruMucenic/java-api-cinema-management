package cinema.core.service.impl;

import cinema.core.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cinema.core.model.Actor;
import cinema.core.repository.ActorRepository;
import cinema.core.service.ActorService;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {
    private static final Logger log = LoggerFactory.getLogger(ActorServiceImpl.class);

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Actor addActor(Actor actor) {
        log.trace("addActor: actor={}", actor);
        Actor result = actorRepository.save(actor);
        log.trace("addActor: result={}", result);
        return result;
    }

    @Override
    @Transactional
    public void deleteActor(Long id) {
        log.trace("deleteActor: id={}", id);
        actorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Actor with ID " + id + " not found"));

        movieRepository.deleteActorAssociations(id);
        actorRepository.deleteById(id);
        log.trace("deleteActor: done");
    }

    @Override
    public Actor updateActor(Long id, Actor actor) {
        log.trace("updateActor: actor={}", actor);

        Actor result = actorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Actor with ID " + id + " not found"));

        result.setName(actor.getName());
        result.setAge(actor.getAge());
        result.setNationality(actor.getNationality());
        result.setAwardsCount(actor.getAwardsCount());
        result.setSsn(actor.getSsn());

        result = actorRepository.save(result);
        log.trace("updateActor: result={}", result);
        return result;
    }

    @Override
    public List<Actor> getAllActors() {
        log.trace("getAllActors --- method entered");
        List<Actor> result = actorRepository.findAll();
        log.trace("getAllActors: result={}", result);
        return result;
    }

    @Override
    public Actor getActorById(Long id) {
        log.trace("getActorById: id={}", id);
        return actorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Actor with ID " + id + " not found"));
    }
}
