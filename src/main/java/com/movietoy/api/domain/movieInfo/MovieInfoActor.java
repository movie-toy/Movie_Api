package com.movietoy.api.domain.movieInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieInfoActor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="movieCd")
    private String movieCd;

    private String peopleNm;

    private String poepleNmEn;

    private String cast;

    private String castEn;
}


