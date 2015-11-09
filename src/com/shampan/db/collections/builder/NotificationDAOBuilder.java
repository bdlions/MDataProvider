/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.collections.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampan.db.collections.NotificationDAO;
import com.shampan.db.collections.fragment.notification.FriendNotification;
import com.shampan.db.collections.fragment.notification.GeneralNotification;
import com.shampan.db.collections.fragment.notification.MessageNotification;
import java.util.List;

/**
 *
 * @author Sampan IT
 */
public class NotificationDAOBuilder {

    private NotificationDAO notification;

    public NotificationDAOBuilder() {
        notification = new NotificationDAO();
    }
    private String _id;
    private String userId;
    private List<FriendNotification> friendNotifications;
    private List<MessageNotification> messageNotifications;
    private List<GeneralNotification> generalNotifications;

    public NotificationDAOBuilder setId(String _id) {
        this._id = _id;
        return this;
    }

    public NotificationDAOBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public NotificationDAOBuilder setFriendNotifications(List<FriendNotification> friendNotifications) {
        this.friendNotifications = friendNotifications;
        return this;
    }

    public NotificationDAOBuilder setMessageNotifications(List<MessageNotification> messageNotifications) {
        this.messageNotifications = messageNotifications;
        return this;
    }

    public NotificationDAOBuilder setGeneralNotifications(List<GeneralNotification> generalNotifications) {
        this.generalNotifications = generalNotifications;
        return this;
    }
    
    public NotificationDAO build(){
        notification.set_id(_id);
        notification.setUserId(userId);
        notification.setFriendNotifications(friendNotifications);
        notification.setMessageNotifications(messageNotifications);
        notification.setGeneralNotifications(generalNotifications);
        return notification;
    }
    
    public NotificationDAO build(String daoContent){
          ObjectMapper mapper = new ObjectMapper();
        try {
            notification = mapper.readValue(daoContent, NotificationDAO.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return notification;
    }

}
