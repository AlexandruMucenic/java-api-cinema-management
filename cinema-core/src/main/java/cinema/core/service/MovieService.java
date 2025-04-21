package cinema.core.service;

import cinema.core.model.Movie;
import java.util.List;

public interface MovieService {
    Movie addMovie(Movie movie);

    void deleteMovie(Long id);

    Movie updateMovie(Long id, Movie movie);

    List<Movie> getAllMovies();

    Movie getMovieById(Long id);
}
