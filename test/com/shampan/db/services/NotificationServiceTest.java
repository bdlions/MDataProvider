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
    String userId = "55Lj6k4iZReT4ck";
    String friendId = "55Lj6k4iZReT4ck";
//    String friendId = "9nSEiMgzieo1O4K";
    String referenceId = "2";

//    @Test
    public void addFriendNotification() {
        notificationModel.addFriendNotification(userId, friendId);

    }
//    @Test
    public void getFriendNotifications() {
        notificationModel.getFriendNotifications(userId);

    }
    
//    @Test
    public void getGeneralNotifications() {
        int offset =0;
        int limit =0;
        System.out.println(notificationModel.getGeneralNotifications(userId,offset,limit).toString());

    }
//    @Test
    public void updateStatusGeneralNotifications() {
       String statusTypeId = "";
        notificationModel.updateStatusGeneralNotifications(userId,statusTypeId);

    }

//    @Test
    public void deleteFriendNotification() {
        notificationModel.deleteFriendNotification(userId, friendId);

    }
//    @Test
    public void getNotificationCounter() {
        System.out.println( notificationModel.getNotificationCounter(userId));

    }
    
//    @Test

    public void addGeneralNotificationStatusLike() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("u1");
        userInfo.setFirstName("Alamgir");
        userInfo.setLastName("Kabir");
        notificationModel.addGeneralNotificationStatusLike("u1", referenceId, userInfo.toString());

    }

//    @Test
    public void addGeneralNotificationStatusComment() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("u4");
        userInfo.setFirstName("Rashida");
        userInfo.setLastName("Sultana");
        notificationModel.addGeneralNotificationStatusComment("u1", referenceId, userInfo.toString());

    }
//    @Test
    public void testMongoSql(){
    notificationModel.testMongoSql(userId);
    }
}
