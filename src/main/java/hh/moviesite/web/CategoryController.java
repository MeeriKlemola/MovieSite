package hh.moviesite.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import hh.moviesite.domain.Category;
import hh.moviesite.domain.CategoryRepository;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // http://localhost:8080/categorylist
    @GetMapping(value = "/categorylist")
    public String getCategoryList(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());

        return "categorylist"; // categorylist.html
    }

    // http://localhost:8080/addcategory
    @GetMapping(value = "/addcategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());

        return "addcategory"; // addcategory.html
    }

    // tallentaa lomakkeen tiedot ja tallentaa uuden kategorian
    @PostMapping("/savecategory")
    public String saveCategory(@ModelAttribute Category category) {
        categoryRepository.save(category);

        return "redirect:/categorylist"; // categorylist.html
    }

}
