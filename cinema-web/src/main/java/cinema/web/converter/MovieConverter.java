package cinema.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cinema.core.model.Movie;
import cinema.core.model.Actor;
import cinema.web.dto.MovieDTO;
import cinema.core.service.ActorService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieConverter extends BaseConverter<Movie, MovieDTO> {

    @Autowired
    private ActorService actorService;

    @Override
    public Movie convertDtoToModel(MovieDTO dto) {
        Movie.MovieBuilder builder = Movie.builder()
                .title(dto.getTitle())
                .releaseYear(dto.getReleaseYear());
        if (dto.getActorIds() != null) {
            List<Actor> actors = dto.getActorIds().stream()
                    .map(actorService::getActorById)
                    .toList();
            builder.actors(new java.util.HashSet<>(actors));
        }
        Movie movie = builder.build();
        movie.setId(dto.getId());
        return movie;
    }

    @Override
    public MovieDTO convertModelToDto(Movie movie) {
        MovieDTO.MovieDTOBuilder builder = MovieDTO.builder()
                .title(movie.getTitle())
                .releaseYear(movie.getReleaseYear());
        if (movie.getActors() != null) {
            List<Long> ids = movie.getActors().stream()
                    .map(Actor::getId)
                    .collect(Collectors.toList());
            builder.actorIds(ids);
        }
        MovieDTO dto = builder.build();
        dto.setId(movie.getId());
        return dto;
    }
}
