package hh.moviesite;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hh.moviesite.domain.Category;
import hh.moviesite.domain.CategoryRepository;
import hh.moviesite.domain.Movie;
import hh.moviesite.domain.MovieRepository;
import hh.moviesite.domain.StreamingService;
import hh.moviesite.domain.StreamingServiceRepository;

@SpringBootApplication
public class MoviesiteApplication {
	private static final Logger log = LoggerFactory.getLogger(MoviesiteApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MoviesiteApplication.class, args);
	}

	@Bean
	public CommandLineRunner fetchData(CategoryRepository categoryRepository, MovieRepository movieRepository, StreamingServiceRepository streamingServiceRepository) {
		return (args) -> {
			Category action = new Category("Action");
			categoryRepository.save(action);
			Category drama = new Category("Drama");
			categoryRepository.save(drama);
			Category scifi = new Category("Scifi");
			categoryRepository.save(scifi);
			Category war = new Category("War");
			categoryRepository.save(war);
			Category childrensmovie = new Category("Children's movie");
			categoryRepository.save(childrensmovie);
			Category mystery = new Category("Mystery");
			categoryRepository.save(mystery);
			Category horror = new Category("Horror");
			categoryRepository.save(horror);
			Category fantasy = new Category("Fantasy");
			categoryRepository.save(fantasy);

			StreamingService none = new StreamingService("-");
			streamingServiceRepository.save(none);
			StreamingService netflix = new StreamingService("Netflix");
			streamingServiceRepository.save(netflix);
			StreamingService max = new StreamingService("MAX");
			streamingServiceRepository.save(max);
			StreamingService disney = new StreamingService("Disney +");
			streamingServiceRepository.save(disney);
			StreamingService primevideo = new StreamingService("Prime Video");
			streamingServiceRepository.save(primevideo);
			StreamingService skyshowtime = new StreamingService("SkyShowTime");
			streamingServiceRepository.save(skyshowtime);

			log.info("save a couple of movies");
			movieRepository.save(new Movie("Transformers One", "Josh Cooley", "Transformers origin story. Fantastic childrens movie that is supprisingly good for adults too.", 
			2024, 90, childrensmovie, new HashSet<>(Set.of(none))));
            movieRepository.save(new Movie("Whiplash", "Damien Chazelle", "Motivating and realistic ", 
			2014, 85, drama, new HashSet<>(Set.of(none))));
            movieRepository.save(new Movie("Godzilla Minus One", "Takashi Yamazaki", "Fantastic Godzilla origin story. Set in postwar Japan", 
			2023, 95, war, new HashSet<>(Set.of(netflix))));

            movieRepository.save(new Movie("Fight Club", "David Fincher", "Truly captivating movie that you just have to see twice to really soak in all the nyances.", 
			1999, 100, action, new HashSet<>(Set.of(disney))));
            movieRepository.save(new Movie("Dark Knight", "Christopher Nolan", "This is THE batman movie.", 
			2008, 80, action, new HashSet<>(Set.of(netflix))));
            movieRepository.save(new Movie("Rogue one", "Gareth Edwards", "", 
			2016, 90, scifi, new HashSet<>(Set.of(disney))));

			movieRepository.save(new Movie("Interstellar", "Christopher Nolan", "", 
			2014, 90, scifi, new HashSet<>(Set.of(max, primevideo))));
			movieRepository.save(new Movie("Shutter Island", "Martin Scorsese", "", 
			2010, 85, mystery, new HashSet<>(Set.of(skyshowtime))));
			movieRepository.save(new Movie("Black Swan", "Darren Aronofsky", "", 
			2010, 85, horror, new HashSet<>(Set.of(disney))));
			
			movieRepository.save(new Movie("Top gun", "Tony Scott", "", 
			1986, 95, action, new HashSet<>(Set.of(netflix, skyshowtime))));
			movieRepository.save(new Movie("Fantastic beasts and where to find them", "David Yates", "", 
			2016, 80, fantasy, new HashSet<>(Set.of(max))));
			movieRepository.save(new Movie("Watchmen", "Zack Snyder", "", 
			2009, 90, scifi, new HashSet<>(Set.of(primevideo))));


			log.info("fetch all movies");
			for (Movie movie : movieRepository.findAll()) {
				log.info(movie.toString());
			}

		};
	}

}
