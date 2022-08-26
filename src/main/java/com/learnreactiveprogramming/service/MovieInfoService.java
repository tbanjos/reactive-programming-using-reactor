package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.MovieInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

import static com.learnreactiveprogramming.util.CommonUtil.delay;

public class MovieInfoService {

    List<MovieInfo> movieInfoList = List.of(new MovieInfo(100L, "Batman Begins", 2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15")),
            new MovieInfo(101L,"The Dark Knight", 2008, List.of("Christian Bale", "HeathLedger"), LocalDate.parse("2008-07-18")),
            new MovieInfo(102L,"Dark Knight Rises", 2008, List.of("Christian Bale", "Tom Hardy"), LocalDate.parse("2012-07-20")));

    MovieInfo movie = new MovieInfo(1L, "Batman Begins", 2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15"));

    public  Flux<MovieInfo> retrieveMoviesFlux(){
        return Flux.fromIterable(movieInfoList);
    }

    public  Mono<MovieInfo> retrieveMovieInfoMonoUsingId(long movieId){
        movie.setMovieInfoId(movieId);
        return Mono.just(movie);
    }

    public List<MovieInfo> getMovieInfoList(){
        delay(1000);
        return movieInfoList;
    }

    public  MovieInfo retrieveMovieUsingId(long movieId){
        delay(1000);
        movie.setMovieInfoId(movieId);
        return movie;
    }

}