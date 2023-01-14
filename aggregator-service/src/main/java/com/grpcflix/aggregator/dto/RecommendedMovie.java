package com.grpcflix.aggregator.dto;

import com.grpcflix.movie.MovieDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.DoubleStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecommendedMovie {

    private String title;
    private Integer year;
    private Double rating;

    public static RecommendedMovie fromModel(MovieDto movieDto) {
        return RecommendedMovie.builder()
                .title(movieDto.getTitle())
                .year(movieDto.getYear())
                .rating(movieDto.getRating())
                .build();
    }
}
