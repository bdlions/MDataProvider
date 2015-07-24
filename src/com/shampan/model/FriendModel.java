package com.shampan.model;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.MongoCollection;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.BasicProfileDAO;
import com.shampan.db.collections.RelationsDAO;
import com.shampan.db.collections.fragment.School;
import org.bson.Document;

/**
 *
 * @author nazmul hasan
 */
public class FriendModel {
    public FriendModel()
    {
    
    }
    
    /**
    * This method will add a friend into pending list of a user
    * @param userId user id
    * @param friendId friend id
    */
    public void addPendingRequest(String userId, String friendId)
    {
    
    }
    /**
    * This method will approve a pending friend
    * @param userId user id
    * @param friendId friend id
    */
    public void approvePendingRequest(String userId, String friendId)
    {
    
    }
    /**
    * This method will add a friend
    * @param userId user id
    * @param friendId friend id
    */
    public void addFriend(String userId, String friendId)
    {
        MongoCollection<RelationsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELATIONS.toString(), RelationsDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
//        Document pQuery = new Document();
//        pQuery.put("schools", "$all");
//        BasicProfileDAO schoolCursor = mongoCollection.find(selectQuery).projection(pQuery).first();
//        School school = School.getSchool(additionalData);
//        if (school != null) {
//            schoolCursor.getSchools().add(school);
//        }
//        BasicProfileDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", schoolCursor));
//        String response = "successful";
//        return response;
    }
    /**
    * This method will remove a friend from friend list
    * @param userId user id
    * @param friendId friend id
    */
    public void removeFriend(String userId, String friendId)
    {
    
    }
    /**
    * This method will block a friend
    * @param userId user id
    * @param friendId friend id
    */
    public void blockFriend(String userId, String friendId)
    {
    
    }
    /**
    * This method will unblock a friend from unblock friend list
    * @param userId user id
    * @param friendId friend id
    */
    public void unblockFriend(String userId, String friendId)
    {
    
    }
    /**
    * This method will block a friend from pending friend list
    * @param userId user id
    * @param friendId friend id
    */
    public void blockPendingFriend(String userId, String friendId)
    {
    
    }
    /**
    * This method will unblock a pending friend
    * @param userId user id
    * @param friendId friend id
    */
    public void unblockPendingFriend(String userId, String friendId)
    {
    
    }
    /**
    * This method will return friend status of a user. i.e. friend/blocked/pending
    * @param userId user id
    * @param friendId friend id
    */
    public void getFriendStatus(String userId, String friendId)
    {
    
    }
    /**
    * This method will return friend list of a user
    * @param userId user id
    * @param offset offset
    * @param limit limit
    */
    public void getFriendList(String userId, int offset, int limit)
    {
    
    }
    /**
    * This method will return blocked friend list of a user
    * @param userId user id
    * @param offset offset
    * @param limit limit
    */
    public void getBlockedFriendList(String userId, int offset, int limit)
    {
    
    }
    /**
    * This method will return pending friend list of a user
    * @param userId user id
    * @param offset offset
    * @param limit limit
    */
    public void getPendingFriendList(String userId, int offset, int limit)
    {
    
    }
}
