package com.grpc.flix.user.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@ToString
@Table(name = "user_db")
public class User {

    @Id
    private String login;
    private String name;
    private String genre;

}
