package hh.moviesite.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test // search functionality test
    public void findByCategoryNameShouldReturnCategory() {
        List<Category> categories = repository.findByCategoryName("Horror");

        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getCategoryId()).isEqualTo(1);
    }

    @Test // create functionality test
    public void createNewCategory() {
        Category category = new Category("TestCategory");
        repository.save(category);
        assertThat(category.getCategoryId()).isNotNull();
    }

    @Test // delete functionality test
    public void deleteCategory() {
        Category category = new Category("TestCategory");
        repository.save(category);
        repository.deleteById(category.getCategoryId());
        assertThat(repository.findById(category.getCategoryId())).isEmpty();
    }

}