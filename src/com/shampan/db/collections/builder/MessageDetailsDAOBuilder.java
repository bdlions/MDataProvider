/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.MessageDetailsDAO;
import com.shampan.db.collections.fragment.message.Messages;
import java.util.List;

/**
 *
 * @author Sampan IT
 */
public class MessageDetailsDAOBuilder {

    private MessageDetailsDAO messageDetails;

    public MessageDetailsDAOBuilder() {
        messageDetails = new MessageDetailsDAO();
    }
    private String _id;
    private String groupId;
    private List<Messages> messages;

    public MessageDetailsDAOBuilder setMessageDetails(MessageDetailsDAO messageDetails) {
        this.messageDetails = messageDetails;
        return this;
    }

    public MessageDetailsDAOBuilder setId(String _id) {
        this._id = _id;
        return this;
    }

    public MessageDetailsDAOBuilder setGroupId(String groupId) {
        this.groupId = groupId;
        return this;

    }

    public MessageDetailsDAOBuilder setMessages(List<Messages> messages) {
        this.messages = messages;
        return this;
    }

    public MessageDetailsDAO build() {
        messageDetails.setGroupId(groupId);
        messageDetails.setMessages(messages);
        return messageDetails;
    }

    public MessageDetailsDAO build(String daoContent) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            messageDetails = mapper.readValue(daoContent, MessageDetailsDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return messageDetails;
    }

}
