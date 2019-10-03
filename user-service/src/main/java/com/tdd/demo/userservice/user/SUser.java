package com.tdd.demo.userservice.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user")
@ApiModel(description = "User class")
public class SUser {


    @GeneratedValue()
    private int id;
    @ApiModelProperty("Cannot be an empty string")
    @Id
    private String username;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public SUser(String username) {
        this.username = username;
    }

    protected SUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    @Override
    public String toString() {
        return "SUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
