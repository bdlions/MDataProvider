/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.PagePhotoDAO;
import com.shampan.db.collections.fragment.common.Comment;
import com.shampan.db.collections.fragment.common.Like;
import com.shampan.db.collections.fragment.common.Share;
import java.util.List;

/**
 *
 * @author Sampan IT
 */
public class PagePhotoDAOBuilder {

    PagePhotoDAO photoInfo;

    public PagePhotoDAOBuilder() {
        photoInfo = new PagePhotoDAO();
    }

    private String _id;
    private String photoId;
    private String albumId;
    private String pageId;
    private String image;
    private String description;
    private String categoryId;
    private int createdOn;
    private int modifiedOn;
    private List<Like> like;
    private List<Comment> comment;
    private List<Share> share;
    private String referenceId;

    public PagePhotoDAOBuilder setId(String _id) {
        this._id = _id;
        return this;
    }

    public PagePhotoDAOBuilder setPhotoId(String photoId) {
        this.photoId = photoId;
        return this;
    }

    public PagePhotoDAOBuilder setAlbumId(String albumId) {
        this.albumId = albumId;
        return this;
    }

    public PagePhotoDAOBuilder setPageId(String pageId) {
        this.pageId = pageId;
        return this;
    }

    public PagePhotoDAOBuilder setImage(String image) {
        this.image = image;
        return this;
    }

    public PagePhotoDAOBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public PagePhotoDAOBuilder setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public PagePhotoDAOBuilder setCreatedOn(int createdOn) {
        this.createdOn = createdOn;
        return this;

    }

    public PagePhotoDAOBuilder setModifiedOn(int modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;

    }

    public PagePhotoDAOBuilder setLike(List<Like> like) {
        this.like = like;
        return this;

    }

    public PagePhotoDAOBuilder setComment(List<Comment> comment) {
        this.comment = comment;
        return this;

    }

    public PagePhotoDAOBuilder setShare(List<Share> share) {
        this.share = share;
        return this;

    }

    public PagePhotoDAOBuilder setReferenceId(String referenceId) {
        this.referenceId = referenceId;
        return this;

    }

    public PagePhotoDAO build() {
        photoInfo.setAlbumId(albumId);
        photoInfo.setPhotoId(photoId);
        photoInfo.setCategoryId(categoryId);
        photoInfo.setImage(image);
        photoInfo.setDescription(description);
        photoInfo.setComment(comment);
        photoInfo.setLike(like);
        photoInfo.setModifiedOn(modifiedOn);
        photoInfo.setShare(share);
        photoInfo.setPageId(pageId);
        photoInfo.setReferenceId(referenceId);
        return photoInfo;
    }

    public PagePhotoDAO build(String daoContent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            photoInfo = mapper.readValue(daoContent, PagePhotoDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return photoInfo;
    }

}
