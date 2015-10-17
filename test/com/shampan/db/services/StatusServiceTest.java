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
import com.shampan.db.collections.fragment.photo.Image;
import com.shampan.db.collections.fragment.status.Comment;
import com.shampan.db.collections.fragment.status.Like;
import com.shampan.db.collections.fragment.status.ReferenceInfo;
import com.shampan.db.collections.fragment.status.ReferenceList;
import com.shampan.db.collections.fragment.status.Share;
import com.shampan.db.collections.fragment.status.UserInfo;
import com.shampan.model.StatusModel;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sampan-IT
 */
public class StatusServiceTest {

    StatusModel statusObject = new StatusModel();
    String userId ="eQeOwhE7hrUkCyP";
    String statusId ="YC0EHMn2JVxcpyz";

//    @Test
    public void addStatus() {
        String userId = "100157";
        String statusId = "1";
        List<Image> images = new ArrayList<Image>();
        
        UserInfo userInfo = new UserInfo();
        userInfo.setFristName("Alamgir");
        userInfo.setLastName("Kabir");
        userInfo.setUserId(userId);
        StatusDAO satusInfo = new StatusDAOBuilder()
                .setStatusId(statusId)
                .setUserId(userId)
                .setUserInfo(userInfo)
                .setMappingId(userId)
                .setDescription("this is a wounderful invention By scientist Shemin of NASA")
                .setStatusTypeId("1")
                .build();
        System.out.println(statusObject.addStatus(satusInfo.toString()));
    }

//    @Test
    public void addStatusComment() {
        UserInfo rUserInfo = new UserInfo();
        rUserInfo.setFristName("Nazmul");
        rUserInfo.setLastName("Hasan");
        rUserInfo.setUserId("100105");
        Comment statusCommentInfo = new Comment();
        statusCommentInfo.setDescription("Thank you !!");
        statusCommentInfo.setUserInfo(rUserInfo);
        System.out.println(statusCommentInfo.toString());
        System.out.println(statusObject.addStatusComment(statusId, statusCommentInfo.toString()));

    }

//    @Test
    public void shareStatus() {
        UserInfo rUserInfo = new UserInfo();
        rUserInfo.setFristName("Nazmul");
        rUserInfo.setLastName("Hasan");
        rUserInfo.setUserId("100105");
        ReferenceInfo referenceInfo = new ReferenceInfo();
        referenceInfo.setDescription("Old status description");
        referenceInfo.setUserInfo(rUserInfo);
        
        UserInfo userInfo = new UserInfo();
        userInfo.setFristName("Alamgir");
        userInfo.setLastName("Kabir");
        userInfo.setUserId("1");
        Share shareInfo = new Share();
        shareInfo.setUserInfo(userInfo);
        StatusDAO satusInfo = new StatusDAOBuilder()
                .setStatusId(statusId)
                .setUserId("1")
                .setUserInfo(userInfo)
                .setMappingId(userId)
                .setReferenceInfo(referenceInfo)
                .setDescription("this is a wounderful invention By scientist Shemin of NASA")
                .setStatusTypeId("3")
                .build();
        System.out.println(statusObject.shareStatus(statusId, shareInfo.toString(), satusInfo.toString()));
    }

//    @Test
    public void addStatusLike() {
        UserInfo rUserInfo = new UserInfo();
        rUserInfo.setFristName("Nazmul");
        rUserInfo.setLastName("Hasan");
        rUserInfo.setUserId("100105");
        Like statusLikeInfo = new Like();
        statusLikeInfo.setUserInfo(rUserInfo);
        System.out.println(statusLikeInfo.toString());
        System.out.println(statusObject.addStatusLike(statusId, statusLikeInfo.toString()));

    }
//    @Test
    public void addStatusShare() {
        UserInfo rUserInfo = new UserInfo();
        rUserInfo.setFristName("Nazmul");
        rUserInfo.setLastName("Hasan");
        rUserInfo.setUserId("100105");
        Share statusLikeInfo = new Share();
        statusLikeInfo.setUserInfo(rUserInfo);
        System.out.println(statusLikeInfo.toString());
        System.out.println(statusObject.addStatusLike(statusId, statusLikeInfo.toString()));

    }
//    @Test

    public void updateStatus() {
        System.out.println(statusObject.updateStatus("2", "update status...."));

    }
//    @Test

    public void deleteStatus() {
        System.out.println(statusObject.deleteStatus("2"));

    }
//    @Test

    public void getStatusLikeList() {
        System.out.println(statusObject.getStatusLikeList(statusId));

    }
//    @Test

    public void getStatusComments() {
        System.out.println(statusObject.getStatusComments(statusId));

    }
    
//    @Test
    public void getStatuses() {
        int offset = 0;
        int limit = 5;
       statusObject.getStatuses(userId,offset,limit);

    }

}
