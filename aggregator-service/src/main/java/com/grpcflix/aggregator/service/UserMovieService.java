package com.grpcflix.aggregator.service;

import com.grpcflix.aggregator.dto.RecommendedMovie;
import com.grpcflix.aggregator.dto.UserGenreRequest;
import com.grpcflix.common.Genre;
import com.grpcflix.movie.MovieSearchRequest;
import com.grpcflix.movie.MovieSearchResponse;
import com.grpcflix.movie.MovieServiceGrpc;
import com.grpcflix.user.UserGenreUpdateRequest;
import com.grpcflix.user.UserResponse;
import com.grpcflix.user.UserSearchRequest;
import com.grpcflix.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMovieService {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userStub;

    @GrpcClient("movie-service")
    private MovieServiceGrpc.MovieServiceBlockingStub movieStub;


    public List<RecommendedMovie> getUserMovieSuggestions(String loginId) {
        UserSearchRequest userSearchRequest = UserSearchRequest.newBuilder().setLoginId(loginId).build();
        UserResponse userResponse = this.userStub.getUserGenre(userSearchRequest);
        MovieSearchRequest movieSearchRequest = MovieSearchRequest.newBuilder().setGenre(userResponse.getGenre()).build();
        MovieSearchResponse movieSearchResponse = this.movieStub.getMovies(movieSearchRequest);
        return movieSearchResponse.getMovieList().stream().map(RecommendedMovie::fromModel).collect(Collectors.toList());
    }

    public void setUserGenre(UserGenreRequest userGenreRequest) {
        UserGenreUpdateRequest genreUpdateRequest = UserGenreUpdateRequest.newBuilder()
                .setLoginId(userGenreRequest.getLoginId())
                .setGenre(Genre.valueOf(userGenreRequest.getGenre().toUpperCase()))
                .build();
        UserResponse userResponse = this.userStub.updateUserGenre(genreUpdateRequest);

    }

}
