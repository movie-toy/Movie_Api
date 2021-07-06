package com.movietoy.api.controller.movieInfo;

import com.movietoy.api.domain.movieInfo.MovieInfo;
import com.movietoy.api.domain.movieList.MovieList;
import com.movietoy.api.dto.movieInfo.MovieInfoResponseDto;
import com.movietoy.api.service.movieInfo.MovieInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.annotation.ExceptionProxy;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MovieInfoController {

    private final MovieInfoService movieInfoService;

    @GetMapping("/movie/{movieCd}")
    public ResponseEntity<MovieInfoResponseDto> MovieInfo(@PathVariable(value="movieCd") String movieCd) throws Exception {

        //영화 상세 정보
        MovieInfoResponseDto movieInfo = movieInfoService.MovieInfo(movieCd);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        return new ResponseEntity<MovieInfoResponseDto>(movieInfo, HttpStatus.OK);

    }
}
