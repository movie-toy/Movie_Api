package com.movietoy.api.domain.movieInfo.Object;

import lombok.Data;

@Data
public class Companys {

    /*참여 영화사 코드*/
    private String companyCd;

    /*참여 영화사명*/
    private String companyNm;

    /*참여 영화사명(영문)*/
    private String companyNmEn;

    /*참여 영화사 분야명*/
    private String companyPartNm;
}
