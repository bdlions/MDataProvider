/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment.status;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Sampan-IT
 */
public class Share {

    private UserInfo userInfo;
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

     public static Share getStatusShare(String jsonContent) {
        Share shareInfo = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            shareInfo = mapper.readValue(jsonContent, Share.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return shareInfo;
    }

}
