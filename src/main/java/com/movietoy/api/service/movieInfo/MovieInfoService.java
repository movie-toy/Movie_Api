package com.movietoy.api.service.movieInfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movietoy.api.domain.movieInfo.*;
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

        //심의정보 Data 넣기
        String audits = movieInfo.getAudits();
        Object auditsObj = jsonParser.parse(audits);
        JSONArray auditsJsonArray = (JSONArray) auditsObj;
        List<Audits> auditsList = new ArrayList<>();
        if (auditsJsonArray != null) {
            for (int i=0; i<auditsJsonArray.size(); i++){
                JSONObject auditsJsonObject = (JSONObject) auditsJsonArray.get(i);
                Audits audits1 = objectMapper.readValue(auditsJsonObject.toString(), Audits.class);
                auditsList.add(audits1);
            }
        }

        //제작사 Data 넣기
        String companys = movieInfo.getCompanys();
        Object companysObj = jsonParser.parse(companys);
        JSONArray companysJsonArray = (JSONArray) companysObj;
        List<Companys> companysList = new ArrayList<>();
        if (companysJsonArray != null) {
            for (int i=0; i<companysJsonArray.size(); i++){
                JSONObject auditsJsonObject = (JSONObject) companysJsonArray.get(i);
                Companys companys1 = objectMapper.readValue(auditsJsonObject.toString(), Companys.class);
                companysList.add(companys1);
            }
        }

        //영화감독 Data 넣기
        String directors = movieInfo.getDirectors();
        Object directorsObj = jsonParser.parse(directors);
        JSONArray directorsJsonArray = (JSONArray) directorsObj;
        List<Directors> directorsList = new ArrayList<>();
        if (directorsJsonArray != null) {
            for (int i=0; i<directorsJsonArray.size(); i++){
                JSONObject directorsJsonObject = (JSONObject) directorsJsonArray.get(i);
                Directors directors1 = objectMapper.readValue(directorsJsonObject.toString(), Directors.class);
                directorsList.add(directors1);
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

        //제작국가 Data 넣기
        String nations = movieInfo.getNations();
        Object nationsObj = jsonParser.parse(nations);
        JSONArray nationsJsonArray = (JSONArray) nationsObj;
        List<Nations> nationsList = new ArrayList<>();
        if (nationsJsonArray != null) {
            for (int i=0; i<nationsJsonArray.size(); i++){
                JSONObject nationsJsonObject = (JSONObject) nationsJsonArray.get(i);
                Nations Nations1 = objectMapper.readValue(nationsJsonObject.toString(), Nations.class);
                nationsList.add(Nations1);
            }
        }


        MovieInfoResponseDto movieInfoResponseDto = MovieInfoResponseDto.builder()
                .id(movieInfo.getId())
                .movieCd(movieInfo.getMovieCd())
                .movieNm(movieInfo.getMovieNm())
                .movieNmEn(movieInfo.getMovieNmEn())
                .movieNmOg(movieInfo.getMovieNmOg())
                .prdtYear(movieInfo.getPrdtYear())
                .openDt(movieInfo.getOpenDt())
                .prdtStatNm(movieInfo.getPrdtStatNm())
                .typeNm(movieInfo.getTypeNm())
                .actors(actorsList)
                .audits(auditsList)
                .companys(companysList)
                .directors(directorsList)
                .genres(genresList)
                .nations(nationsList)
                .build();

        return movieInfoResponseDto;
    }

}
