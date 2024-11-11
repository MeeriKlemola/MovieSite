package hh.moviesite.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository repository;

    @Test // search functionality
    public void findByUsernameShouldReturnUser() {  
        AppUser foundUser = repository.findByUsername("admin");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(1);
    }

    @Test // create functionality test
    public void createNewUser() {
        AppUser testUser = new AppUser("testUser", "test", "USER");
        repository.save(testUser);
        assertThat(testUser.getId()).isNotNull();
    }

    @Test // delete functionality test
    public void deleteUser() {
        AppUser testUser = new AppUser("testUser", "test", "USER");
        repository.save(testUser);
        repository.deleteById(testUser.getId());
        assertThat(repository.findById(testUser.getId())).isEmpty();
    }

}