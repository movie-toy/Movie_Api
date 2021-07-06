package com.movietoy.api.dto.movieInfo;

import com.movietoy.api.domain.movieInfo.Actors;
import com.movietoy.api.domain.movieInfo.Genres;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private List<Actors> actors;

    private List<Genres> genres;

    @Builder
    public MovieInfoResponseDto(Long id, String movieCd, String movieNm, String movieNmEn, String movieNmOg, String prdtYear, String openDt, String prdtStatNm, String typeNm, List<Actors> actors, List<Genres> genres) {
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
    }
}
