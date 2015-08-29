package com.shampan.db.collections.fragment.relation;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author nazmul hasan
 */
public class RelationInfo {

    private String userId;
    private String fristName;
    private String lastName;
    private String isInitiated;
    private String createdOn;

    public RelationInfo() {

    }

    public String getFristName() {
        return fristName;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsInitiated() {
        return isInitiated;
    }

    public void setIsInitiated(String isInitiated) {
        this.isInitiated = isInitiated;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
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
