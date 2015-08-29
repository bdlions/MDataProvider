/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.PhotoDAO;
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
public class PhotoDAOBuilder {

    private PhotoDAO photoInfo;

    public PhotoDAOBuilder() {
        photoInfo = new PhotoDAO();

    }
    private String _id;
    private String photoId;
    private String albumId;
    private String image;
    private int categoryId;
    private String createdOn;
    private String modifiedOn;
    private Privacy privacy;
    private Privacy commentPrivacy;
    private List<Like> like;
    private List<Comment> comment;
    private List<Share> share;

    public PhotoDAOBuilder setPhotoId(String photoId) {
        this.photoId = photoId;
        return this;
    }

    public PhotoDAOBuilder setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public PhotoDAOBuilder setId(String _id) {
        this._id = _id;
        return this;
    }

    public PhotoDAOBuilder setAlbumId(String albumId) {
        this.albumId = albumId;
        return this;
    }

    public PhotoDAOBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public PhotoDAOBuilder setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public PhotoDAOBuilder setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public PhotoDAOBuilder setPrivacy(Privacy privacy) {
        this.privacy = privacy;
        return this;
    }

    public PhotoDAOBuilder setCommentPrivacy(Privacy commentPrivacy) {
        this.commentPrivacy = commentPrivacy;
        return this;
    }

    public PhotoDAOBuilder setLike(List<Like> like) {
        this.like = like;
        return this;
    }

    public PhotoDAOBuilder setComment(List<Comment> comment) {
        this.comment = comment;
        return this;
    }

    public PhotoDAOBuilder setShare(List<Share> share) {
        this.share = share;
        return this;
    }

    public PhotoDAO build() {
        photoInfo.setAlbumId(albumId);
        photoInfo.setPhotoId(photoId);
        photoInfo.setCategoryId(categoryId);
        photoInfo.setImage(image);
        photoInfo.setComment(comment);
        photoInfo.setCommentPrivacy(commentPrivacy);
        photoInfo.setLike(like);
        photoInfo.setModifiedOn(modifiedOn);
        photoInfo.setPrivacy(privacy);
        photoInfo.setShare(share);
        return photoInfo;
    }

    public PhotoDAO build(String daoContent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            photoInfo = mapper.readValue(daoContent, PhotoDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return photoInfo;
    }

}