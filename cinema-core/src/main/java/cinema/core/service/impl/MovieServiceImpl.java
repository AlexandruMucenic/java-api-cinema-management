
package cinema.core.service.impl;

import cinema.core.model.Movie;
import cinema.core.repository.MovieRepository;
import cinema.core.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private static final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie addMovie(Movie movie) {
        log.trace("addMovie: movie={}", movie);
        Movie result = movieRepository.save(movie);
        log.trace("addMovie: result={}", result);
        return result;
    }

    @Override
    public void deleteMovie(Long id) {
        log.trace("deleteMovie: id={}", id);
        movieRepository.deleteById(id);
        log.trace("deleteMovie: id={}", id);
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) {
        log.trace("updateMovie: id={}, movie={}", id, movie);
        Movie existing = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie with ID " + id + " not found"));

        existing.setTitle(movie.getTitle());
        existing.setReleaseYear(movie.getReleaseYear());
        existing.setActors(movie.getActors());

        Movie result = movieRepository.save(existing);
        log.trace("updateMovie: result={}", result);
        return result;
    }

    @Override
    public List<Movie> getAllMovies() {
        log.trace("getAllMovies --- method entered");
        List<Movie> result = movieRepository.findAllWithActors();
        log.trace("getAllMovies: result={}", result);
        return result;
    }

    @Override
    public Movie getMovieById(Long id) {
        log.trace("findById: id={}", id);
        return movieRepository.findByIdWithActors(id)
                .orElseThrow(() -> new IllegalArgumentException("Movie with ID " + id + " not found"));
    }
}
