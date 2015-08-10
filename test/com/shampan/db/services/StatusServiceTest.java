/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.services;

import com.mongodb.client.MongoCollection;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.StatusDAO;
import com.shampan.db.collections.builder.StatusDAOBuilder;
import com.shampan.db.collections.fragment.status.Comment;
import com.shampan.db.collections.fragment.status.Like;
import com.shampan.db.collections.fragment.status.ReferenceInfo;
import com.shampan.db.collections.fragment.status.ReferenceList;
import com.shampan.db.collections.fragment.status.Share;
import com.shampan.db.collections.fragment.status.UserInfo;
import com.shampan.model.StatusModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sampan-IT
 */
public class StatusServiceTest {
    
    
      public static void main(String[] args) {
        DBConnection.getInstance().getConnection();
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        String userId = "100157";
        String statusId = "1";
        UserInfo userInfo = new UserInfo();
        userInfo.setFristName("Alamgir");
        userInfo.setLastName("Kabir");
        userInfo.setUserId(userId);
        
        UserInfo rUserInfo = new UserInfo();
        rUserInfo.setFristName("Nazmul");
        rUserInfo.setLastName("Hasan");
        rUserInfo.setUserId("100105");
        
        ReferenceInfo referenceInfo = new ReferenceInfo();
        referenceInfo.setDescription("I Like this Invention");
        referenceInfo.setImg("shemin.jpg");
        referenceInfo.setUserInfo(rUserInfo)
                ;
//        Like statusLikeInfo = new Like();
//        statusLikeInfo.setUserInfo(rUserInfo);
        
        Comment statusCommentInfo = new Comment();
        statusCommentInfo.setDescription("Thank you !!");
        statusCommentInfo.setUserInfo(userInfo);
        
        Share shareShareInfo = new Share();
//        shareShareInfo.setShareCounter(statusId);
        shareShareInfo.setUserInfo(rUserInfo);
        
        ReferenceList refStatusIdList = new ReferenceList();
        refStatusIdList.setStatusId(statusId);
        
        List<ReferenceList> refStatusList = new ArrayList<ReferenceList>();
        refStatusList.add(refStatusIdList);

        StatusDAO satusInfo = new StatusDAOBuilder()
                .setStatusId(statusId)
                .setUserId(userId)
                .setUserInfo(userInfo)
                .setMappingId(userId)
                .setDescription("this is a wounderful invention By scientist Shemin of NASA")
                .setStatusTypeId("1")
                .setReferenceInfo(referenceInfo)
                .setReferenceList(refStatusList)
                .build();
//        System.out.println("test" + satusInfo.toString());
//        Document projectionQuary = new Document();
//        projectionQuary.put("statusId", statusId);
//        mongoCollection.findOneAndDelete(projectionQuary);
        StatusModel ob = new StatusModel();
//        System.out.println(ob.deleteStatus(statusId));
//        System.out.println(ob.getStatus(statusId));
//        System.out.println(ob.updatesatus(statusId, "The dream comes True to Night"));
//        ob.addStatus(satusInfo.toString());
//        System.out.println(ob.updateStatusLike(statusId, statusLikeInfo.toString()));
//        System.out.println(ob.updateStatusComment(statusId, statusCommentInfo.toString()));
//        System.out.println(ob.updateStatusShare(statusId, shareShareInfo.toString()));
    }
    
}
