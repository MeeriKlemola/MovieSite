package hh.moviesite.domain;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository repository;

    @Test // search functionality test
    public void findByTitleShouldReturnTitle() {
        List<Movie> movies = repository.findByTitle("Transformers One");

        assertThat(movies).hasSize(1);
        assertThat(movies.get(0).getId()).isEqualTo(1);
    }

    @Test // create functionality test
    public void createNewMovie() {
        Movie movie = new Movie();
        repository.save(movie);
        assertThat(movie.getId()).isNotNull();
    }

    @Test // delete functionality test
    public void deleteMovie() {
        Movie movie = new Movie();
        repository.save(movie);
        repository.deleteById(movie.getId());
        assertThat(repository.findById(movie.getId())).isEmpty();
    }
}
