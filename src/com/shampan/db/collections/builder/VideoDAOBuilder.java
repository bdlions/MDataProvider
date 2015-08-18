/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.VideoDAO;
import com.shampan.db.collections.fragment.common.Comment;
import com.shampan.db.collections.fragment.common.Like;
import com.shampan.db.collections.fragment.common.Privacy;
import com.shampan.db.collections.fragment.common.Share;
import com.shampan.db.collections.fragment.common.UserInfo;
import java.util.List;

/**
 *
 * @author Sampan-IT
 */
public class VideoDAOBuilder {

    private VideoDAO videoInfo;

    public VideoDAOBuilder() {
        videoInfo = new VideoDAO();
    }

    private String _id;
    private String videoId;
    private String userId;
    private String categoryId;
    private String url;
    private String createdOn;
    private UserInfo UserInfo;
    private Privacy privacy;
    private Privacy commentPrivacy;
    private List<Like> like;
    private List<Comment> comment;
    private List<Share> share;

    public VideoDAOBuilder set_id(String _id) {
        this._id = _id;
        return this;
    }

    public VideoDAOBuilder setVideoId(String videoId) {
        this.videoId = videoId;
        return this;
    }

    public VideoDAOBuilder setVideoInfo(VideoDAO videoInfo) {
        this.videoInfo = videoInfo;
        return this;
    }

    public VideoDAOBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public VideoDAOBuilder setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public VideoDAOBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public VideoDAOBuilder setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public VideoDAOBuilder setUserInfo(UserInfo UserInfo) {
        this.UserInfo = UserInfo;
        return this;
    }

    public VideoDAOBuilder setPrivacy(Privacy privacy) {
        this.privacy = privacy;
        return this;
    }

    public VideoDAOBuilder setCommentPrivacy(Privacy commentPrivacy) {
        this.commentPrivacy = commentPrivacy;
        return this;
    }

    public VideoDAOBuilder setLike(List<Like> like) {
        this.like = like;
        return this;
    }

    public VideoDAOBuilder setComment(List<Comment> comment) {
        this.comment = comment;
        return this;
    }

    public VideoDAOBuilder setShare(List<Share> share) {
        this.share = share;
        return this;
    }

    public VideoDAO build() {
        videoInfo.setVideoId(videoId);
        videoInfo.setUserId(userId);
        videoInfo.setUserInfo(UserInfo);
        videoInfo.setCategoryId(categoryId);
        videoInfo.setUrl(url);
        videoInfo.setComment(comment);
        videoInfo.setLike(like);
        videoInfo.setShare(share);
        videoInfo.setPrivacy(privacy);
        videoInfo.setCommentPrivacy(commentPrivacy);
        videoInfo.setCreatedOn(createdOn);
        return videoInfo;
    }

    public VideoDAO build(String daoContent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            videoInfo = mapper.readValue(daoContent, VideoDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return videoInfo;
    }
}
