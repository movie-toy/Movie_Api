package com.movietoy.api.service.movieList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movietoy.api.domain.movieList.MovieList;
import com.movietoy.api.domain.movieList.MovieListRepository;
import com.movietoy.api.domain.movieList.Object.Companys;
import com.movietoy.api.domain.movieList.Object.Directors;
import com.movietoy.api.dto.movieList.MovieListResponseDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieListService {

    private final MovieListRepository movieListRepository;

    //일간 박스오피스 리스트
    @Cacheable("MovieList")
    @Transactional(readOnly = true)
    public List<MovieListResponseDto> MovieList() throws Exception{
        List<MovieList> movieList = movieListRepository.findAll();

        List<MovieListResponseDto> movieListResponse = new ArrayList<>();

        //JSON Parser 객체 생성
        JSONParser jsonParser = new JSONParser();
        ObjectMapper objectMapper = new ObjectMapper();

        for(int i=0; i < movieList.size(); i++){
            //제작사 Data 넣기
            String companys = movieList.get(i).getCompanys();
            Object companysObj = jsonParser.parse(companys);
            JSONArray companysJsonArray = (JSONArray) companysObj;
            List<Companys> companysList = new ArrayList<>();
            if (companysJsonArray != null) {
                for (int j=0; j<companysJsonArray.size(); j++){
                    JSONObject auditsJsonObject = (JSONObject) companysJsonArray.get(j);
                    Companys companys1 = objectMapper.readValue(auditsJsonObject.toString(), Companys.class);
                    companysList.add(companys1);
                }
            }

            //영화감독 Data 넣기
            String directors = movieList.get(i).getDirectors();
            Object directorsObj = jsonParser.parse(directors);
            JSONArray directorsJsonArray = (JSONArray) directorsObj;
            List<Directors> directorsList = new ArrayList<>();
            if (directorsJsonArray != null) {
                for (int k=0; k<directorsJsonArray.size(); k++){
                    JSONObject directorsJsonObject = (JSONObject) directorsJsonArray.get(k);
                    Directors directors1 = objectMapper.readValue(directorsJsonObject.toString(), Directors.class);
                    directorsList.add(directors1);
                }
            }

            MovieListResponseDto movieListResponseDto = MovieListResponseDto.builder()
                    .id(movieList.get(i).getId())
                    .movieCd(movieList.get(i).getMovieCd())
                    .movieNm(movieList.get(i).getMovieNm())
                    .movieNmEn(movieList.get(i).getMovieNmEn())
                    .prdtYear(movieList.get(i).getPrdtYear())
                    .openDt(movieList.get(i).getOpenDt())
                    .typeNm(movieList.get(i).getTypeNm())
                    .prdtStatNm(movieList.get(i).getPrdtStatNm())
                    .nationAlt(movieList.get(i).getNationAlt())
                    .genreAlt(movieList.get(i).getGenreAlt())
                    .repNationNm(movieList.get(i).getRepNationNm())
                    .repGenreNm(movieList.get(i).getRepGenreNm())
                    .directors(directorsList)
                    .companys(companysList)
                    .build();

            movieListResponse.add(movieListResponseDto);

        }

        return movieListResponse;
    }

    //일간 박스오피스 페이징
    @Cacheable("PagingMovieList")
    @Transactional(readOnly = true)
    public List<MovieListResponseDto> PagingMovieList(int page) throws Exception
    {
        //Paging 객체 가져오기
        Page<MovieList> pagingMovieList = movieListRepository.findAll(PageRequest.of(page,20, Sort.Direction.ASC,"id"));
        //페이징 처리된 Data만 리스트로 넘겨주기
        List<MovieList> movieList = pagingMovieList.getContent();

        List<MovieListResponseDto> movieListResponse = new ArrayList<>();

        //JSON Parser 객체 생성
        JSONParser jsonParser = new JSONParser();
        ObjectMapper objectMapper = new ObjectMapper();

        for(int i=0; i < movieList.size(); i++){
            //제작사 Data 넣기
            String companys = movieList.get(i).getCompanys();
            Object companysObj = jsonParser.parse(companys);
            JSONArray companysJsonArray = (JSONArray) companysObj;
            List<Companys> companysList = new ArrayList<>();
            if (companysJsonArray != null) {
                for (int j=0; j<companysJsonArray.size(); j++){
                    JSONObject auditsJsonObject = (JSONObject) companysJsonArray.get(j);
                    Companys companys1 = objectMapper.readValue(auditsJsonObject.toString(), Companys.class);
                    companysList.add(companys1);
                }
            }

            //영화감독 Data 넣기
            String directors = movieList.get(i).getDirectors();
            Object directorsObj = jsonParser.parse(directors);
            JSONArray directorsJsonArray = (JSONArray) directorsObj;
            List<Directors> directorsList = new ArrayList<>();
            if (directorsJsonArray != null) {
                for (int k=0; k<directorsJsonArray.size(); k++){
                    JSONObject directorsJsonObject = (JSONObject) directorsJsonArray.get(k);
                    Directors directors1 = objectMapper.readValue(directorsJsonObject.toString(), Directors.class);
                    directorsList.add(directors1);
                }
            }

            MovieListResponseDto movieListResponseDto = MovieListResponseDto.builder()
                    .id(movieList.get(i).getId())
                    .movieCd(movieList.get(i).getMovieCd())
                    .movieNm(movieList.get(i).getMovieNm())
                    .movieNmEn(movieList.get(i).getMovieNmEn())
                    .prdtYear(movieList.get(i).getPrdtYear())
                    .openDt(movieList.get(i).getOpenDt())
                    .typeNm(movieList.get(i).getTypeNm())
                    .prdtStatNm(movieList.get(i).getPrdtStatNm())
                    .nationAlt(movieList.get(i).getNationAlt())
                    .genreAlt(movieList.get(i).getGenreAlt())
                    .repNationNm(movieList.get(i).getRepNationNm())
                    .repGenreNm(movieList.get(i).getRepGenreNm())
                    .directors(directorsList)
                    .companys(companysList)
                    .build();

            movieListResponse.add(movieListResponseDto);

        }

        return movieListResponse;
    }



}
