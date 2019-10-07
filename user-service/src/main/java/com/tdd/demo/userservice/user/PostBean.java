package com.tdd.demo.userservice.user;

public class PostBean {
    private String postContent;
    private int userId;
    private int postId;

    public PostBean(String content, int userId, int postId) {
        this.postContent = content;
        this.userId = userId;
        this.postId = postId;

    }

    @Override
    public String toString() {
        return "PostBean{" +
                "postContent='" + postContent + '\'' +
                ", userId=" + userId +
                ", postId=" + postId +
                '}';
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
