/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

/**
 *
 * @author Sampan-IT
 */
public class ReferenceInfo {
    private String description;
    private List<Image> img;
    private UserInfo userInfo;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImg() {
        return img;
    }

    public void setImg(List<Image> img) {
        this.img = img;
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

    public static ReferenceInfo getRefInfo(String jsonContent) {
        ReferenceInfo refInfo = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            refInfo = mapper.readValue(jsonContent, ReferenceInfo.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return refInfo;
    }
    
}
