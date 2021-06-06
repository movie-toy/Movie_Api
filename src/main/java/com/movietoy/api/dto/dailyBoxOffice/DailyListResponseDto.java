package com.movietoy.api.dto.dailyBoxOffice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DailyListResponseDto {

    private Long Id;
    private String boxofficeType;
    private String showRange;
    private String rnum;
    private String rank;
    private String rankInten;
    private String rankOldAndNew;
    private String movieCd;
    private String movieNm;
    private String openDt;
    private String salesAmt;
    private String salesShare;
    private String salesInten;
    private String salesChange;
    private String salesAcc;
    private String audiCnt;
    private String audiInten;
    private String audiChange;
    private String audiAcc;
    private String scrnCnt;
    private String showCnt;
    private String targetDt;
}
