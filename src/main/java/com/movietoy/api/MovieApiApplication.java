package com.movietoy.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class MovieApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieApiApplication.class, args);
    }

}
