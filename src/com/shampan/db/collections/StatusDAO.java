/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.fragment.status.Comment;
import com.shampan.db.collections.fragment.status.Image;
import com.shampan.db.collections.fragment.status.Like;
import com.shampan.db.collections.fragment.status.Privacy;
import com.shampan.db.collections.fragment.status.ReferenceInfo;
import com.shampan.db.collections.fragment.status.ReferenceList;
import com.shampan.db.collections.fragment.status.Share;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import com.shampan.db.collections.fragment.status.UserInfo;
import java.util.List;

/**
 *
 * @author Sampan-IT
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusDAO implements Bson {

    private String _id;
    private String statusId;
    private String userId;
    private String mappingId;
    private String statusTypeId;
    private String description;
    private List<Image> image;
    private String createdOn;
    private String modifiedOn;
    private UserInfo userInfo;
    private ReferenceInfo referenceInfo;
    private List<ReferenceList> referenceList;
    private Privacy privacy;
    private List<Like> like;
    private List<Comment> comment;
    private List<Share> share;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMappingId() {
        return mappingId;
    }

    public void setMappingId(String mappingId) {
        this.mappingId = mappingId;
    }

    public String getStatusTypeId() {
        return statusTypeId;
    }

    public void setStatusTypeId(String statusTypeId) {
        this.statusTypeId = statusTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public ReferenceInfo getReferenceInfo() {
        return referenceInfo;
    }

    public void setReferenceInfo(ReferenceInfo referenceInfo) {
        this.referenceInfo = referenceInfo;
    }

    public List<ReferenceList> getReferenceList() {
        return referenceList;
    }

    public void setReferenceList(List<ReferenceList> referenceList) {
        this.referenceList = referenceList;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    public List<Like> getLike() {
        return like;
    }

    public void setLike(List<Like> like) {
        this.like = like;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public List<Share> getShare() {
        return share;
    }

    public void setShare(List<Share> share) {
        this.share = share;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
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

    @Override
    public <C> BsonDocument toBsonDocument(final Class<C> documentClass, final CodecRegistry codecRegistry) {
        return new BsonDocumentWrapper<StatusDAO>(this, codecRegistry.get(StatusDAO.class));
    }

}
