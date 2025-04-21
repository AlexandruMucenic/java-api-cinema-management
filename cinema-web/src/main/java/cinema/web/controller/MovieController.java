package cinema.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cinema.core.model.Movie;
import cinema.core.service.MovieService;
import cinema.web.converter.MovieConverter;
import cinema.web.dto.MovieDTO;
import cinema.web.dto.MoviesDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieConverter movieConverter;

    @GetMapping
    public MoviesDTO getMovies() {
        log.trace("getMovies - method entered");
        List<Movie> all = movieService.getAllMovies();
        List<MovieDTO> dtos = all.stream()
                .map(movieConverter::convertModelToDto)
                .collect(Collectors.toList());
        MoviesDTO result = new MoviesDTO(dtos);
        log.trace("getMovies: result={}", result);
        return result;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> saveMovie(@RequestBody MovieDTO dto) {
        log.trace("saveMovie: dto={}", dto);
        Movie saved = movieService.addMovie(
                movieConverter.convertDtoToModel(dto));
        MovieDTO resultDto = movieConverter.convertModelToDto(saved);
        Map<String, String> resp = new HashMap<>();
        resp.put("message", "Movie saved successfully");
        resp.put("id", String.valueOf(resultDto.getId()));
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable("id") Long id) {
        log.trace("getMovieById - id={}", id);
        Movie movie = movieService.getMovieById(id);
        MovieDTO dto = movieConverter.convertModelToDto(movie);
        log.trace("getMovieById: result={}", dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(
            @PathVariable("id") Long id,
            @RequestBody MovieDTO dto) {

        log.trace("updateMovie - id={}, dto={}", id, dto);
        Movie toUpdate = movieConverter.convertDtoToModel(dto);
        Movie updated = movieService.updateMovie(id, toUpdate);
        MovieDTO resultDto = movieConverter.convertModelToDto(updated);
        log.trace("updateMovie: result={}", resultDto);
        return ResponseEntity.ok(resultDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("id") Long id) {
        log.trace("deleteMovie - id={}", id);
        movieService.deleteMovie(id);
        log.trace("deleteMovie: done");
        return ResponseEntity.noContent().build();
    }
}