package com.movietoy.api.controller.dailyBoxOffice;

import com.movietoy.api.domain.dailyBoxOffice.DailyMovie;
import com.movietoy.api.service.dailyBoxOffice.DailyBoxOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
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

/*    @RequestMapping(value = "/daily/allList")
    public ResponseEntity<List<DailyMovie>> AllDailyBoxOfficeList(){
        
        //일간 박스오피스 모든 리스트 가져오기
        List<DailyMovie> dailyBoxOfficeList = dailyBoxOfficeService.AllDailyBoxOfficeList();
        
        //Header 입력
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");


        return new ResponseEntity<List<DailyMovie>>(dailyBoxOfficeList, httpHeaders, HttpStatus.OK);
    }*/

    @RequestMapping(value = "/daily/list")
    public ResponseEntity<List<DailyMovie>> TodayDailyBoxOfficeList(){

        //일간 박스오피스 리스트
        List<DailyMovie> dailyBoxOfficeList = dailyBoxOfficeService.TodayDailyBoxOfficeList();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        return new ResponseEntity<List<DailyMovie>>(dailyBoxOfficeList, HttpStatus.OK);
    }

/*    @RequestMapping(value = "/daily/pagingAllList")
    public ResponseEntity<Page<DailyMovie>> PagingAllDailyBoxOfficeList(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<DailyMovie> dailyBoxOfficeList = dailyBoxOfficeService.PagingAllDailyBoxOfficeList(pageable);
        return new ResponseEntity<Page<DailyMovie>>(dailyBoxOfficeList, HttpStatus.OK);
    }*/

    @RequestMapping(value = "/daily/listPaging")
    public ResponseEntity<Page<DailyMovie>> PagingTodayDailyBoxOfficeList(){

        Page<DailyMovie> dailyBoxOfficeList = dailyBoxOfficeService.PagingTodayDailyBoxOfficeList();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        return new ResponseEntity<Page<DailyMovie>>(dailyBoxOfficeList, HttpStatus.OK);
    }

}
