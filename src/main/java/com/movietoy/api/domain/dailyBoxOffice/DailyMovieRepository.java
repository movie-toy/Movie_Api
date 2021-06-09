package com.movietoy.api.domain.dailyBoxOffice;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyMovieRepository extends JpaRepository<DailyMovie, Long> {

    List<DailyMovie> findAllByTargetDt(String targetDt);

    Page<DailyMovie> findAllByTargetDt(String targetDt, Pageable pageable);
}

