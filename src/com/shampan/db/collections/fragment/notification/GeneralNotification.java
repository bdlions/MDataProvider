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
    private List<UserInfo> userList = new ArrayList<>();
    private String createdOn;
    private String modifiedOn;

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

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public List<UserInfo> getUserList() {
        return userList;
    }

    public void setUserList(List<UserInfo> userList) {
        this.userList = userList;
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
