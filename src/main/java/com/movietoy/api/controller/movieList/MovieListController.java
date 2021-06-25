package com.movietoy.api.controller.movieList;

import com.movietoy.api.domain.dailyBoxOffice.DailyMovie;
import com.movietoy.api.domain.movieList.MovieList;
import com.movietoy.api.service.dailyBoxOffice.DailyBoxOfficeService;
import com.movietoy.api.service.movieList.MovieListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieListController {

    @Autowired
    public MovieListService movieListService;

    @GetMapping(value = "/movie/list")
    public ResponseEntity<List<MovieList>> MovieList(){

        //일간 박스오피스 리스트
        List<MovieList> movieList = movieListService.MovieList();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping(value = "/movie/paging")
    public ResponseEntity<List<MovieList>> PagingMovieList(@RequestParam(defaultValue = "0") int page){

        List<MovieList> movieList = movieListService.PagingMovieList(page);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

}
