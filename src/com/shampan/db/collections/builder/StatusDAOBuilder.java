/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.StatusDAO;
import com.shampan.db.collections.fragment.page.PageInfo;
import com.shampan.db.collections.fragment.status.Comment;
import com.shampan.db.collections.fragment.status.Image;
import com.shampan.db.collections.fragment.status.Like;
import com.shampan.db.collections.fragment.status.Privacy;
import com.shampan.db.collections.fragment.status.ReferenceInfo;
import com.shampan.db.collections.fragment.status.ReferenceList;
import com.shampan.db.collections.fragment.status.Share;
import com.shampan.db.collections.fragment.status.UserInfo;
import java.util.List;

/**
 *
 * @author Sampan-IT
 */
public class StatusDAOBuilder {

    private StatusDAO status;

    public StatusDAOBuilder() {
        status = new StatusDAO();
    }

    private String _id;
    private String userId;
    private String statusId;
    private String mappingId;
    private String statusTypeId;
    private String description;
    private int createdOn;
    private int modifiedOn;
    private UserInfo userInfo;
    private PageInfo pageInfo;
    private List<Image> images;
    private ReferenceInfo referenceInfo;
    private List<ReferenceList> referenceList;
    private Privacy privacy;
    private List<Like> like;
    private List<Comment> comment;
    private List<Share> share;

    public StatusDAO getStatus() {
        return status;
    }

    public StatusDAOBuilder setStatus(StatusDAO status) {
        this.status = status;
        return this;
    }

    public StatusDAOBuilder setId(String _id) {
        this._id = _id;
        return this;
    }

    public StatusDAOBuilder setStatusId(String statusId) {
        this.statusId = statusId;
        return this;
    }

    public StatusDAOBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public StatusDAOBuilder setMappingId(String mappingId) {
        this.mappingId = mappingId;
        return this;
    }

    public StatusDAOBuilder setStatusTypeId(String statusTypeId) {
        this.statusTypeId = statusTypeId;
        return this;
    }

    public StatusDAOBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public StatusDAOBuilder setCreatedOn(int createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public StatusDAOBuilder setModifiedOn(int modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public StatusDAOBuilder setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
        return this;
    }

    public StatusDAOBuilder setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    public StatusDAOBuilder setReferenceInfo(ReferenceInfo referenceInfo) {
        this.referenceInfo = referenceInfo;
        return this;
    }

    public StatusDAOBuilder setReferenceList(List<ReferenceList> referenceList) {
        this.referenceList = referenceList;
        return this;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public StatusDAOBuilder setPrivacy(Privacy privacy) {
        this.privacy = privacy;
        return this;
    }

    public StatusDAOBuilder setLike(List<Like> like) {
        this.like = like;
        return this;
    }

    public StatusDAOBuilder setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public StatusDAOBuilder setComment(List<Comment> comment) {
        this.comment = comment;
        return this;
    }

    public StatusDAOBuilder setShare(List<Share> share) {
        this.share = share;
        return this;
    }

    public StatusDAO build() {
        status.set_id(_id);
        status.setUserId(userId);
        status.setStatusId(statusId);
        status.setMappingId(mappingId);
        status.setStatusTypeId(statusTypeId);
        status.setDescription(description);
        status.setCreatedOn(createdOn);
        status.setModifiedOn(modifiedOn);
        status.setUserInfo(userInfo);
        status.setReferenceInfo(referenceInfo);
        status.setReferenceList(referenceList);
        status.setPrivacy(privacy);
        status.setLike(like);
        status.setComment(comment);
        status.setShare(share);
        status.setImages(images);
        status.setPageInfo(pageInfo);
        return status;
    }

    public StatusDAO build(String daoContent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            status = mapper.readValue(daoContent, StatusDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return status;
    }

}
