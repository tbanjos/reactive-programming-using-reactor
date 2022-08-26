package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {

    MovieService movieService = new MovieService(new MovieInfoService(), new ReviewService(), new RevenueService());

    @Test
    void getMovies() {
        StepVerifier.create(movieService.getMovies())
                .assertNext(movie -> {
                    assertEquals("Batman Begins", movie.getMovie().getName());
                    assertEquals(2, movie.getReviewList().size());
                    assertEquals(5000000, movie.getRevenue().getBoxOffice());
                })
                .assertNext(movie -> {
                    assertEquals("The Dark Knight", movie.getMovie().getName());
                    assertEquals(2, movie.getReviewList().size());
                    assertEquals(5000000, movie.getRevenue().getBoxOffice());
                })
                .assertNext(movie -> {
                    assertEquals("Dark Knight Rises", movie.getMovie().getName());
                    assertEquals(2, movie.getReviewList().size());
                    assertEquals(5000000, movie.getRevenue().getBoxOffice());
                })
                .verifyComplete();
    }

    @Test
    void getMovie(){
        StepVerifier.create(movieService.getMovieById(1L))
                .assertNext(movie -> {
                    assertEquals("Batman Begins", movie.getMovie().getName());
                    assertEquals(2, movie.getReviewList().size());
                    assertEquals(5000000, movie.getRevenue().getBoxOffice());
                })
                .verifyComplete();
    }
}