package com.movietoy.api.domain.movieInfo.Object;

import lombok.Data;

@Data
public class Actors {

    /*배역명*/
    private String cast;

    /*배역명(영문)*/
    private String castEn;

    /*배우명*/
    private String peopleNm;

    /*배우명(영문)*/
    private String peopleNmEn;
}
