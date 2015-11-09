/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.fragment.status.Comment;

/**
 *
 * @author Sampan IT
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Group {
    private String groupId ;
    private String title ;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public static Group getGroup(String jsonContent) {
        Group groupInfo = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            groupInfo = mapper.readValue(jsonContent, Group.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return groupInfo;
    }
    
}
