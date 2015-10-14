/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.services;

import com.mongodb.client.MongoCollection;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.RelationsDAO;
import com.shampan.db.collections.UserDAO;
import com.shampan.db.collections.builder.RelationsDAOBuilder;
import com.shampan.db.collections.builder.UserDAOBuilder;
import com.shampan.db.collections.fragment.relation.RelationInfo;
import com.shampan.model.FriendModel;
import com.shampan.util.PropertyProvider;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Sampan-IT
 */
public class RelationServiceTest {

    FriendModel friendObj = new FriendModel();
    String userId = "2";
    String friendId = "3";

//    @Test
    public void addUser() {

        UserDAO userInfo = new UserDAOBuilder()
                .setFirstName("Shemin")
                .setLastName("Haque")
                .setUserId("4")
                .build();
        System.out.println(friendObj.addUser(userInfo.toString()));

    }

//    @Test
    public void getUserInfo() {
        System.out.println(friendObj.getUserInfo("2"));

    }

//    @Test
    public void addRequest() {
        PropertyProvider.add("com.shampan.properties/relations");
        String typeId = PropertyProvider.get("FriendTypeId");
        friendObj.addRequest(userId, friendId, typeId);

    }

//    @Test
    public void getRelationShipStatus() {

        System.out.println(friendObj.getRelationShipStatus(userId, friendId));

    }
//    @Test

    public void deleteRequest() {

        System.out.println(friendObj.deleteRequest(userId, friendId));

    }

//    @Test
    public void blockFriend() {

        System.out.println(friendObj.blockFriend(userId, friendId));

    }
//    @Test

    public void blockNonFriend() {

        System.out.println(friendObj.blockNonFriend(userId, friendId));

    }

//    @Test
    public void getFriendInfo() {
        System.out.println(friendObj.getFriendInfo(userId, friendId).toString());

    }

//    @Test
    public void approveRequest() {
        PropertyProvider.add("com.shampan.properties/relations");
        String typeId = PropertyProvider.get("FriendTypeId");
        System.out.println(friendObj.changeRelationShipStatus(userId, friendId,typeId));

    }

    @Test
    public void getFriendList() {
        PropertyProvider.add("com.shampan.properties/relations");
        String typeId = PropertyProvider.get("FriendTypeId");
        int offset = 1;
        int limit = 2;
        friendObj.getFriendList(userId, offset, limit, typeId);

    }
//    @Test
    public void getTestFriendList() {
        PropertyProvider.add("com.shampan.properties/relations");
        String typeId = PropertyProvider.get("FriendTypeId");
        int offset = 0;
        int limit = 5;
        friendObj.getTestFriendList(userId, offset, limit, typeId);

    }

}
