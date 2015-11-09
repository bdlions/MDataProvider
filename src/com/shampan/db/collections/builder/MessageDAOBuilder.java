/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.MessageDAO;
import com.shampan.db.collections.fragment.common.UserInfo;
import java.util.List;

/**
 *
 * @author Sampan IT
 */
public class MessageDAOBuilder {

    private MessageDAO message;

    public MessageDAOBuilder() {
        message = new MessageDAO();
    }
    private String _id;
    private String groupId;
    private String groupTitle;
    private List<UserInfo> userList;
    private String latestMessage;
    private String messageTime;

    public MessageDAOBuilder setMessage(MessageDAO message) {
        this.message = message;
        return this;

    }

    public MessageDAOBuilder set_id(String _id) {
        this._id = _id;
        return this;
    }

    public MessageDAOBuilder setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public MessageDAOBuilder setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
        return this;
    }

    public MessageDAOBuilder setUserList(List<UserInfo> userList) {
        this.userList = userList;
        return this;
    }

    public MessageDAOBuilder setLatestMessage(String latestMessage) {
        this.latestMessage = latestMessage;
        return this;
    }

    public MessageDAOBuilder setMessageTime(String messageTime) {
        this.messageTime = messageTime;
        return this;
    }

    public MessageDAO build() {
        message.setGroupId(groupId);
        message.setGroupTitle(groupTitle);
        message.setLatestMessage(latestMessage);
        message.setUserList(userList);
        message.setMessageTime(messageTime);
        return message;
    }

    public MessageDAO build(String daoContent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            message = mapper.readValue(daoContent, MessageDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return message;
    }

}
