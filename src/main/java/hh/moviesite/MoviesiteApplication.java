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
			movieRepository.save(new Movie("Transformers One", "Josh Cooley", "Transformers origin story. Fantastic childrens movie that is supprisingly good for adults too. Good humour, interesting story even for those that don't care about Transformers and hard hitting action.", 
			2024, 90, childrensmovie, new HashSet<>(Set.of(none))));
            movieRepository.save(new Movie("Whiplash", "Damien Chazelle", "Movie about a aspiring drummer, who is slaving himself away to succeed. Great charecters, surprising yet realistic feeling story.", 
			2014, 95, drama, new HashSet<>(Set.of(none))));
            movieRepository.save(new Movie("Godzilla Minus One", "Takashi Yamazaki", "Fantastic Godzilla origin story. Set in postwar Japan where Godzilla is being encountered for the first time. Great balance of tough expectations of soldiers, fantastic charecters and little action for the nerds. Surprisingly serious movie with very little humour.", 
			2023, 95, war, new HashSet<>(Set.of(netflix))));

            movieRepository.save(new Movie("Fight Club", "David Fincher", "Truly captivating movie that you just have to see at least twice to really soak in all the nyances. When I saw this for the first time I made three of my friends watch it with me and needless to say they all loved it.", 
			1999, 100, action, new HashSet<>(Set.of(disney))));
            movieRepository.save(new Movie("Dark Knight", "Christopher Nolan", "Joker wreaks havoc in Gotham and Batman comes to the rescue. Heath Ledger as a Joker is a match made in heaven. I have seen many Batman movies, but Christian Bale is my favourite Batman.", 
			2008, 80, action, new HashSet<>(Set.of(netflix))));
            movieRepository.save(new Movie("Rogue one", "Gareth Edwards", "Star Wars movie that fits right in the middle ot the third and fourth movie. Small group of rebels go on a tough mission to give the rebels a chance at a victory. Hard hitting action, no unnecessary fluff, great story.", 
			2016, 80, scifi, new HashSet<>(Set.of(disney))));

			movieRepository.save(new Movie("Interstellar", "Christopher Nolan", "Interstellar is set in a dystopian earth where isn't much food left and humanity is facing extinction in the near future. Astronauts go on a mission to find better planet to live. In space the time travels differently in some zones and the pilots watch through videomessages how their family grows without them. Tough watch, but a great movie with fantastic cast. The soundtrack is absolutely amazing. Fantastic music to listen while concentrating on work.", 
			2014, 90, scifi, new HashSet<>(Set.of(max, primevideo))));
			movieRepository.save(new Movie("Shutter Island", "Martin Scorsese", "Two marshals go to a hospital for the criminally insane to investigate a disappearance of a patient. The main charecter also has a ulterior motive as he believes his wifes killer resides in the hospital. This psychological horror movie truly blew me away, it makes me anxious and curious.", 
			2010, 85, horror, new HashSet<>(Set.of(skyshowtime))));
			movieRepository.save(new Movie("Black Swan", "Darren Aronofsky", "Ballet dancer goes insane trying to perfect her swan lake act. Bizarre, anxiety inducing yet beautiful movie.", 
			2010, 85, horror, new HashSet<>(Set.of(disney))));
			
			movieRepository.save(new Movie("Top gun", "Tony Scott", "Top Gun is a school for the very best fighterpilots. This movie is full of action, romance and great charecters and chemistry.", 
			1986, 95, action, new HashSet<>(Set.of(netflix, skyshowtime))));
			movieRepository.save(new Movie("Fantastic beasts and where to find them", "David Yates", "Main charecter Newt Scamander lives in the Harry Potter universe and tends magical animals. Must watch for the mytchical animal lovers!", 
			2016, 80, fantasy, new HashSet<>(Set.of(max))));
			movieRepository.save(new Movie("Watchmen", "Zack Snyder", "Set in the year 1985. Main charecter is a vigilante \"detective\" on a case. Fantastic superhero movie for those that want more grown up, but still eventful movie.", 
			2009, 90, scifi, new HashSet<>(Set.of(primevideo))));


			log.info("fetch all movies");
			for (Movie movie : movieRepository.findAll()) {
				log.info(movie.toString());
			}

		};
	}

}
