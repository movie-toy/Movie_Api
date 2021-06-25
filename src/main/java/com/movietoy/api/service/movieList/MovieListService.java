package com.movietoy.api.service.movieList;

import com.movietoy.api.domain.dailyBoxOffice.DailyMovie;
import com.movietoy.api.domain.movieList.MovieList;
import com.movietoy.api.domain.movieList.MovieListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieListService {

    private final MovieListRepository movieListRepository;

    //일간 박스오피스 리스트
    @Cacheable("MovieList")
    @Transactional(readOnly = true)
    public List<MovieList> MovieList(){
        List<MovieList> movieList = movieListRepository.findAll();

        return movieList;
    }

    //일간 박스오피스 페이징
    @Cacheable("PagingMovieList")
    @Transactional(readOnly = true)
    public List<MovieList> PagingMovieList(int page)
    {
        //Paging 객체 가져오기
        Page<MovieList> pagingMovieList = movieListRepository.findAll(PageRequest.of(page,20, Sort.Direction.ASC,"id"));
        //페이징 처리된 Data만 리스트로 넘겨주기
        List<MovieList> movieList = pagingMovieList.getContent();

        return movieList;
    }



}
