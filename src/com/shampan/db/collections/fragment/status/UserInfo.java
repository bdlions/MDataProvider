/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.fragment.profile.WorkPlace;

/**
 *
 * @author Sampan-IT
 */
public class UserInfo {
    private String userId ;
    private String firstName;
    private String lastName;

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

    public static UserInfo getUserInfo(String jsonContent) {
        UserInfo userInfo = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            userInfo = mapper.readValue(jsonContent, UserInfo.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userInfo;
    }
    
}
