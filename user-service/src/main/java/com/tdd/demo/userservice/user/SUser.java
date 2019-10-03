package com.tdd.demo.userservice.user;

//import com.bench.tdd.demo.tdddemo.post.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@ApiModel(description = "User class")
public class SUser {

    @Id
    @GeneratedValue
    private int id;
    @ApiModelProperty("Cannot be an empty string")
    private String username;



    // make space for posts
    /*@OneToMany(mappedBy = "user")
    private List<Post> user_posts;*/
    // Initially shared between Post service and user service, now removed to loosely couple systems
    private List<Integer> postIds;

    public List<Integer> getPostIds() {
        return postIds;
    }

    public void setPostIds(List<Integer> postIds) {
        this.postIds = postIds;
    }

    public SUser(String username) {
        this.username = username;
    }

    protected SUser() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                ", postIds=" + postIds +
                '}';
    }
}
