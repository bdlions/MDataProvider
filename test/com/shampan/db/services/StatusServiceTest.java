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
import com.shampan.db.collections.fragment.status.Image;
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
    String userId = "55Lj6k4iZReT4ck";
    String mappingId = "mqQ06eko9TqYYul";
    String friendId = "9nSEiMgzieo1O4K";
    String statusId = "Xs2Z3qLtNQmY1Iq";

//    @Test
    public void addStatus() {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName("Alamgir");
        userInfo.setLastName("Kabir");
        userInfo.setUserId(userId);
        Image image = new Image();
        image.setImage("hasenhen.jpg");
        List<Image> imageList = new ArrayList<Image>();
        imageList.add(image);
        ReferenceList refId = new ReferenceList();
        refId.setStatusId(statusId);
        List<ReferenceList> refList = new ArrayList<ReferenceList>();
        refList.add(refId);
        ReferenceInfo refInfo = new ReferenceInfo();
        refInfo.setDescription("reference info");
        refInfo.setUserInfo(userInfo);
        refInfo.setImages(imageList);
        StatusDAO satusInfo = new StatusDAOBuilder()
                .setStatusId(statusId)
                .setUserId(friendId)
                .setUserInfo(userInfo)
                .setMappingId(friendId)
                .setDescription("hi hi ha ha ha")
                .setStatusTypeId("1")
                .setReferenceList(refList)
                .setReferenceInfo(refInfo)
                //                .setImages(imageList)
                .build();
//        System.out.println(satusInfo.toString());
        System.out.println(statusObject.addStatus(satusInfo.toString()));
    }

    @Test
    public void getStatusDetails() {
        System.out.println(statusObject.getStatusDetails(userId,statusId).toString());
    }

//    @Test
    public void addStatusComment() {
        UserInfo rUserInfo = new UserInfo();
        rUserInfo.setFirstName("Nazmul");
        rUserInfo.setLastName("Hasan");
        rUserInfo.setUserId("u1");
        Comment statusCommentInfo = new Comment();
        statusCommentInfo.setDescription("Thank you !!");
        statusCommentInfo.setUserInfo(rUserInfo);
        System.out.println(statusCommentInfo.toString());
        System.out.println(statusObject.addStatusComment(userId, statusId, statusCommentInfo.toString()));

    }

//    @Test
    public void shareStatus() {
        UserInfo rUserInfo = new UserInfo();
        rUserInfo.setFirstName("Nazmul");
        rUserInfo.setLastName("Hasan");
        rUserInfo.setUserId(userId);
        ReferenceInfo referenceInfo = new ReferenceInfo();
        referenceInfo.setDescription("Old status description");
        referenceInfo.setUserInfo(rUserInfo);

        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName("Alamgir");
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
        System.out.println(statusObject.shareStatus(userId, statusId, shareInfo.toString(), satusInfo.toString()));
    }

//    @Test
    public void addStatusLike() {
        UserInfo rUserInfo = new UserInfo();
        rUserInfo.setFirstName("Nazmul");
        rUserInfo.setLastName("Hasan");
        rUserInfo.setUserId("1");
        Like statusLikeInfo = new Like();
        statusLikeInfo.setUserInfo(rUserInfo);
        System.out.println(statusLikeInfo.toString());
        System.out.println(statusObject.addStatusLike(userId, statusId, statusLikeInfo.toString()));

    }
//    @Test

    public void addStatusShare() {
        UserInfo rUserInfo = new UserInfo();
        rUserInfo.setFirstName("NAzmul");
        rUserInfo.setLastName("Hasan");
        rUserInfo.setUserId("100105");
        Share statusLikeInfo = new Share();
        statusLikeInfo.setUserInfo(rUserInfo);
        System.out.println(statusLikeInfo.toString());
//        System.out.println(statusObject.addStatusLike(statusId, statusLikeInfo.toString()));

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
    public void getStatusShareList() {
        System.out.println(statusObject.getStatusShareList(statusId));

    }
//    @Test

    public void getStatusComments() {
        System.out.println(statusObject.getStatusComments(statusId));

    }

//    @Test
    public void getStatuses() {
        int offset = 0;
        int limit = 5;
        System.out.println(statusObject.getStatuses(userId, offset, limit));

    }
//    @Test
    public void getUserProfileStatuses() {
        int offset = 0;
        int limit = 5;
        System.out.println(statusObject.getUserProfileStatuses(userId,mappingId, offset, limit));

    }

}
