package hh.moviesite.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MoviesiteApplicationTests {

	@Autowired
	private MovieController MovieController;

	@Test
	public void contextLoadsBookController() throws Exception {
		assertThat(MovieController).isNotNull();
	}

	@Autowired
	private CategoryRestController RestController;

	@Test
	public void contextLoadsRestController() throws Exception {
		assertThat(RestController).isNotNull();
	}

	@Autowired
	private CategoryController CategoryController;

	@Test
	public void contextLoadsCategoryController() throws Exception {
		assertThat(CategoryController).isNotNull();
	}

	@Autowired
	private StreamingServiceController StreamingServiceController;

	@Test
	public void contextLoadsSServiceController() throws Exception {
		assertThat(StreamingServiceController).isNotNull();
	}

}
