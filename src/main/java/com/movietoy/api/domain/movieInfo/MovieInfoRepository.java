package com.movietoy.api.domain.movieInfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieInfoRepository extends JpaRepository<MovieInfo, Long> {

    MovieInfo findByMovieCd(String movieCd);
}
