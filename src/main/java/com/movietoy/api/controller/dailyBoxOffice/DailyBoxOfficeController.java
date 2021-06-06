package com.movietoy.api.controller.dailyBoxOffice;

import com.movietoy.api.domain.dailyBoxOffice.DailyMovie;
import com.movietoy.api.service.dailyBoxOffice.DailyBoxOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class DailyBoxOfficeController {

    @Autowired
    public DailyBoxOfficeService dailyBoxOfficeService;

    @RequestMapping(value = "/daily/allList")
    public ResponseEntity<List<DailyMovie>> AllDailyBoxOfficeList(){
        List<DailyMovie> dailyBoxOfficeList = dailyBoxOfficeService.AllDailyBoxOfficeList();
        return new ResponseEntity<List<DailyMovie>>(dailyBoxOfficeList, HttpStatus.OK);
    }

    @RequestMapping(value = "/daily/todayList")
    public ResponseEntity<List<DailyMovie>> TodayDailyBoxOfficeList(){
        List<DailyMovie> dailyBoxOfficeList = dailyBoxOfficeService.TodayDailyBoxOfficeList();
        return new ResponseEntity<List<DailyMovie>>(dailyBoxOfficeList, HttpStatus.OK);
    }

    @RequestMapping(value = "/daily/pagingAllList")
    public ResponseEntity<Page<DailyMovie>> PagingAllDailyBoxOfficeList(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<DailyMovie> dailyBoxOfficeList = dailyBoxOfficeService.PagingAllDailyBoxOfficeList(pageable);
        return new ResponseEntity<Page<DailyMovie>>(dailyBoxOfficeList, HttpStatus.OK);
    }

    @RequestMapping(value = "/daily/pagingTodayList")
    public ResponseEntity<Page<DailyMovie>> PagingTodayDailyBoxOfficeList(){
        Page<DailyMovie> dailyBoxOfficeList = dailyBoxOfficeService.PagingTodayDailyBoxOfficeList();
        return new ResponseEntity<Page<DailyMovie>>(dailyBoxOfficeList, HttpStatus.OK);
    }

}
