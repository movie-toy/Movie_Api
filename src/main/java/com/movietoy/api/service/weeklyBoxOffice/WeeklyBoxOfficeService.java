package com.movietoy.api.service.weeklyBoxOffice;

import com.movietoy.api.domain.dailyBoxOffice.DailyMovie;
import com.movietoy.api.domain.weeklyBoxOffice.WeeklyMovie;
import com.movietoy.api.domain.weeklyBoxOffice.WeeklyMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeeklyBoxOfficeService {

    private final WeeklyMovieRepository weeklyMovieRepository;

    //금주의 주간 박스오피스 리스트
    @Cacheable("WeeklyBoxOfficeList")
    public List<WeeklyMovie> WeeklyBoxOfficeList(){

        //오늘 년도 구해오기
        LocalDateTime time = LocalDateTime.now();
        String year = time.format(DateTimeFormatter.ofPattern("yyyy"));

        //해당 년의 몇주차인지 가져오기
        Calendar now = Calendar.getInstance();
        int getweekOfYear = now.get(Calendar.WEEK_OF_YEAR);
        String weekOfYear = Integer.toString(getweekOfYear-2);

        //년도+주차
        String yearWeekTime = year+weekOfYear;

        List<WeeklyMovie> weeklyBoxOfficeList = weeklyMovieRepository.findAllByYearWeekTime(yearWeekTime);
        return weeklyBoxOfficeList;
    }

    //주간 박스오피스 페이징
    public Page<WeeklyMovie> PagingWeeklyBoxOfficeList(int page)
    {
        //어제 일자 구해오기
        LocalDateTime time = LocalDateTime.now();
        String year =  time.format(DateTimeFormatter.ofPattern("yyyy"));

        //해당 년의 몇주차인지 가져오기
        Calendar now = Calendar.getInstance();
        int getweekOfYear = now.get(Calendar.WEEK_OF_YEAR);
        String weekOfYear = Integer.toString(getweekOfYear-2);

        //년도+주차
        String yearWeekTime = year+weekOfYear;

        Page<WeeklyMovie> weeklyBoxOfficeList = weeklyMovieRepository.findAllByYearWeekTime(yearWeekTime, PageRequest.of(page,5, Sort.Direction.ASC,"id"));
        return weeklyBoxOfficeList;
    }
}
