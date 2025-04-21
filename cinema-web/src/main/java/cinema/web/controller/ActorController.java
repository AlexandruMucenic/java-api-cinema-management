package cinema.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cinema.core.model.Actor;
import cinema.core.service.ActorService;
import cinema.web.converter.ActorConverter;
import cinema.web.dto.ActorDTO;
import cinema.web.dto.ActorsDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/actors")

public class ActorController {
    private static final Logger log = LoggerFactory.getLogger(ActorController.class);

    @Autowired
    private ActorService actorService;

    @Autowired
    private ActorConverter actorConverter;

    @GetMapping()
    public ActorsDTO getActors() {
        log.trace("getActors - method entered");
        List<Actor> allActors = actorService.getAllActors();
        List<ActorDTO> actorDTOS = actorConverter.convertModelsToDtos(allActors);
        ActorsDTO result = new ActorsDTO(actorDTOS);
        log.trace("getActors: result={}", result);
        return result;
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> saveActor(@RequestBody ActorDTO dto) {
        log.trace("saveActor - dto={}", dto);

        for (Actor actor : actorService.getAllActors()) {
            if (dto.getSsn().equals(actor.getSsn())) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "SSN is not unique");
                return ResponseEntity.badRequest().body(error);
            }
        }

        Actor saved = actorService.addActor(actorConverter.convertDtoToModel(dto));
        log.trace("saveActor: saved={}", saved);

        Map<String, String> resp = new HashMap<>();
        resp.put("message", "Actor saved successfully");
        resp.put("id", saved.getId().toString());
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDTO> getActorById(@PathVariable("id") Long id) {
        log.trace("getActorById - id={}", id);
        Actor actor = actorService.getActorById(id);
        ActorDTO dto = actorConverter.convertModelToDto(actor);
        log.trace("getActorById: result={}", dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorDTO> updateActor(
            @PathVariable("id") Long id,
            @RequestBody ActorDTO dto) {

        log.trace("updateActor - id={}, dto={}", id, dto);
        Actor toUpdate = actorConverter.convertDtoToModel(dto);
        Actor updated = actorService.updateActor(id, toUpdate);
        ActorDTO resultDto = actorConverter.convertModelToDto(updated);
        log.trace("updateActor: result={}", resultDto);
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable("id") Long id) {
        log.trace("deleteActor - id={}", id);
        actorService.deleteActor(id);
        log.trace("deleteActor: done");
        return ResponseEntity.noContent().build();
    }
}
