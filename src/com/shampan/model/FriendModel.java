package com.shampan.model;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.util.JSON;
import com.sampan.response.ResultEvent;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.RelationsDAO;
import com.shampan.db.collections.UserDAO;
import com.shampan.db.collections.builder.RelationsDAOBuilder;
import com.shampan.db.collections.builder.UserDAOBuilder;
import com.shampan.db.collections.fragment.relation.RelationInfo;
import com.shampan.util.PropertyProvider;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import org.bson.Document;
import org.json.JSONObject;

/**
 *
 * @author nazmul hasan
 */
public class FriendModel {
//    private Object resultEvent;

    ResultEvent resultEvent = new ResultEvent();

    public FriendModel() {
        PropertyProvider.add("response");
        PropertyProvider.add("com.shampan.properties/relations");

    }

    /**
     * This method will approve a pending friend or block a friend
     *
     * @param userId user id
     * @param friendId friend id
     */
    public String changeRelationShipStatus(String userId, String friendId, String statusTypeId) {
        MongoCollection<RelationsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELATIONS.toString(), RelationsDAO.class);
        Document sQuery = new Document();
        sQuery.put("userId", userId);
        sQuery.put("friendList.userId", friendId);
        Document sQueryF = new Document();
        sQueryF.put("userId", friendId);
        sQueryF.put("friendList.userId", userId);
        Document setQuery = new Document();
        setQuery.put("friendList.$.relationTypeId", statusTypeId);
        Document setQuery1 = new Document();
        setQuery1.put("friendList.$.relationTypeId", statusTypeId);
        if (statusTypeId == PropertyProvider.get("BlockedTypeId")) {
            setQuery.put("friendList.$.isInitiated", PropertyProvider.get("RequestSender"));
            setQuery1.put("friendList.$.isInitiated", PropertyProvider.get("RequestReceiver"));
        }
        mongoCollection.findOneAndUpdate(sQuery, new Document("$set", setQuery));
        mongoCollection.findOneAndUpdate(sQueryF, new Document("$set", setQuery1));
        resultEvent.setResponseCode(PropertyProvider.get("success"));
        return resultEvent.toString();
    }

    /**
     * This method will get a userInfo
     *
     * @param userId user id
     */
    public String addUser(String userInfo) {
        MongoCollection<UserDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
        UserDAO user = new UserDAOBuilder().build(userInfo);
        mongoCollection.insertOne(user);
        return userInfo;
    }

    public UserDAO getUserInfo(String userId) {
        MongoCollection<UserDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("fristName", "$all");
        pQuery.put("lastName", "$all");
        UserDAO userInfo = mongoCollection.find(selectQuery).first();
        return userInfo;
    }

    /**
     * This method will add pending request or block non friend
     *
     * @param userId user id
     * @param friendId friend id
     * @param statusTypeId pending or block type
     */
    public String addRequest(String userId, String friendId, String statusTypeId) {

        MongoCollection<RelationsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELATIONS.toString(), RelationsDAO.class);
        FriendModel friendObj = new FriendModel();
        UserDAO fromUserInfo = friendObj.getUserInfo(userId);
        RelationInfo formRelation = new RelationInfo();
        formRelation.setUserId(userId);
        formRelation.setIsInitiated(PropertyProvider.get("RequestSender"));
        formRelation.setRelationTypeId(statusTypeId);
        formRelation.setFristName(fromUserInfo.getFirstName());
        formRelation.setLastName(fromUserInfo.getLastName());

        UserDAO toUserInfo = friendObj.getUserInfo(friendId);
        RelationInfo toRelation = new RelationInfo();
        toRelation.setUserId(friendId);
        toRelation.setIsInitiated(PropertyProvider.get("RequestReceiver"));
        toRelation.setRelationTypeId(statusTypeId);
        toRelation.setFristName(toUserInfo.getFirstName());
        toRelation.setLastName(toUserInfo.getLastName());
        BasicDBObject fromSelectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        BasicDBObject toSelectQuery = (BasicDBObject) QueryBuilder.start("userId").is(friendId).get();
        Document fromPQuery = new Document();
        fromPQuery.put("friendList", new Document("$elemMatch", new Document("userId", friendId)));
        Document toPQuery = new Document();
        toPQuery.put("friendList", new Document("$elemMatch", new Document("userId", userId)));
        RelationsDAO formRelationInfoCursor = mongoCollection.find(fromSelectQuery).projection(fromPQuery).first();
        RelationsDAO toRelationInfoCursor = mongoCollection.find(toSelectQuery).projection(toPQuery).first();
        int fromStatus = 0;
        int toStatus = 0;
        if (formRelationInfoCursor != null) {
            int fromFriendList = formRelationInfoCursor.getFriendList().size();
            for (int i = 0; i < fromFriendList; i++) {
                if (formRelationInfoCursor.getFriendList().get(i).getUserId().equals(friendId)) {
                    System.out.println(fromStatus);
                    fromStatus = 1;
                }
            }
            if (fromStatus == 0) {
                System.out.println(fromStatus);
                RelationsDAO result = mongoCollection.findOneAndUpdate(fromSelectQuery, new Document("$push", new Document("friendList", JSON.parse(toRelation.toString()))));
            }

        } else {
            List<RelationInfo> toRelationsList = new ArrayList<RelationInfo>();
            toRelationsList.add(toRelation);
            RelationsDAO formRelationInfo = new RelationsDAOBuilder()
                    .setUserId(userId)
                    .setFriendList(toRelationsList)
                    .build();
            mongoCollection.insertOne(formRelationInfo);
        }
        if (toRelationInfoCursor != null) {
            int toFriendList = toRelationInfoCursor.getFriendList().size();
            for (int i = 0; i < toFriendList; i++) {
                if (toRelationInfoCursor.getFriendList().get(i).getUserId().equals(userId)) {
                    toStatus = 1;
                }
            }

            if (toStatus == 0) {
                RelationsDAO result = mongoCollection.findOneAndUpdate(toSelectQuery, new Document("$push", new Document("friendList", JSON.parse(formRelation.toString()))));
            }

        } else {
            List<RelationInfo> formRelationsList = new ArrayList<RelationInfo>();
            formRelationsList.add(formRelation);
            RelationsDAO toRelationInfo = new RelationsDAOBuilder()
                    .setUserId(friendId)
                    .setFriendList(formRelationsList)
                    .build();
            mongoCollection.insertOne(toRelationInfo);

        }
        resultEvent.setResponseCode(PropertyProvider.get("success"));
        return resultEvent.toString();
    }

    /**
     * This method will return a friend relation ship status
     *
     * @param userId user id
     * @param friendId friend id
     */
    public String getRelationShipStatus(String userId, String friendId) {
        MongoCollection<RelationsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELATIONS.toString(), RelationsDAO.class);
        Document sQuery = new Document();
        sQuery.put("userId", userId);
        sQuery.put("friendList.userId", friendId);
        Document pQuery = new Document();
        pQuery.put("friendList.$", "$all");
        RelationsDAO status = mongoCollection.find(sQuery).projection(pQuery).first();
        String relationShipStatus = null;
        String isInitiated = null;
        JSONObject rStatusJson = new JSONObject();
        if (status != null) {
            for (int i = 0; i < status.getFriendList().size(); i++) {
                relationShipStatus = status.getFriendList().get(i).getRelationTypeId();
                isInitiated = status.getFriendList().get(i).getIsInitiated();
                rStatusJson.put("relationShipStatus", relationShipStatus);
                rStatusJson.put("isInitiated", isInitiated);
            }
        } else {
            relationShipStatus = PropertyProvider.get("NonFriendTypeId");
            rStatusJson.put("relationShipStatus", relationShipStatus);
        }
        return rStatusJson.toString();
    }

    /**
     * This method will return a friend relation ship status
     *
     * @param userId user id
     * @param friendId friend id
     */
    public RelationsDAO getFriendInfo(String userId, String friendId) {
        MongoCollection<RelationsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELATIONS.toString(), RelationsDAO.class);
        Document sQuery = new Document();
        sQuery.put("userId", userId);
        sQuery.put("friendList.userId", friendId);
        Document pQuery = new Document();
        pQuery.put("friendList.$", "$all");
        RelationsDAO friendInfo = mongoCollection.find(sQuery).projection(pQuery).first();
        return friendInfo;
    }

    /**
     * This method will remove a friend from friend list
     *
     * @param userId user id
     * @param friendId friend id
     */
    public String deleteRequest(String userId, String friendId) {
        MongoCollection<RelationsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELATIONS.toString(), RelationsDAO.class);
        Document sQuery = new Document();
        sQuery.put("userId", userId);
        sQuery.put("friendList.userId", friendId);
        Document sQuery1 = new Document();
        sQuery1.put("userId", friendId);
        sQuery1.put("friendList.userId", userId);
        Document pQuery = new Document();
        pQuery.put("userId", friendId);
        Document pQuery1 = new Document();
        pQuery1.put("userId", userId);
        RelationsDAO result = mongoCollection.findOneAndUpdate(sQuery, new Document("$pull", new Document("friendList", pQuery)));
        RelationsDAO result1 = mongoCollection.findOneAndUpdate(sQuery1, new Document("$pull", new Document("friendList", pQuery1)));
        return "successful";

    }

    /**
     * This method will block a friend
     *
     * @param userId user id
     * @param friendId friend id
     */
    public String blockFriend(String userId, String friendId) {
        MongoCollection<RelationsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELATIONS.toString(), RelationsDAO.class);
        Document sQuery = new Document();
        sQuery.put("userId", userId);
        sQuery.put("friendList.userId", friendId);
        Document sQueryF = new Document();
        sQueryF.put("userId", friendId);
        sQueryF.put("friendList.userId", userId);
        Document setQuery = new Document();
        setQuery.put("friendList.$.relationTypeId", PropertyProvider.get("BlockedTypeId"));
        mongoCollection.findOneAndUpdate(sQuery, new Document("$set", setQuery));
        mongoCollection.findOneAndUpdate(sQueryF, new Document("$set", setQuery));
        resultEvent.setResponseCode(PropertyProvider.get("success"));
        return resultEvent.toString();

    }

    /**
     * This method will unblock a friend from unblock friend list
     *
     * @param userId user id
     * @param friendId friend id
     */
    public void unblockFriend(String userId, String friendId) {
//delete request
    }

    /**
     * This method will block a friend from pending friend list
     *
     * @param userId user id
     * @param friendId friend id
     */
    public String blockNonFriend(String userId, String friendId) {
        MongoCollection<RelationsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELATIONS.toString(), RelationsDAO.class);
        FriendModel friendObj = new FriendModel();
        UserDAO fromUserInfo = friendObj.getUserInfo(userId);
        RelationInfo formRelation = new RelationInfo();
        formRelation.setUserId(userId);
        formRelation.setIsInitiated(PropertyProvider.get("FriendRequestSender"));
        formRelation.setRelationTypeId(PropertyProvider.get("BlockedTypeId"));
        formRelation.setFristName(fromUserInfo.getFirstName());
        formRelation.setLastName(fromUserInfo.getLastName());

        UserDAO toUserInfo = friendObj.getUserInfo(friendId);
        RelationInfo toRelation = new RelationInfo();
        toRelation.setUserId(friendId);
        toRelation.setIsInitiated(PropertyProvider.get("FriendRequestReceiver"));
        toRelation.setRelationTypeId(PropertyProvider.get("BlockedTypeId"));
        toRelation.setFristName(toUserInfo.getFirstName());
        toRelation.setLastName(toUserInfo.getLastName());
        List<RelationInfo> toRelationsList = new ArrayList<RelationInfo>();
        toRelationsList.add(toRelation);
        RelationsDAO formRelationInfo = new RelationsDAOBuilder()
                .setUserId(userId)
                .setFriendList(toRelationsList)
                .build();
//        mongoCollection.insertOne(formRelationInfo);
        List<RelationInfo> formRelationsList = new ArrayList<RelationInfo>();
        formRelationsList.add(formRelation);
        RelationsDAO toRelationInfo = new RelationsDAOBuilder()
                .setUserId(friendId)
                .setFriendList(formRelationsList)
                .build();
        List<RelationsDAO> insertList = new ArrayList<RelationsDAO>();
        insertList.add(formRelationInfo);
        insertList.add(toRelationInfo);
        mongoCollection.insertMany(insertList);
        resultEvent.setResponseCode(PropertyProvider.get("success"));
        return resultEvent.toString();

    }

    /**
     * This method will unblock a pending friend
     *
     * @param userId user id
     * @param friendId friend id
     */
    public void unblockPendingFriend(String userId, String friendId) {
//delete request
    }

    /**
     * This method will return friend list of a user
     *
     * @param userId user id
     * @param offset offset
     * @param limit limit
     * @param typeId relation ship status
     */
    public String getTestFriendList(String userId, int offset, int limit, String typeId) {
        MongoCollection<RelationsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELATIONS.toString(), RelationsDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document sQuery = new Document();
        sQuery.put("userId", userId);
//        sQuery.put("friendList", new Document("$elemMatch", new Document("relationTypeId", typeId)));
//        sQuery.put("friendList.relationTypeId", typeId);
        List fSelection = new ArrayList<>();
        fSelection.add(offset);
        fSelection.add(limit);
        Document pQuery = new Document();
//        pQuery.put("friendList", new Document("$slice", fSelection));
        pQuery.put("friendList", new Document("$elemMatch", new Document("relationTypeId", typeId)));
        RelationsDAO friendList = mongoCollection.find(sQuery).projection(pQuery).first();
        if (friendList != null) {
            System.out.println(friendList);
            return friendList.toString();

        } else {
            return "";
        }

    }

    public String getFriendList(String userId, int offset, int limit, String typeId) {
        MongoCollection<RelationsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELATIONS.toString(), RelationsDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document sQuery = new Document();
        sQuery.put("userId", userId);
        List fSelection = new ArrayList<>();
        fSelection.add(offset);
        fSelection.add(limit);
        Document pQuery = new Document();
        pQuery.put("friendList", "$all");
        RelationsDAO friendList = mongoCollection.find(sQuery).projection(pQuery).first();
        List<RelationInfo> requestList = new ArrayList<RelationInfo>();
        if (friendList != null) {
            for (int i = 0; i < friendList.getFriendList().size(); i++) {
                if (friendList.getFriendList().get(i).getRelationTypeId().equals(typeId)) {
                    requestList.add(friendList.getFriendList().get(i));
                }
            }
        }
        if (requestList != null) {
            System.out.println(requestList);
            return requestList.toString();

        } else {
            return "";
        }

    }

    /**
     * This method will return blocked friend list of a user
     *
     * @param userId user id
     * @param offset offset
     * @param limit limit
     */
    public void getBlockedFriendList(String userId, int offset, int limit) {

    }

    /**
     * This method will return pending friend list of a user
     *
     * @param userId user id
     * @param offset offset
     * @param limit limit
     */
    public void getPendingFriendList(String userId, int offset, int limit) {

    }

    public String shearchFriend() {
        MongoCollection<RelationsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELATIONS.toString(), RelationsDAO.class);

//        MongoCursor<RelationsDAO> CursorReligionList = mongoCollection.find({"name": /.*m.*/}).iterator();
        return "success";
    }

}
