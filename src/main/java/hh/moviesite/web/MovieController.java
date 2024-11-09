package hh.moviesite.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.moviesite.domain.Movie;
import hh.moviesite.domain.MovieRepository;
import hh.moviesite.domain.CategoryRepository;
import hh.moviesite.domain.StreamingServiceRepository;

@Controller
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StreamingServiceRepository streamingServiceRepository;

    @GetMapping("/index")
    public String Welcome() {
        return "index"; // index.html
    }

    // http://localhost:8080/movielist
    @GetMapping(value = "/movielist")
    public String getMovieList(Model model) {
        model.addAttribute("movies", movieRepository.findAll());

        return "movielist"; // movielist.html
    }

    // http://localhost:8080/addmovie
    @GetMapping(value = "/addmovie")
    public String addMovie(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("streamingservices", streamingServiceRepository.findAll());

        return "addmovie"; // addmovie.html
    }

    // tallentaa lomakkeen tiedot sekä lisäyksessä, että päivittäessä
    @PostMapping("/save")
    public String saveMovie(@ModelAttribute Movie movie) {
        movieRepository.save(movie);

        return "redirect:/movielist";
    }

    // lue lisää elokuvasta
    @GetMapping("/review/{id}")
    public String review(@PathVariable("id") Long movieId, Model model) {
        Optional<Movie> movieOpt = movieRepository.findById(movieId);
        if (movieOpt.isPresent()) {
            Movie movie = movieOpt.get();
            System.out.println("Retrieved movie: " + movie.getTitle());
            model.addAttribute("movie", movie);
        } else {
            System.out.println("Movie not found for ID: " + movieId);
            return "redirect:/error";
        }

        return "review"; // review.html
    }

    // poistaa id:llä kirjan
    // @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteMovie(@PathVariable("id") Long movieId, Model model) {
        movieRepository.deleteById(movieId);

        return "redirect:../movielist"; // movielist.html
    }

    // editoi id:llä kirjaa
    // @PreAuthorize
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long movieId, Model model) {
        model.addAttribute("movie", movieRepository.findById(movieId));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("streamingservices", streamingServiceRepository.findAll());

        return "editmovie"; // editmovie.html
    }

}
