package com.movietoy.api.controller.dailyBoxOffice;

import com.movietoy.api.domain.dailyBoxOffice.DailyMovie;
import com.movietoy.api.dto.Message;
import com.movietoy.api.dto.StatusEnum;
import com.movietoy.api.service.dailyBoxOffice.DailyBoxOfficeService;
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
public class DailyBoxOfficeController {

    @Autowired
    public DailyBoxOfficeService dailyBoxOfficeService;

    @GetMapping(value = "/daily/list")
    public ResponseEntity<Message> DailyBoxOfficeList(){

        //일간 박스오피스 리스트
        List<DailyMovie> dailyBoxOfficeList = dailyBoxOfficeService.DailyBoxOfficeList();

        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공");
        message.setData(dailyBoxOfficeList);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        return new ResponseEntity<>(message, httpHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "/daily/paging")
    public ResponseEntity<Message> PagingDailyBoxOfficeList(@RequestParam(defaultValue = "0") int page){

        List<DailyMovie> dailyBoxOfficeList = new ArrayList<>();

        //첫 0페이지는 캐싱
        if(page == 0){
            dailyBoxOfficeList = dailyBoxOfficeService.CacheablePagingDailyBoxOfficeList(page);
        //아니면 캐싱제외
        }else{
            dailyBoxOfficeList = dailyBoxOfficeService.PagingDailyBoxOfficeList(page);
        }

        Message message = new Message();
        message.setStatus(StatusEnum.OK);
        message.setMessage("성공");
        message.setData(dailyBoxOfficeList);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type","application/json");

        return new ResponseEntity<>(message, httpHeaders, HttpStatus.OK);
    }


}
