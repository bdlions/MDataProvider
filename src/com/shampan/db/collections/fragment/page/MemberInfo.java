/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment.page;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Sampan IT
 */
public class MemberInfo {

    private String userId;
    private String firstName;
    private String lastName;
    private String genderId;
    private String relationTypeId;
    private long createdOn;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public String getRelationTypeId() {
        return relationTypeId;
    }

    public void setRelationTypeId(String relationTypeId) {
        this.relationTypeId = relationTypeId;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }
    
    public static MemberInfo getMemberInfo(String jsonContent) {
        MemberInfo memberInfo = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            memberInfo = mapper.readValue(jsonContent, MemberInfo.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return memberInfo;
    }
}
