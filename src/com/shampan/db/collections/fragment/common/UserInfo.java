package com.shampan.db.collections.fragment.common;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author nazmul hasan
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

    public static UserInfo getuserInfo(String jsonContent) {
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
