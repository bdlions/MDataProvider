package com.shampan.model;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.BasicProfileDAO;
import com.shampan.db.collections.RelationsDAO;
import com.shampan.db.collections.builder.RelationsDAOBuilder;
import com.shampan.db.collections.fragment.School;
import com.shampan.db.collections.fragment.relations.RelationInfo;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author nazmul hasan
 */
public class FriendModel {

    public FriendModel() {

    }

    /**
     * This method will add a friend into pending list of a user
     *
     * @param userId user id
     * @param friendId friend id
     */
    public void addPendingRequest(String userId, String friendId) {

    }

    /**
     * This method will approve a pending friend
     *
     * @param userId user id
     * @param friendId friend id
     */
    public void approvePendingRequest(String userId, String friendId) {

    }

    /**
     * This method will add a friend
     *
     * @param userId user id
     * @param friendId friend id
     */
    public String addFriend(String userId, String friendId) {
        MongoCollection<RelationsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELATIONS.toString(), RelationsDAO.class);

        RelationInfo formRelation = new RelationInfo();
        formRelation.setUserId(userId);
        formRelation.setIsInitiated("1");

        RelationInfo toRelation = new RelationInfo();
        toRelation.setUserId(friendId);
        toRelation.setIsInitiated("0");

        BasicDBObject fromSelectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        BasicDBObject toSelectQuery = (BasicDBObject) QueryBuilder.start("userId").is(friendId).get();
        Document fromPQuery = new Document();
        fromPQuery.put("friendList", "$all");
        Document toPQuery = new Document();
        toPQuery.put("friendList", "$all");
        RelationsDAO formRelationInfoCursor = mongoCollection.find(fromSelectQuery).projection(fromPQuery).first();
        RelationsDAO toRelationInfoCursor = mongoCollection.find(toSelectQuery).projection(toPQuery).first();

        if (formRelationInfoCursor != null) {
            formRelationInfoCursor.getFriendList().add(toRelation);
            RelationsDAO result = mongoCollection.findOneAndUpdate(fromSelectQuery, new Document("$set", formRelationInfoCursor));
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
            toRelationInfoCursor.getFriendList().add(formRelation);
            RelationsDAO result = mongoCollection.findOneAndUpdate(toSelectQuery, new Document("$set", toRelationInfoCursor));
        } else {
            List<RelationInfo> formRelationsList = new ArrayList<RelationInfo>();
            formRelationsList.add(formRelation);
            RelationsDAO toRelationInfo = new RelationsDAOBuilder()
                    .setUserId(friendId)
                    .setFriendList(formRelationsList)
                    .build();
            mongoCollection.insertOne(toRelationInfo);

        }

        String response = "successful";
        return response;
    }

    /**
     * This method will remove a friend from friend list
     *
     * @param userId user id
     * @param friendId friend id
     */
    public void removeFriend(String userId, String friendId) {

    }

    /**
     * This method will block a friend
     *
     * @param userId user id
     * @param friendId friend id
     */
    public void blockFriend(String userId, String friendId) {

    }

    /**
     * This method will unblock a friend from unblock friend list
     *
     * @param userId user id
     * @param friendId friend id
     */
    public void unblockFriend(String userId, String friendId) {

    }

    /**
     * This method will block a friend from pending friend list
     *
     * @param userId user id
     * @param friendId friend id
     */
    public void blockPendingFriend(String userId, String friendId) {

    }

    /**
     * This method will unblock a pending friend
     *
     * @param userId user id
     * @param friendId friend id
     */
    public void unblockPendingFriend(String userId, String friendId) {

    }

    /**
     * This method will return friend status of a user. i.e.
     * friend/blocked/pending
     *
     * @param userId user id
     * @param friendId friend id
     */
    public void getFriendStatus(String userId, String friendId) {

    }

    /**
     * This method will return friend list of a user
     *
     * @param userId user id
     * @param offset offset
     * @param limit limit
     */
//    public String getFriendList(String userId, int offset, int limit) {
    public String getFriendList(String userId) {
        MongoCollection<RelationsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELATIONS.toString(), RelationsDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("friendList", "$all");
          RelationsDAO friendList = mongoCollection.find(selectQuery).projection(pQuery).first();
//        MongoCursor<ReligionsDAO> CursorReligionList = mongoCollection.find().iterator();
//         MongoCursor<ReligionsDAO> result1 = mongoCollection.find(SelectQuery, new Document("$slice", new Document("friendList", [offset,limit])));
//         MongoCursor<RelationsDAO> friendList =  (MongoCursor<RelationsDAO>) mongoCollection.find(SelectQuery).projection(pQuery).limit(limit);
//        System.out.println(friendList);
                
        return friendList.toString() ;

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

    public static void main(String[] args) {

//        RelationInfo formRelation = new RelationInfo();
//        formRelation.setUserId("100157");
//        formRelation.setFristName("Nazneen");
//        formRelation.setLastName("Sultana");
//        formRelation.setIsInitiated("1");
//        
//        RelationInfo toRelation = new RelationInfo();
//        toRelation.setUserId("100105");
//        toRelation.setIsInitiated("0");
//        toRelation.setFristName("Alamgir");
//        toRelation.setLastName("Kabir");
//
//        List<RelationInfo> formRelationsList = new ArrayList<RelationInfo>();
//        formRelationsList.add(formRelation);
//        
//        List<RelationInfo> toRelationsList = new ArrayList<RelationInfo>();
//        toRelationsList.add(toRelation);
//
//
//        RelationsDAO formRelationInfo = new RelationsDAOBuilder()
//                .setUserId("100157")
//                .setFriendList(toRelationsList)
//                .build();
//
//        RelationsDAO toRelationInfo = new RelationsDAOBuilder()
//                .setUserId("100105")
//                .setFriendList(formRelationsList)
//                .build();

//        System.out.print(formRelationInfo);
//        System.out.print(toRelationInfo);
        String userId = "100157";
        String friendId = "100106";
        int limit = 3;
        int offset = 0;
        FriendModel ob = new FriendModel();
//        System.out.println(ob.getFriendList(userId));
        
//        String friendInfo = ob.addFriend(userId, friendId);

    }
}
