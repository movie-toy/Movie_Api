package com.movietoy.api.service.dailyBoxOffice;

import com.movietoy.api.domain.dailyBoxOffice.DailyMovie;
import com.movietoy.api.domain.dailyBoxOffice.DailyMovieRepository;
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
public class DailyBoxOfficeService {

    private final DailyMovieRepository dailyMovieRepository;

    //일간 박스오피스 리스트
    @Cacheable("DailyBoxOfficeList")
    @Transactional(readOnly = true)
    public List<DailyMovie> DailyBoxOfficeList(){
        //어제 일자 구해오기
        LocalDateTime time = LocalDateTime.now().minusDays(1);
        String targetDt =  time.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        List<DailyMovie> dailyBoxOfficeList = dailyMovieRepository.findAllByTargetDt(targetDt);
        return dailyBoxOfficeList;
    }

    //일간 박스오피스 페이징
    @Cacheable("DailyBoxOfficeList")
    @Transactional(readOnly = true)
    public List<DailyMovie> PagingDailyBoxOfficeList(int page)
    {
        //어제 일자 구해오기
        LocalDateTime time = LocalDateTime.now().minusDays(1);
        String targetDt =  time.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        //Paging 객체 가져오기
        Page<DailyMovie> pagingDailyBoxOfficeList = dailyMovieRepository.findAllByTargetDt(targetDt, PageRequest.of(page,5, Sort.Direction.ASC,"id"));
        //페이징 처리된 Data만 리스트로 넘겨주기
        List<DailyMovie> dailyBoxOfficeList = pagingDailyBoxOfficeList.getContent();

        return dailyBoxOfficeList;
    }
}
