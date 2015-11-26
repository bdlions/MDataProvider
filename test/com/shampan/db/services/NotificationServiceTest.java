/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.services;

import com.shampan.db.collections.NotificationDAO;
import com.shampan.db.collections.builder.NotificationDAOBuilder;
import com.shampan.db.collections.fragment.common.UserInfo;
import com.shampan.db.collections.fragment.notification.FriendNotification;
import com.shampan.model.NotificationModel;
import com.shampan.util.PropertyProvider;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Sampan IT
 */
public class NotificationServiceTest {

    NotificationModel notificationModel = new NotificationModel();
    String userId = "2Q52DbDnqKEiSCn";
    String friendId = "WPkbWVADuhT8Vmj";
//    String friendId = "9nSEiMgzieo1O4K";
    String referenceId = "1";

//    @Test
    public void addFriendNotification() {
        notificationModel.addFriendPendingNotification(userId, friendId);

    }
//    @Test
    public void addFriendAcceptNotification() {
        notificationModel.addFriendAcceptNotification(userId, friendId);

    }
//    @Test

    public void getFriendNotifications() {
        System.out.println( notificationModel.getFriendNotifications(userId));

    }

//    @Test
    public void getGeneralNotifications() {
        int offset = 0;
        int limit = 10;
        System.out.println(notificationModel.getGeneralNotifications(userId, offset, limit).toString());

    }
//    @Test

    public void updateStatusGeneralNotifications() {
        String statusTypeId = "";
        notificationModel.updateStatusGeneralNotifications(userId, statusTypeId);

    }

//    @Test
    public void deleteFriendNotification() {
        notificationModel.deleteFriendNotification(friendId, userId);

    }
//    @Test

    public void getNotificationCounter() {
        System.out.println(notificationModel.getNotificationCounter("u2"));

    }

    @Test
    public void addGeneralNotificationStatusLike() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("u3");
        userInfo.setFirstName("Rashida");
        userInfo.setLastName("Sultana");

        notificationModel.addGeneralNotificationStatusLike("u1", referenceId, userInfo.toString());

    }

//    @Test
    public void addGeneralNotificationStatusComment() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("u1");
        userInfo.setFirstName("Rashida");
        userInfo.setLastName("Sultana");
        UserInfo refuserInfo = new UserInfo();
        refuserInfo.setUserId("u1");
        refuserInfo.setFirstName("Alamgir");
        refuserInfo.setLastName("Kabir");
        notificationModel.addGeneralNotificationStatusComment(refuserInfo.toString(), referenceId, userInfo.toString());

    }
    
//    @Test

    public void updateStatusFriendNotifications() {
        notificationModel.updateStatusFriendNotifications(userId);
    }
//    @Test

    public void testMongoSql() {
        notificationModel.testMongoSql(userId);
    }
}
