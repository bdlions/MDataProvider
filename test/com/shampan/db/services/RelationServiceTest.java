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
import com.shampan.model.RelationModel;
import com.shampan.util.PropertyProvider;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.internal.runners.TestClass;

/**
 *
 * @author Sampan-IT
 */
public class RelationServiceTest {

    RelationModel friendObj = new RelationModel();
    String userId = "dRmgLnlhu8OTSbY";
    String friendId = "hcZrdhteaIeUYrP";

//    @Test
    public void addUser() {

        UserDAO userInfo = new UserDAOBuilder()
                .setFirstName("Salma")
                .setLastName("Hasan")
                .setUserId("6")
                .build();
//        System.out.println(friendObj.addUser(userInfo.toString()));

    }

//    @Test
    public void getUserInfo() {
//        System.out.println(friendObj.getUserInfo("2"));

    }

//    @Test
    public void addRequest() {
        PropertyProvider.add("com.shampan.properties/relations");
        String typeId = PropertyProvider.get("RELATION_TYPE_PENDING_ID");
        friendObj.addFriend(userId, friendId);

    }

//    @Test
    public void getRelationShipStatus() {

        System.out.println(friendObj.getRelationInfo(userId, friendId));
    }

//    @Test
    public void deleteRequest() {

//        System.out.println(friendObj.deleteRequest(userId, friendId));
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
//        System.out.println(friendObj.getFriendInfo(userId, friendId).toString());

    }

//    @Test
    public void approveRequest() {
        PropertyProvider.add("com.shampan.properties/relations");
        String typeId = PropertyProvider.get("FriendTypeId");
//        System.out.println(friendObj.changeRelationShipStatus(userId, friendId,typeId));

    }

    @Test
    public void getFriendList() {
        PropertyProvider.add("com.shampan.properties/relations");
        String typeId = "1";
        int offset = 0;
        int limit = 5;
        System.out.println(friendObj.getRelationList(userId, "1", offset, limit).toString());

    }
//    @Test

    public void getTestFriendList() {
        PropertyProvider.add("com.shampan.properties/relations");
        String typeId = PropertyProvider.get("RELATION_TYPE_PENDING_ID");
        int offset = 0;
        int limit = 5;
//        friendObj.getFriendList(userId, offset, limit, typeId);

    }

}
