package hh.moviesite.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hh.moviesite.domain.Category;
import hh.moviesite.domain.CategoryRepository;

@RestController
@RequestMapping("/api")
public class CategoryRestController {

    @Autowired
    private CategoryRepository categoryRepository;

    // http://localhost:8080/api/categories
    @GetMapping("/categories")
    public @ResponseBody List<Category> categoryListRest() {
        return (List<Category>) categoryRepository.findAll();
    }

    @GetMapping("/categories/{id}")
    public @ResponseBody Optional<Category> findCategoryRest(@PathVariable("id") Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

}
