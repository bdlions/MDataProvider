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
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONArray;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sampan-IT
 */
public class StatusServiceTest {

    StatusModel statusObject = new StatusModel();
    String userId = "SbFZCNsXxuyVgYi";
    String mappingId = "SbFZCNsXxuyVgYi";
    String friendId = "9nSEiMgzieo1O4K";
    String statusId = "1";
    String commentId = "1";

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
                .setStatusId("2")
                .setUserId("u1")
                .setUserInfo(userInfo)
                .setMappingId("u1")
                .setDescription("hi hi ha ha ha")
                .setStatusTypeId("1")
                //                .setImages(imageList)
                .build();
//        System.out.println(satusInfo.toString());
        System.out.println(statusObject.addStatus(satusInfo.toString()));
    }

//    @Test
    public void getStatusDetails() throws ParseException {
        System.out.println(statusObject.getStatusDetails(userId, statusId).toString());
    }

//    @Test
    public void addStatusComment() {
        UserInfo rUserInfo = new UserInfo();
        rUserInfo.setUserId("u5");
        UserInfo rUserInfo1 = new UserInfo();
        rUserInfo1.setUserId("u1");
        Comment statusCommentInfo = new Comment();
        statusCommentInfo.setCommentId(commentId);
        statusCommentInfo.setDescription("Thank you !!");
        statusCommentInfo.setUserInfo(rUserInfo);
        System.out.println(statusCommentInfo.toString());
        System.out.println(statusObject.addStatusComment(rUserInfo1.toString(), "2", statusCommentInfo.toString()));

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
        rUserInfo.setUserId("u5");
        Like statusLikeInfo = new Like();
        statusLikeInfo.setUserInfo(rUserInfo);
        System.out.println(statusLikeInfo.toString());
        System.out.println(statusObject.addStatusLike("u1", "2", statusLikeInfo.toString()));

    }

//    @Test
    public void addStatusCommentLike() {
        String statusId = "U1gBHO0O5XHzbGc";
        String commentId = "ChGr7xzobTjOhs7";
        UserInfo rUserInfo = new UserInfo();
        rUserInfo.setFirstName("Nazmul");
        rUserInfo.setLastName("Hasan");
        rUserInfo.setUserId("1");
        Like statusLikeInfo = new Like();
        statusLikeInfo.setUserInfo(rUserInfo);
        System.out.println(statusLikeInfo.toString());
        System.out.println(statusObject.addStatusCommentLike(statusId, commentId, statusLikeInfo.toString()));

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

    public void updateStatusComment() {
        System.out.println(statusObject.updateStatusComment("3iUkjdSgvff8YS8","0Q6rP5E90A4IuV6", "update status comment ...."));

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
    public void deleteStatusComment() {
        System.out.println(statusObject.deleteStatusComment(statusId, commentId));

    }
//    @Test
    public void getStatusComments() {
        System.out.println(statusObject.getStatusComments(userId, statusId));

    }

//    @Test
    public void getStatuses() throws ParseException {
        int offset = 0;
        int limit = 5;
        System.out.println(statusObject.getStatuses("2Q52DbDnqKEiSCn", offset, limit));

    }
//    @Test
    public void getStatusCommentLikeList() {
        int offset = 0;
        int limit = 5;
        System.out.println(statusObject.getStatusCommentLikeList(statusId, commentId));

    }
//    @Test

    public void getUserProfileStatuses() throws ParseException {
        int offset = 0;
        int limit = 5;
        System.out.println(statusObject.getUserProfileStatuses(userId, mappingId, offset, limit));

    }
    @Test

    public void recentActivities() {
        int offset = 0;
        int limit = 10;
        System.out.println(statusObject.getRecentActivities(userId, offset, limit));

    }

//    @Test
    public void unixToHuman() throws ParseException {
//        long unixSeconds = 1447681923;
        long unixSeconds = System.currentTimeMillis();
//        Date date = new Date(unixSeconds*1000L); // *1000 is to convert seconds to milliseconds
        Date date = new Date(unixSeconds); // *1000 is to convert seconds to milliseconds
//        System.out.println(date);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT+14")); // give a timezone reference for formating (see comment at the bottom
//        String formattedDate = sdf.format(date);
//        System.out.println("GMT other country  Date = " + formattedDate);
//        long a = sdf.parse(formattedDate).getTime();
//        System.out.println("unixGMTTime = " + a);
        SimpleDateFormat sdfForBD = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
        sdfForBD.setTimeZone(TimeZone.getTimeZone("GMT+6")); // give a timezone reference for formating (see comment at the bottom
        String formattedDateForBD = sdfForBD.format(date);
        System.out.println("BD Date = " + formattedDateForBD);
//        long bdTime = sdfForBD.parse(formattedDateForBD).getTime();
//        System.out.println("unixGMTTime = " + bdTime);
        SimpleDateFormat sdfForGMT0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
        sdfForGMT0.setTimeZone(TimeZone.getTimeZone("GMT+0")); // give a timezone reference for formating (see comment at the bottom
        String formattedDateForGMT0 = sdfForGMT0.format(date);
        System.out.println("BD Date = " + formattedDateForGMT0);
        long gmt0Time1 = sdfForBD.parse(formattedDateForGMT0).getTime();
        long gmt0Time2 = sdfForBD.parse(formattedDateForBD).getTime();
        System.out.println(gmt0Time1);
        System.out.println(gmt0Time2);
//        System.out.println("unixTime = " + bT);

    }

//    @Test
    public void getCurrentDate() {
        String offset = "+06";
        String gmtOffset = "GMT" + offset;
        System.out.println(gmtOffset);
        long unixSeconds = 1447681923 * 1000L;
        Date date = new Date(unixSeconds);
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sDateFormat.setTimeZone(TimeZone.getTimeZone(gmtOffset));
        String currentDate = sDateFormat.format(date);
        System.out.println(currentDate);
    }

}
