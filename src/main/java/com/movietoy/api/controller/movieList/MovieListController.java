package com.movietoy.api.controller.movieList;

import com.movietoy.api.dto.Message;
import com.movietoy.api.dto.StatusEnum;
import com.movietoy.api.dto.movieList.MovieListResponseDto;
import com.movietoy.api.service.movieList.MovieListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieListController {

    @Autowired
    public MovieListService movieListService;

    @GetMapping(value = "/movie/list")
    public ResponseEntity<Message> MovieList() throws Exception{

        //영화 리스트
        List<MovieListResponseDto> movieList = movieListService.MovieList();

        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공");
        message.setData(movieList);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        return new ResponseEntity<>(message, httpHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "/movie/paging")
    public ResponseEntity<Message> PagingMovieList(@RequestParam(defaultValue = "0") int page) throws Exception{

        List<MovieListResponseDto> movieList = movieListService.PagingMovieList(page);

        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공");
        message.setData(movieList);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        return new ResponseEntity<>(message, httpHeaders, HttpStatus.OK);
    }

}
