package com.movietoy.api.domain.weeklyBoxOffice;

import com.movietoy.api.domain.dailyBoxOffice.DailyMovie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeeklyMovieRepository extends JpaRepository<WeeklyMovie, Long> {

    List<WeeklyMovie> findAllByYearWeekTime(String yearWeekTime);

    Page<WeeklyMovie> findAllByYearWeekTime(String yearWeekTime, Pageable pageable);

}
