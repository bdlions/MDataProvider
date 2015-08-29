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
import com.shampan.db.collections.builder.RelationsDAOBuilder;
import com.shampan.db.collections.fragment.relation.RelationInfo;
import com.shampan.model.FriendModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sampan-IT
 */
public class RelationServiceTest {
   
    public void main() {
        MongoCollection<RelationsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELATIONS.toString(), RelationsDAO.class);
                RelationInfo formRelation = new RelationInfo();
        formRelation.setUserId("100157");
        formRelation.setFristName("Nazneen");
        formRelation.setLastName("Sultana");
        formRelation.setIsInitiated("1");
        
        RelationInfo toRelation = new RelationInfo();
        toRelation.setUserId("100105");
        toRelation.setIsInitiated("0");
        toRelation.setFristName("Alamgir");
        toRelation.setLastName("Kabir");

        List<RelationInfo> formRelationsList = new ArrayList<RelationInfo>();
        formRelationsList.add(formRelation);
        
        List<RelationInfo> toRelationsList = new ArrayList<RelationInfo>();
        toRelationsList.add(toRelation);


        RelationsDAO formRelationInfo = new RelationsDAOBuilder()
                .setUserId("100157")
                .setFriendList(toRelationsList)
                .build();

        RelationsDAO toRelationInfo = new RelationsDAOBuilder()
                .setUserId("100105")
                .setFriendList(formRelationsList)
                .build();

        System.out.print(formRelationInfo);
        System.out.print(toRelationInfo);
        String userId = "100157";
        String friendId = "100106";
        int limit = 3;
        int offset = 0;
        FriendModel ob = new FriendModel();
        String friendInfo = ob.addFriend(userId, friendId);
        System.out.println(ob.getFriendList(userId));
        

        



    }
}
