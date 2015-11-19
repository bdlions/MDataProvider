package com.shampan.db.collections.fragment.notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.fragment.common.UserInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nazmul hasan
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralNotification {
    private String statusId;
    private String typeId;
    private String referenceId;
    private UserInfo referenceUserInfo ;
    private List<UserInfo> userList = new ArrayList<>();
    private long createdOn;
    private long modifiedOn;

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public UserInfo getReferenceUserInfo() {
        return referenceUserInfo;
    }

    public void setReferenceUserInfo(UserInfo referenceUserInfo) {
        this.referenceUserInfo = referenceUserInfo;
    }
    

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public List<UserInfo> getUserList() {
        return userList;
    }

    public void setUserList(List<UserInfo> userList) {
        this.userList = userList;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public long getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(long modifiedOn) {
        this.modifiedOn = modifiedOn;
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
}
