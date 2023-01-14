package com.grpc.flix.user.service;

import com.grpc.flix.user.repository.UserRepository;
import com.grpcflix.common.Genre;
import com.grpcflix.user.UserGenreUpdateRequest;
import com.grpcflix.user.UserResponse;
import com.grpcflix.user.UserSearchRequest;
import com.grpcflix.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void getUserGenre(UserSearchRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse.Builder builder = UserResponse.newBuilder();
        this.userRepository.findById(request.getLoginId())
                .ifPresent(u -> {
                    builder.setName(u.getName())
                            .setLoginId(u.getLogin())
                            .setGenre(Genre.valueOf(u.getGenre().toUpperCase()));
                });
       responseObserver.onNext(builder.build());
       responseObserver.onCompleted();
    }

    @Override
    @Transactional
    public void updateUserGenre(UserGenreUpdateRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse.Builder builder = UserResponse.newBuilder();
        this.userRepository.findById(request.getLoginId()).ifPresent(u -> {
            u.setGenre(request.getGenre().toString());
            builder.setName(u.getName())
                    .setLoginId(u.getLogin())
                    .setGenre(Genre.valueOf(u.getGenre().toUpperCase()));
        });
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
