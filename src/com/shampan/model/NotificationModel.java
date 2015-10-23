package com.shampan.model;

import com.shampan.db.collections.fragment.common.UserInfo;
import com.shampan.db.collections.fragment.notification.GeneralNotification;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author nazmul hasan
 */
public class NotificationModel {
    public NotificationModel()
    {
    
    }
    
    public String getNotificationCounter()
    {
        JSONObject resultEvent = new JSONObject();
        JSONObject counter = new JSONObject();
        counter.put("friend","2");
        counter.put("message","5");
        counter.put("general","11");
        resultEvent.put("notificationCounter",counter.toString());
        return resultEvent.toString();
    }
    
    public void addFriendNotification(String userId, String friendId)
    {
    
    }
    
    public void updateStatusFriendNotifications(String userId, String statusTypeId)
    {
    
    }
    
    public List<UserInfo> getFriendNotifications(String userId)
    {
        List<UserInfo> userList = new ArrayList<>();
        UserInfo user1 = new UserInfo();
        user1.setUserId("1");
        user1.setFirstName("First1");
        user1.setLastName("Last1");
        UserInfo user2 = new UserInfo();
        user2.setUserId("2");
        user2.setFirstName("First2");
        user2.setLastName("Last2");
        userList.add(user1);
        userList.add(user2);
        return userList;
    }
    
    public void deleteFriendNotification(String userId, String friendId)
    {
    
    }
    
    public void addGeneralNotificationStatusComment(String notificationData)
    {
    
    }
    public void addGeneralNotificationStatusLike(String notificationData)
    {
    
    }
    public void addGeneralNotificationStatusShare(String notificationData)
    {
    
    }
    
    public void updateStatusGeneralNotifications(String userId, String StatusTypeId)
    {
        
    }
    
    public List<GeneralNotification> getGeneralNotifications(String userId, String limit, String offset)
    {
        List<GeneralNotification> generalNotifications = new ArrayList<>();
        
        GeneralNotification generalNotification1 = new GeneralNotification();
        generalNotification1.setTypeId("1");
        generalNotification1.setReferenceId("1");
        List<UserInfo> userList = new ArrayList<>();
        UserInfo user1 = new UserInfo();
        user1.setUserId("1");
        user1.setFirstName("First1");
        user1.setLastName("Last1");
        UserInfo user2 = new UserInfo();
        user2.setUserId("2");
        user2.setFirstName("First2");
        user2.setLastName("Last2");
        userList.add(user1);
        userList.add(user2);
        generalNotification1.setUserList(userList);
        generalNotifications.add(generalNotification1);
        
        GeneralNotification generalNotification2 = new GeneralNotification();
        generalNotification2.setTypeId("2");
        generalNotification2.setReferenceId("2");
        List<UserInfo> userList2 = new ArrayList<>();
        UserInfo user3 = new UserInfo();
        user3.setUserId("3");
        user3.setFirstName("First3");
        user3.setLastName("Last3");
        UserInfo user4 = new UserInfo();
        user4.setUserId("4");
        user4.setFirstName("First4");
        user4.setLastName("Last4");
        UserInfo user5 = new UserInfo();
        user5.setUserId("5");
        user5.setFirstName("First5");
        user5.setLastName("Last5");
        userList2.add(user3);
        userList2.add(user4);
        userList2.add(user5);
        generalNotification2.setUserList(userList2);
        generalNotifications.add(generalNotification2);
        
        return generalNotifications;
        
    }
}
