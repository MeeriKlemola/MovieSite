package hh.moviesite.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title, director, description, review;
    private Integer publicationYear, rating;

    @ManyToOne // many movies to one category
    @JsonIgnoreProperties("movies")
    @JoinColumn(name = "categoryid") // Foreign key of Movie table

    private Category category;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(name = "movie_streaming_service", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "streaming_service_id"))
    @JsonIgnoreProperties("movies")
    private Set<StreamingService> streamingServices = new HashSet<>();

    public Movie() {
    }

    public Movie(String title, String director, String description, String review, Integer publicationYear, Integer rating,
            Category category, Set<StreamingService> streamingServices) {
        this.title = title;
        this.director = director;
        this.description = description;
        this.review = review;
        this.publicationYear = publicationYear;
        this.rating = rating;
        this.category = category;
        this.streamingServices = streamingServices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<StreamingService> getStreamingServices() {
        return streamingServices;
    }

    public void setStreamingServices(Set<StreamingService> streamingServices) {
        this.streamingServices = streamingServices;
    }

    @Override
    public String toString() {
        return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", description=" + description
                + ", review=" + review + ", publicationYear=" + publicationYear + ", rating=" + rating + ", category="
                + category + "]";
    }



}
