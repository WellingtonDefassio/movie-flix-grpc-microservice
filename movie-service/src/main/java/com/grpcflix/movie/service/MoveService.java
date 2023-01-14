package com.grpcflix.movie.service;

import com.grpcflix.movie.MovieDto;
import com.grpcflix.movie.MovieSearchRequest;
import com.grpcflix.movie.MovieSearchResponse;
import com.grpcflix.movie.MovieServiceGrpc;
import com.grpcflix.movie.repository.MovieRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class MoveService extends MovieServiceGrpc.MovieServiceImplBase {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public void getMovies(MovieSearchRequest request, StreamObserver<MovieSearchResponse> responseObserver) {
        List<MovieDto> movieDtoList = movieRepository.getMovieByGenreOrderByLaunchYearDesc(request.getGenre().toString())
                .stream().map(m -> MovieDto.newBuilder()
                        .setTitle(m.getTittle())
                        .setYear(m.getLaunchYear())
                        .setRating(m.getRating())
                        .build()
                ).collect(Collectors.toList());

        responseObserver.onNext(MovieSearchResponse.newBuilder().addAllMovie(movieDtoList).build());
        responseObserver.onCompleted();
    }
}
