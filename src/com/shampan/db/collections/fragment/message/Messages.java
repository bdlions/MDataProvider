/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.fragment.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.fragment.common.UserInfo;

/**
 *
 * @author Sampan IT
 */
public class Messages {

    private String message;
    private String sentTime;
    private String receiveTime;
    private String status;
    private UserInfo senderInfo;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentTime() {
        return sentTime;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserInfo getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(UserInfo senderInfo) {
        this.senderInfo = senderInfo;
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
