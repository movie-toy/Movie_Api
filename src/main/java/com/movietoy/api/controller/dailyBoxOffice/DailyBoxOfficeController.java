package com.movietoy.api.controller.dailyBoxOffice;

import com.movietoy.api.domain.dailyBoxOffice.DailyMovie;
import com.movietoy.api.service.dailyBoxOffice.DailyBoxOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DailyBoxOfficeController {

    @Autowired
    public DailyBoxOfficeService dailyBoxOfficeService;

    @GetMapping(value = "/daily/list")
    public ResponseEntity<List<DailyMovie>> DailyBoxOfficeList(){

        //일간 박스오피스 리스트
        List<DailyMovie> dailyBoxOfficeList = dailyBoxOfficeService.DailyBoxOfficeList();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        return new ResponseEntity<>(dailyBoxOfficeList, HttpStatus.OK);
    }

    @GetMapping(value = "/daily/paging")
    public ResponseEntity<List<DailyMovie>> PagingDailyBoxOfficeList(@RequestParam(defaultValue = "0") int page){

        List<DailyMovie> dailyBoxOfficeList = dailyBoxOfficeService.PagingDailyBoxOfficeList(page);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        return new ResponseEntity<>(dailyBoxOfficeList, HttpStatus.OK);
    }


}
