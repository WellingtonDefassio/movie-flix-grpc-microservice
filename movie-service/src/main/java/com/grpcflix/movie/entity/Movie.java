package com.grpcflix.movie.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@ToString
public class Movie {
    @Id
    private Integer id;
    private String tittle;
    @Column(name = "launch_year")
    private Integer launchYear;
    private Double rating;
    private String genre;
}
