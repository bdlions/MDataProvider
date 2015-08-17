/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

/**
 *
 * @author Sampan-IT
 */
public class Like {

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

    public static Like getLikeInfo(String jsonContent) {
        Like likeInfo = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            likeInfo = mapper.readValue(jsonContent, Like.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return likeInfo;
    }

}
