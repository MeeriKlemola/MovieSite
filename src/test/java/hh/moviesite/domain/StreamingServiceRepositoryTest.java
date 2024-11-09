package hh.moviesite.domain;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class StreamingServiceRepositoryTest {

    @Autowired
    private StreamingServiceRepository repository;

    @Test // search functionality test
    public void findByServiceNameShouldReturnServiceName() {
        List<StreamingService> streamingServices = repository.findByServiceName("Netflix");

        assertThat(streamingServices).hasSize(1);
        assertThat(streamingServices.get(0).getId()).isEqualTo(2);
    }

    @Test // create functionality test
    public void createNewStreamingService() {
        StreamingService streamingService = new StreamingService();
        repository.save(streamingService);
        assertThat(streamingService.getId()).isNotNull();
    }

    @Test // delete functionality test
    public void deleteStreamingService() {
        StreamingService streamingService = new StreamingService();
        repository.save(streamingService);
        repository.deleteById(streamingService.getId());
        assertThat(repository.findById(streamingService.getId())).isEmpty();
    }

}
