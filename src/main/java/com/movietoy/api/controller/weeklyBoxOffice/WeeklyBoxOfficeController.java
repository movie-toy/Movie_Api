package com.movietoy.api.controller.weeklyBoxOffice;

import com.movietoy.api.domain.weeklyBoxOffice.WeeklyMovie;
import com.movietoy.api.dto.Message;
import com.movietoy.api.dto.StatusEnum;
import com.movietoy.api.service.weeklyBoxOffice.WeeklyBoxOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WeeklyBoxOfficeController {

    @Autowired
    public WeeklyBoxOfficeService weeklyBoxOfficeService;


    @GetMapping(value = "/weekly/list")
    public ResponseEntity<Message> WeeklyBoxOfficeList(){

        //주간 박스오피스 리스트
        List<WeeklyMovie> weeklyBoxOfficeList = weeklyBoxOfficeService.WeeklyBoxOfficeList();

        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공");
        message.setData(weeklyBoxOfficeList);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        return new ResponseEntity<>(message, httpHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "/weekly/paging")
    public ResponseEntity<Message> PagingWeeklyBoxOfficeList(@RequestParam(defaultValue = "0") int page){
        List<WeeklyMovie> weeklyBoxOfficeList = new ArrayList<>();

        //첫 0페이지는 캐싱
        if(page == 0){
            weeklyBoxOfficeList = weeklyBoxOfficeService.PagingWeeklyBoxOfficeList(page);
        //아니면 캐싱제외
        }else{
            weeklyBoxOfficeList = weeklyBoxOfficeService.PagingWeeklyBoxOfficeList(page);
        }

        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공");
        message.setData(weeklyBoxOfficeList);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        return new ResponseEntity<>(message, httpHeaders, HttpStatus.OK);
    }
}
