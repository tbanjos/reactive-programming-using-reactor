package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
public class MovieService {

    private MovieInfoService movieInfoService;

    private ReviewService reviewService;

    private RevenueService revenueService;

    public Flux<Movie> getMovies(){
        var movieInfoList = movieInfoService.retrieveMoviesFlux();
        return movieInfoList.map(movieInfo -> {
            var movieReviews= reviewService.retrieveReviews(movieInfo.getMovieInfoId());
            return new Movie(movieInfo, movieReviews, revenueService.getRevenue(movieInfo.getMovieInfoId()));
        }).log();
    }

    public Mono<Movie> getMovieById(long movieId){
        var movieMono = movieInfoService.retrieveMovieInfoMonoUsingId(movieId);
        var reviews = reviewService.retrieveReviews(movieId);
        return movieMono.map(movieInfo -> new Movie(movieInfo, reviews)).log();
    }
}
