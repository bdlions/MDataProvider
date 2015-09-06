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
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sampan-IT
 */
public class StatusServiceTest {

    StatusModel statusObject = new StatusModel();

    //@Test
    public void main() {
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
        referenceInfo.setUserInfo(rUserInfo);
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

    }

//    @Test
    public void addStatus() {
        String userId = "100157";
        String statusId = "1";
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
        System.out.println(statusObject.addStatusComment("2", statusCommentInfo.toString()));

    }

    @Test
    public void shareStatus() {
        String userId = "100157";
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
        userInfo.setUserId(userId);
        StatusDAO satusInfo = new StatusDAOBuilder()
                .setStatusId("3")
                .setUserId(userId)
                .setUserInfo(userInfo)
                .setMappingId(userId)
                .setReferenceInfo(referenceInfo)
                .setDescription("this is a wounderful invention By scientist Shemin of NASA")
                .setStatusTypeId("3")
                .build();
        System.out.println(statusObject.shareStatus("1", userInfo.toString(), satusInfo.toString()));
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
        System.out.println(statusObject.addStatusLike("2", statusLikeInfo.toString()));

    }
//    @Test

    public void updateStatus() {
        System.out.println(statusObject.updateStatus("2", "update status...."));

    }
//    @Test

    public void deleteStatus() {
        System.out.println(statusObject.deleteStatus("2"));

    }

}
