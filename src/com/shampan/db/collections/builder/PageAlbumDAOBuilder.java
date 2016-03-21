/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.AlbumDAO;
import com.shampan.db.collections.PageAlbumDAO;
import com.shampan.db.collections.fragment.common.Comment;
import com.shampan.db.collections.fragment.common.Like;
import com.shampan.db.collections.fragment.common.Privacy;
import com.shampan.db.collections.fragment.common.Share;
import com.shampan.db.collections.fragment.common.UserInfo;
import com.shampan.db.collections.fragment.page.PageInfo;
import java.util.List;

/**
 *
 * @author Sampan-IT
 */
public class PageAlbumDAOBuilder {

    private PageAlbumDAO pageAlbum;

    public PageAlbumDAOBuilder() {
        pageAlbum = new PageAlbumDAO();
    }
    private String _id;
    private String albumId;
    private String pageId;
    private String title;
    private String description;
    private int createdOn;
    private int modifiedOn;
    private String photoId;
    private String defaultImg;
    private int totalImg;
    private PageInfo pageInfo;
    private List like;
    private List comment;
    private List share;
    private String referenceId;

    public PageAlbumDAOBuilder setId(String _id) {
        this._id = _id;
        return this;
    }

    public PageAlbumDAOBuilder setTotalImg(int totalImg) {
        this.totalImg = totalImg;
        return this;
    }

    public PageAlbumDAOBuilder setAlbumId(String albumId) {
        this.albumId = albumId;
        return this;
    }

    public PageAlbumDAOBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public PageAlbumDAOBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public PageAlbumDAOBuilder setPhotoId(String photoId) {
        this.photoId = photoId;
        return this;
    }

    public void setCreatedOn(int createdOn) {
        this.createdOn = createdOn;
    }

    public void setModifiedOn(int modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public PageAlbumDAOBuilder setDefaultImg(String defaultImg) {
        this.defaultImg = defaultImg;
        return this;
    }

    public PageAlbumDAOBuilder setLike(List<Like> like) {
        this.like = like;
        return this;
    }

    public PageAlbumDAOBuilder setComment(List<Comment> comment) {
        this.comment = comment;
        return this;
    }

    public PageAlbumDAOBuilder setReferenceId(String referenceId) {
        this.referenceId = referenceId;
        return this;
    }

    public PageAlbumDAOBuilder setShare(List<Share> share) {
        this.share = share;
        return this;
    }

    public PageAlbumDAOBuilder setPageId(String pageId) {
        this.pageId = pageId;
        return this;
    }

    public PageAlbumDAOBuilder setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
        return this;
    }

    public PageAlbumDAO build() {
        pageAlbum.setAlbumId(albumId);
        pageAlbum.setPageId(pageId);
        pageAlbum.setTitle(title);
        pageAlbum.setDescription(description);
        pageAlbum.setPageInfo(pageInfo);
        pageAlbum.setDefaultImg(defaultImg);
        pageAlbum.setPhotoId(photoId);
        pageAlbum.setTotalImg(totalImg);
        pageAlbum.setComment(comment);
        pageAlbum.setLike(like);
        pageAlbum.setShare(share);
        pageAlbum.setCreatedOn(createdOn);
        pageAlbum.setModifiedOn(modifiedOn);
        pageAlbum.setReferenceId(referenceId);

        return pageAlbum;
    }

    public PageAlbumDAO build(String daoContent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            pageAlbum = mapper.readValue(daoContent, PageAlbumDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pageAlbum;
    }

}
