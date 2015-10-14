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

    public static String addRequest(String userId, String friendId,String typeId ) {
        String response = obj.addRequest(userId, friendId,typeId);
        return response;
    }
    public static String changeRelationShipStatus(String userId, String friendId, String statusType) {
        String response = obj.changeRelationShipStatus(userId, friendId, statusType);
        return response;
    }
    public static String deleteRequest(String userId, String friendId) {
        String response = obj.deleteRequest(userId, friendId);
        return response;
    }

    public  static String getFriendList(String userId,int offset,int limit, String typeId) {
        String friendList = obj.getFriendList(userId,offset,limit,typeId);
        return friendList;
    }
    public  static String getRelationShipStatus(String userId,String friendId) {
        String relationShipStatus = obj.getRelationShipStatus(userId,friendId);
        return relationShipStatus;
    }

    public static void FriendService() {

    }

    public static void addPendingRequest(String userId, String friendId) {

    }
}
