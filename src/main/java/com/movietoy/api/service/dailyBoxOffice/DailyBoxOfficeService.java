package com.movietoy.api.service.dailyBoxOffice;

import com.movietoy.api.domain.dailyBoxOffice.DailyMovie;
import com.movietoy.api.domain.dailyBoxOffice.DailyMovieRepository;
import com.movietoy.api.domain.weeklyBoxOffice.WeeklyMovie;
import com.movietoy.api.domain.weeklyBoxOffice.WeeklyMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyBoxOfficeService {

    private final DailyMovieRepository dailyMovieRepository;

    //일간 박스오피스 리스트
    @Cacheable(value = "dailyMovieList", cacheManager = "dailyMovieCacheManager")
    public List<DailyMovie> DailyBoxOfficeList(){
        //어제 일자 구해오기
        LocalDateTime time = LocalDateTime.now().minusDays(1);
        String targetDt =  time.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        List<DailyMovie> dailyBoxOfficeList = dailyMovieRepository.findAllByTargetDt(targetDt);
        return dailyBoxOfficeList;
    }

    //일간 박스오피스 페이징
    @Cacheable(value = "dailyMoviePaging", cacheManager = "dailyMovieCacheManager")
    public Page<DailyMovie> PagingDailyBoxOfficeList(int page)
    {
        //어제 일자 구해오기
        LocalDateTime time = LocalDateTime.now().minusDays(1);
        String targetDt =  time.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Page<DailyMovie> pagingDailyBoxOfficeList = dailyMovieRepository.findAllByTargetDt(targetDt, PageRequest.of(page,5, Sort.Direction.ASC,"id"));
        return pagingDailyBoxOfficeList;
    }
}
