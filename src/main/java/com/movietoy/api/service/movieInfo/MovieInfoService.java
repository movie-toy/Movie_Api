package com.movietoy.api.service.movieInfo;

import com.movietoy.api.domain.movieInfo.MovieInfo;
import com.movietoy.api.domain.movieInfo.MovieInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MovieInfoService {

    private final MovieInfoRepository movieInfoRepository;

    //영화 상세 정보 조회
    @Transactional(readOnly = true)
    @Cacheable("MovieInfo")
    public MovieInfo MovieInfo(String movieCd) {
        return movieInfoRepository.findByMovieCd(movieCd);
    }

}
