package com.movietoy.api.dto.movieInfo;

import com.movietoy.api.domain.movieInfo.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.tool.schema.extract.spi.NameSpaceTablesInformation;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class MovieInfoResponseDto {

    private Long Id;

    private String movieCd;

    private String movieNm;

    private String movieNmEn;

    private String movieNmOg;

    private String prdtYear;

    private String openDt;

    private String prdtStatNm;

    private String typeNm;

    //영화배우
    private List<Actors> actors;

    //장르
    private List<Genres> genres;

    //심의정보
    private List<Audits> audits;

    //제작사
    private List<Companys> companys;

    //영화감독
    private List<Directors> directors;

    //제작국가
    private List<Nations> nations;

    @Builder
    public MovieInfoResponseDto(Long id, String movieCd, String movieNm, String movieNmEn, String movieNmOg, String prdtYear, String openDt, String prdtStatNm, String typeNm, List<Actors> actors, List<Genres> genres, List<Audits> audits, List<Companys> companys, List<Directors> directors, List<Nations> nations) {
        Id = id;
        this.movieCd = movieCd;
        this.movieNm = movieNm;
        this.movieNmEn = movieNmEn;
        this.movieNmOg = movieNmOg;
        this.prdtYear = prdtYear;
        this.openDt = openDt;
        this.prdtStatNm = prdtStatNm;
        this.typeNm = typeNm;
        this.actors = actors;
        this.genres = genres;
        this.audits = audits;
        this.companys = companys;
        this.directors = directors;
        this.nations = nations;
    }
}
