/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

/**
 *
 * @author Sampan-IT
 */
public class Comment {

    private String description;
    private String commentId;
    private UserInfo userInfo;
    private List<Like> like;

    public String getDescription() {
        return description;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<Like> getLike() {
        return like;
    }

    public void setLike(List<Like> like) {
        this.like = like;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return json;
    }

    public static Comment getStatusComment(String jsonContent) {
        Comment commentInfo = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            commentInfo = mapper.readValue(jsonContent, Comment.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return commentInfo;
    }
}
