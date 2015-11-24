/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.AlbumDAO;
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
public class AlbumDAOBuilder {

    private AlbumDAO userAlbum;

    public AlbumDAOBuilder() {
        userAlbum = new AlbumDAO();
    }
    private String _id;
    private String albumId;
    private String userId;
    private String title;
    private String description;
    private int createdOn;
    private int modifiedOn;
    private String photoId;
    private String defaultImg;
    private int totalImg;
    private UserInfo userInfo;
    private Privacy privacy;
    private Privacy commentPrivacy;
    private List like;
    private List comment;
    private List share;

    public AlbumDAOBuilder setId(String _id) {
        this._id = _id;
        return this;
    }

    public AlbumDAOBuilder setTotalImg(int totalImg) {
        this.totalImg = totalImg;
        return this;
    }

    public AlbumDAOBuilder setAlbumId(String albumId) {
        this.albumId = albumId;
        return this;
    }

    public AlbumDAOBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public AlbumDAOBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public AlbumDAOBuilder setDescription(String description) {
        this.description = description;
        return this;
    }
    
    public AlbumDAOBuilder setPhotoId(String photoId) {
        this.photoId = photoId;
        return this;
    }

    public void setCreatedOn(int createdOn) {
        this.createdOn = createdOn;
    }

    public void setModifiedOn(int modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public AlbumDAOBuilder setDefaultImg(String defaultImg) {
        this.defaultImg = defaultImg;
        return this;
    }


    public AlbumDAOBuilder setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    public AlbumDAOBuilder setPrivacy(Privacy privacy) {
        this.privacy = privacy;
        return this;
    }

    public AlbumDAOBuilder setCommentPrivacy(Privacy commentPrivacy) {
        this.commentPrivacy = commentPrivacy;
        return this;
    }

    public AlbumDAOBuilder setLike(List<Like> like) {
        this.like = like;
        return this;
    }

    public AlbumDAOBuilder setComment(List<Comment> comment) {
        this.comment = comment;
        return this;
    }

    public AlbumDAOBuilder setShare(List<Share> share) {
        this.share = share;
        return this;
    }

 
    public AlbumDAO build() {
        userAlbum.setAlbumId(albumId);
        userAlbum.setUserId(userId);
        userAlbum.setTitle(title);
        userAlbum.setDescription(description);
        userAlbum.setUserInfo(userInfo);
        userAlbum.setDefaultImg(defaultImg);
        userAlbum.setPhotoId(photoId);
        userAlbum.setTotalImg(totalImg);
        userAlbum.setComment(comment);
        userAlbum.setCommentPrivacy(commentPrivacy);
        userAlbum.setLike(like);
        userAlbum.setShare(share);
        userAlbum.setCreatedOn(createdOn);
        userAlbum.setModifiedOn(modifiedOn);

        return userAlbum;
    }

    public AlbumDAO build(String daoContent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            userAlbum = mapper.readValue(daoContent, AlbumDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userAlbum;
    }

    
}
