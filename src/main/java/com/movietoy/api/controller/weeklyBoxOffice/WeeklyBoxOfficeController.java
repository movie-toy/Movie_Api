package com.movietoy.api.controller.weeklyBoxOffice;

import com.movietoy.api.domain.weeklyBoxOffice.WeeklyMovie;
import com.movietoy.api.service.weeklyBoxOffice.WeeklyBoxOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeeklyBoxOfficeController {

    @Autowired
    public WeeklyBoxOfficeService weeklyBoxOfficeService;


    @RequestMapping(value = "/weekly/list")
    public ResponseEntity<List<WeeklyMovie>> WeeklyBoxOfficeList(){

        //주간 박스오피스 리스트
        List<WeeklyMovie> weeklyBoxOfficeList = weeklyBoxOfficeService.WeeklyBoxOfficeList();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        return new ResponseEntity<List<WeeklyMovie>>(weeklyBoxOfficeList, HttpStatus.OK);
    }

    @RequestMapping(value = "/weekly/paging")
    public ResponseEntity<Page<WeeklyMovie>> PagingWeeklyBoxOfficeList(@RequestParam(defaultValue = "0") int page){

        //주간 박스오피스 페이징
        Page<WeeklyMovie> weeklyBoxOfficeList = weeklyBoxOfficeService.PagingWeeklyBoxOfficeList(page);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        return new ResponseEntity<Page<WeeklyMovie>>(weeklyBoxOfficeList, HttpStatus.OK);
    }
}
