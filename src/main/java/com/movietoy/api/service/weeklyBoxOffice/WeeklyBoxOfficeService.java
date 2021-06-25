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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class WeeklyBoxOfficeService {

    private final WeeklyMovieRepository weeklyMovieRepository;

    //금주의 주간 박스오피스 리스트
    @Cacheable("WeeklyBoxOfficeList")
    @Transactional(readOnly = true)
    public List<WeeklyMovie> WeeklyBoxOfficeList(){

        //오늘 년도 구해오기
        LocalDateTime time = LocalDateTime.now();
        String year = time.format(DateTimeFormatter.ofPattern("yyyy"));

        //해당 년의 몇주차인지 가져오기
        Calendar now = Calendar.getInstance();
        //오늘이 무슨 요일인지 가져오기 ( 1:일 , 2:월, 3:화 .... 7:토)
        int todayDate = now.get(Calendar.DAY_OF_WEEK);
        System.out.println(todayDate);
        //오늘이 몇주차인지 가져오기
        int getweekOfYear = now.get(Calendar.WEEK_OF_YEAR);

        String weekOfYear;
        if(todayDate == 1){ //일요일이면 -3
            weekOfYear = Integer.toString(getweekOfYear-3);
        }else{  //나머지는 -2
            weekOfYear = Integer.toString(getweekOfYear-2);
        }

        String yearWeekTime = year+weekOfYear;

        List<WeeklyMovie> weeklyBoxOfficeList = weeklyMovieRepository.findAllByYearWeekTime(yearWeekTime);
        return weeklyBoxOfficeList;
    }

    //주간 박스오피스 페이징
    @Cacheable("WeeklyBoxOfficeList")
    @Transactional(readOnly = true)
    public List<WeeklyMovie> PagingWeeklyBoxOfficeList(int page)
    {
        //오늘 년도 구해오기
        LocalDateTime time = LocalDateTime.now();
        String year = time.format(DateTimeFormatter.ofPattern("yyyy"));

        //해당 년의 몇주차인지 가져오기
        Calendar now = Calendar.getInstance();
        //오늘이 무슨 요일인지 가져오기 ( 1:일 , 2:월, 3:화 .... 7:토)
        int todayDate = now.get(Calendar.DAY_OF_WEEK);
        System.out.println(todayDate);
        //오늘이 몇주차인지 가져오기
        int getweekOfYear = now.get(Calendar.WEEK_OF_YEAR);

        String weekOfYear;
        if(todayDate == 1){ //일요일이면 -3
            weekOfYear = Integer.toString(getweekOfYear-3);
        }else{  //나머지는 -2
            weekOfYear = Integer.toString(getweekOfYear-2);
        }

        String yearWeekTime = year+weekOfYear;

        Page<WeeklyMovie> pagingWeeklyBoxOfficeList = weeklyMovieRepository.findAllByYearWeekTime(yearWeekTime, PageRequest.of(page,5, Sort.Direction.ASC,"id"));

        //페이징 처리된 Data만 리스트로 넘겨주기
        List<WeeklyMovie> weeklyBoxOfficeList = pagingWeeklyBoxOfficeList.getContent();

        return weeklyBoxOfficeList;
    }
}
