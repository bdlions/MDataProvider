package com.shampan.services;

import com.shampan.db.Collections;
import com.shampan.db.collections.RelationsDAO;
import com.shampan.model.FriendModel;

/**
 *
 * @author nazmul
 */
public class FriendService {

    private static FriendModel obj = new FriendModel();

    public static String addFriend(String userId, String friendId) {
        String response = obj.addFriend(userId, friendId);
        return response;
    }

    public  static String getFriendList(String userId) {
        String friendList = obj.getFriendList(userId);
        return friendList;
    }

    public static void FriendService() {

    }

    public static void addPendingRequest(String userId, String friendId) {

    }
}
