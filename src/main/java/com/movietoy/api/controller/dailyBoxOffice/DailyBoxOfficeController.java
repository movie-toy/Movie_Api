package com.movietoy.api.controller.dailyBoxOffice;

import com.movietoy.api.domain.dailyBoxOffice.DailyMovie;
import com.movietoy.api.service.dailyBoxOffice.DailyBoxOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class DailyBoxOfficeController {

    @Autowired
    public DailyBoxOfficeService dailyBoxOfficeService;

    @RequestMapping(value = "/daily/list")
    public ResponseEntity<List<DailyMovie>> DailyBoxOfficeList(){

        //일간 박스오피스 리스트
        List<DailyMovie> dailyBoxOfficeList = dailyBoxOfficeService.DailyBoxOfficeList();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        return new ResponseEntity<>(dailyBoxOfficeList, HttpStatus.OK);
    }

    @RequestMapping(value = "/daily/paging")
    public ResponseEntity<Page<DailyMovie>> PagingDailyBoxOfficeList(@RequestParam(defaultValue = "0") int page){

        Page<DailyMovie> dailyBoxOfficeList = dailyBoxOfficeService.PagingDailyBoxOfficeList(page);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        return new ResponseEntity<>(dailyBoxOfficeList, HttpStatus.OK);
    }


}
