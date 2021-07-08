package com.movietoy.api.dto.movieList;

import com.movietoy.api.domain.movieList.Object.Companys;
import com.movietoy.api.domain.movieList.Object.Directors;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MovieListResponseDto {

    private Long id;

    private String movieCd;

    private String movieNm;

    private String movieNmEn;

    private String prdtYear;

    private String openDt;

    private String typeNm;

    private String prdtStatNm;

    private String nationAlt;

    private String genreAlt;

    private String repNationNm;

    private String repGenreNm;

    private String batchStatus;

    //영화감독
    private List<Directors> directors;
    //제작사
    private List<Companys> companys;

    @Builder
    public MovieListResponseDto(Long id, String movieCd, String movieNm, String movieNmEn, String prdtYear, String openDt, String typeNm, String prdtStatNm, String nationAlt, String genreAlt, String repNationNm, String repGenreNm, String batchStatus, List<Directors> directors, List<Companys> companys) {
        this.id = id;
        this.movieCd = movieCd;
        this.movieNm = movieNm;
        this.movieNmEn = movieNmEn;
        this.prdtYear = prdtYear;
        this.openDt = openDt;
        this.typeNm = typeNm;
        this.prdtStatNm = prdtStatNm;
        this.nationAlt = nationAlt;
        this.genreAlt = genreAlt;
        this.repNationNm = repNationNm;
        this.repGenreNm = repGenreNm;
        this.batchStatus = batchStatus;
        this.directors = directors;
        this.companys = companys;
    }
}
