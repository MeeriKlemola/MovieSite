package hh.moviesite.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class StreamingService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String serviceName;

    @ManyToMany(mappedBy = "streamingServices")
    @JsonIgnoreProperties("streamingServices")
    private Set<Movie> movies = new HashSet<>();

    public StreamingService() {
    }

    public StreamingService(String serviceName) {
        this.serviceName = serviceName;
    }

    public StreamingService(String serviceName, Set<Movie> movies) {
        this.serviceName = serviceName;
        this.movies = movies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override public String toString() { return serviceName; }

}
