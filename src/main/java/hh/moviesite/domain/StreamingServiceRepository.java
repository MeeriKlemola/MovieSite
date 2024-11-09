package hh.moviesite.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StreamingServiceRepository extends CrudRepository<StreamingService, Long> {
    List<StreamingService> findByServiceName(String name);
}