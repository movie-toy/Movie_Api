package com.movietoy.api.service.movieInfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movietoy.api.domain.movieInfo.Actors;
import com.movietoy.api.domain.movieInfo.Genres;
import com.movietoy.api.domain.movieInfo.MovieInfo;
import com.movietoy.api.domain.movieInfo.MovieInfoRepository;
import com.movietoy.api.dto.movieInfo.MovieInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MovieInfoService {

    private final MovieInfoRepository movieInfoRepository;

    //영화 상세 정보 조회
    @Transactional(readOnly = true)
    public MovieInfoResponseDto MovieInfo(String movieCd) throws Exception{

        MovieInfo movieInfo = movieInfoRepository.findByMovieCd(movieCd);
        //JSON Parser 객체 생성
        JSONParser jsonParser = new JSONParser();
        ObjectMapper objectMapper = new ObjectMapper();

        //영화배우 Data 넣기
        String actors = movieInfo.getActors();
        Object actorsObj = jsonParser.parse(actors);
        JSONArray actorsJsonArray = (JSONArray) actorsObj;
        List<Actors> actorsList = new ArrayList<>();
        if (actorsJsonArray != null) {
            for (int i=0; i<actorsJsonArray.size(); i++){
                JSONObject actorsJsonObject = (JSONObject) actorsJsonArray.get(i);
                Actors actors1 = objectMapper.readValue(actorsJsonObject.toString(), Actors.class);
                actorsList.add(actors1);
            }
        }

        //장르 Data 넣기
        String genres = movieInfo.getGenres();
        Object genresObj = jsonParser.parse(genres);
        JSONArray genresJsonArray = (JSONArray) genresObj;
        List<Genres> genresList = new ArrayList<>();
        if (genresJsonArray != null) {
            for (int i=0; i<genresJsonArray.size(); i++){
                JSONObject genresJsonObject = (JSONObject) genresJsonArray.get(i);
                Genres genres1 = objectMapper.readValue(genresJsonObject.toString(), Genres.class);
                genresList.add(genres1);
            }
        }



        MovieInfoResponseDto movieInfoResponseDto = MovieInfoResponseDto.builder()
                .movieCd(movieInfo.getMovieCd())
                .movieNm(movieInfo.getMovieNm())
                .movieNmEn(movieInfo.getMovieNmEn())
                .movieNmOg(movieInfo.getMovieNmOg())
                .prdtYear(movieInfo.getPrdtYear())
                .openDt(movieInfo.getOpenDt())
                .prdtStatNm(movieInfo.getPrdtStatNm())
                .typeNm(movieInfo.getTypeNm())
                .actors(actorsList)
                .genres(genresList)
                .build();

        return movieInfoResponseDto;
    }

}
