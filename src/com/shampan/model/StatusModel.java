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

    public String addStatus(String statusInfo) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        StatusDAO statusInfoObj = new StatusDAOBuilder().build(statusInfo);
        mongoCollection.insertOne(statusInfoObj);
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

    public String deleteStatus(String statusId) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        mongoCollection.findOneAndDelete(selectQuery);
        return "successful";

    }

    public String updateStatus(String statusId, String statusInfo) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("description", statusInfo)));
        return "successfull";
    }

    public String updateStatusLike(String statusId, String likeInfo) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        Like statusLikeInfo = Like.getStatusLike(likeInfo);
        mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("like", JSON.parse(statusLikeInfo.toString()))));
        return "Succefull";
    }

    public String addStatusComment(String statusId, String commentInfo) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        Comment statusCommentInfo = Comment.getStatusComment(commentInfo);
        mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("comment", JSON.parse(statusCommentInfo.toString()))));
        return "Succefull";
    }

    public String updateStatusShare(String statusId, String shareInfo) {
        MongoCollection<StatusDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.STATUSES.toString(), StatusDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("statusId").is(statusId).get();
        Share statusShareInfo = Share.getStatusShare(shareInfo);
        mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("share", JSON.parse(statusShareInfo.toString()))));

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
//        System.out.println("test" + satusInfo.toString());
//        Document projectionQuary = new Document();
//        projectionQuary.put("statusId", statusId);
//        mongoCollection.findOneAndDelete(projectionQuary);
        StatusModel ob = new StatusModel();

//        System.out.println(ob.addStatus(satusInfo.toString()));
//        System.out.println(ob.deleteStatus(statusId));
        System.out.println(ob.getStatuses(userId));
//        System.out.println(ob.updatesatus(statusId, "The dream comes True to Night"));
//        ob.addStatus(satusInfo.toString());
//        System.out.println(ob.updateStatusLike(statusId, statusLikeInfo.toString()));
//        System.out.println(ob.addStatusComment(statusId, statusCommentInfo.toString()));
//        System.out.println(ob.updateStatusShare(statusId, shareShareInfo.toString()));
    }

}
