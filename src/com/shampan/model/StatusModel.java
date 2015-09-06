package com.shampan.model;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.RelationsDAO;
import com.shampan.db.collections.StatusDAO;
import com.shampan.db.collections.builder.StatusDAOBuilder;
import com.shampan.db.collections.fragment.status.Comment;
import com.shampan.db.collections.fragment.status.Like;
import com.shampan.db.collections.fragment.status.ReferenceInfo;
import com.shampan.db.collections.fragment.status.ReferenceList;
import com.shampan.db.collections.fragment.status.Share;
import com.shampan.db.collections.fragment.status.UserInfo;
import com.shampan.util.LogWriter;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.bson.Document;

/**
 *
 * @author nazmul
 */
public class StatusModel {

    public StatusModel() {

    }

    /**
     * *
     *
     * Add a status parameter Status Info
     */
    public String addStatus(String statusInfo) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        StatusDAO statusInfoObj = new StatusDAOBuilder().build(statusInfo);
        System.out.println(statusInfoObj);
        try {
            if (statusInfoObj != null) {
                mongoCollection.insertOne(statusInfoObj);
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error(npe);
        }
        return "successful";
    }

    public List<StatusDAO> getStatuses(String userId) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        MongoCursor<StatusDAO> cursorStatusInfoList = mongoCollection.find(selectQuery).iterator();
        List<StatusDAO> statusList = IteratorUtils.toList(cursorStatusInfoList);
        return statusList;

    }

    /**
     * Delete a status parameter StatusId status id
     *
     */
    public String deleteStatus(String statusId) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        mongoCollection.findOneAndDelete(selectQuery);
        return "successful";

    }

    /**
     *
     * Update status parameter statusId and Status Description
     *
     */
    public String updateStatus(String statusId, String statusInfo) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("description", statusInfo)));
        return "successfull";
    }

    /**
     * add Status like parameter statusId and like Info
     */
    public String addStatusLike(String statusId, String likeInfo) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        Like statusLikeInfo = Like.getStatusLike(likeInfo);
        try {
            if (statusLikeInfo != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("like", JSON.parse(statusLikeInfo.toString()))));
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error(npe);
        }
        return "Succefull";
    }

    /**
     * Add Status Comment parameter statusId status Id ,commentInfo description
     *
     */
    public String addStatusComment(String statusId, String commentInfo) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        Comment statusCommentInfo = Comment.getStatusComment(commentInfo);
        try {
            if (statusCommentInfo != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("comment", JSON.parse(statusCommentInfo.toString()))));
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error(npe);
        }
        return "Succefull";
    }

    public String shareStatus(String statusId, String refUserInfo, String shareInfo) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        System.out.println(refUserInfo);
        UserInfo statusRefInfo = UserInfo.getUserInfo(refUserInfo);
        System.out.println(statusRefInfo);
        try {
            if (statusRefInfo != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("ReferenceList", JSON.parse(statusRefInfo.toString()))));
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error(npe);
        }
        if (shareInfo != null) {
            StatusModel obj = new StatusModel();
            obj.addStatus(shareInfo);
        }

        return "Successful";
    }

    public String updateStatusPrivacy(String statusId, String privacyInfo) {

        return "Successful";
    }

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
        referenceInfo.setUserInfo(rUserInfo);

        Like statusLikeInfo = new Like();
        statusLikeInfo.setUserInfo(rUserInfo);
        List<Like> likeList = new ArrayList<Like>();
        likeList.add(statusLikeInfo);
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

    }

}
