/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.services;

import com.mongodb.client.MongoCollection;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.RelationsDAO;
import com.shampan.db.collections.builder.RelationsDAOBuilder;
import com.shampan.db.collections.fragment.relations.RelationInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sampan-IT
 */
public class RelationServiceTest {
   
    public void main() {
        DBConnection.getInstance().getConnection();
        MongoCollection<RelationsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection("user_profiles", RelationsDAO.class);
        
        RelationInfo relationList = new RelationInfo();
        relationList.setUserId("100157");
        relationList.setIsInitiated("1");
        List<RelationInfo>relationsList = new ArrayList<RelationInfo>();
        relationsList.add(relationList);
        RelationsDAO relationInfo = new RelationsDAOBuilder()
                .setUserId("100157")
                .setFriendList(relationsList)
                .build();

        System.out.print(relationInfo);


    }
}
