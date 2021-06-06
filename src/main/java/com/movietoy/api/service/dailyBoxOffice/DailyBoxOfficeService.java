package com.movietoy.api.service.dailyBoxOffice;

import com.movietoy.api.domain.dailyBoxOffice.DailyMovie;
import com.movietoy.api.domain.dailyBoxOffice.DailyMovieRepository;
import com.movietoy.api.domain.weeklyBoxOffice.WeeklyMovie;
import com.movietoy.api.domain.weeklyBoxOffice.WeeklyMovieRepository;
import lombok.RequiredArgsConstructor;
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

    //모든 일간 박스오피스 리스트
    public List<DailyMovie> AllDailyBoxOfficeList(){
        List<DailyMovie> dailyBoxOfficeList = dailyMovieRepository.findAll();
        return dailyBoxOfficeList;
    }

    //어제 날짜의 일간 박스오피스 리스트( 오늘 보여질 박스 오피스 )
    public List<DailyMovie> TodayDailyBoxOfficeList(){

        //어제 일자 구해오기
        LocalDateTime time = LocalDateTime.now().minusDays(1);
        String targetDt =  time.format(DateTimeFormatter.ofPattern("yyyMMdd"));

        List<DailyMovie> dailyBoxOfficeList = dailyMovieRepository.findAllByTargetDt(targetDt);
        return dailyBoxOfficeList;
    }

    //전체 리스트 페이징 
    public Page<DailyMovie> PagingAllDailyBoxOfficeList(Pageable pageable)
    {
        Page<DailyMovie> pagingDailyBoxOfficeList = dailyMovieRepository.findAll(pageable);
        return pagingDailyBoxOfficeList;
    }

    //일간 박스오피스 페이징
    public Page<DailyMovie> PagingTodayDailyBoxOfficeList()
    {
        //어제 일자 구해오기
        LocalDateTime time = LocalDateTime.now().minusDays(1);
        String targetDt =  time.format(DateTimeFormatter.ofPattern("yyyMMdd"));
        Page<DailyMovie> pagingDailyBoxOfficeList = dailyMovieRepository.findAllByTargetDt(targetDt, PageRequest.of(0,5, Sort.Direction.ASC,"id"));
        return pagingDailyBoxOfficeList;
    }
}
