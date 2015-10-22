package com.shampan.model;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.util.JSON;
import com.sampan.response.ResultEvent;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.RelationsDAO;
import com.shampan.db.collections.builder.RelationsDAOBuilder;
import com.shampan.db.collections.fragment.relation.RelationInfo;
import com.shampan.util.PropertyProvider;
import com.shampan.util.Utility;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author nazmul hasan
 */
public class RelationModel {
    private ResultEvent resultEvent = new ResultEvent();
    Utility utility = new Utility();
    public RelationModel()
    {
        PropertyProvider.add("com.shampan.properties/relations");
        PropertyProvider.add("com.shampan.properties/response");
        PropertyProvider.add("com.shampan.properties/attributes");
    }

    /**
     * This method will return result event
     * @return ResultEvent, result event
     */
    public ResultEvent getResultEvent() {
        return resultEvent;
    }

    /**
     * This method will set result event
     * @param resultEvent, result event
     */
    public void setResultEvent(ResultEvent resultEvent) {
        this.resultEvent = resultEvent;
    }
    
    /**
     * This method will add a friend relation
     * @param fromUserId, from user id of a friend relation
     * @param toUserId, to user id of a friend relation
     * @return ResultEvent, result of the operation
     */
    public ResultEvent addFriend(String fromUserId, String toUserId)
    {
        //after adding a friend newly added user will be under pending list
        createRelation(fromUserId, toUserId, PropertyProvider.get("RELATION_TYPE_PENDING_ID"));
        return this.resultEvent;
    }
    
    /**
     * This method will add a block relation
     * @param fromUserId, from user id of a block relation
     * @param toUserId, to user id of a block relation
     * @return ResultEvent, result of the operation
     */
    public ResultEvent blockNonFriend(String fromUserId, String toUserId)
    {
        createRelation(fromUserId, toUserId, PropertyProvider.get("RELATION_TYPE_BLOCK_ID"));
        return this.resultEvent;
    }
    
    /**
     * This method will update block relation
     * @param fromUserId, from user id of a block relation
     * @param toUserId, to user id of a block relation
     * @return ResultEvent, result of the operation
     */
    public ResultEvent blockFriend(String fromUserId, String toUserId)
    {
        updateRelation(fromUserId, toUserId, PropertyProvider.get("RELATION_TYPE_BLOCK_ID"));
        return this.resultEvent;
    }
    
    /**
     * This method will approve a friend relation
     * @param fromUserId, from user id of a friend relation
     * @param toUserId, to user id of a friend relation
     * @return ResultEvent, result of the operation
     */
    public ResultEvent approveFriend(String fromUserId, String toUserId)
    {
        updateRelation(fromUserId, toUserId, PropertyProvider.get("RELATION_TYPE_FRIEND_ID"));
        return this.resultEvent;
    }
    
    /**
     * This method will remove friend relation
     * @param fromUserId, from user id of a friend relation
     * @param toUserId, to user id of a friend relation
     * @return ResultEvent, result of the operation
     */
    public ResultEvent removeFriendRequest(String fromUserId, String toUserId)
    {
        deleteRelation(fromUserId, toUserId);
        return this.resultEvent;
    }
    
    /**
     * This method will remove block relation
     * @param fromUserId, from user id of a block relation
     * @param toUserId, to user id of a block relation
     * @return ResultEvent, result of the operation
     */
    public ResultEvent unblockFriend(String fromUserId, String toUserId)
    {
        deleteRelation(fromUserId, toUserId);
        return this.resultEvent;
    }
    
    /**
     * This method will remove friend relation
     * @param fromUserId, from user id of a friend relation
     * @param toUserId, to user id of a friend relation
     * @return ResultEvent, result of the operation
     */
    public ResultEvent removeFriend(String fromUserId, String toUserId)
    {
        deleteRelation(fromUserId, toUserId);
        return this.resultEvent;
    }
    
    /**
     * This method will create a relation between two users
     * @param fromUserId , from user of a relation
     * @param toUserId , to user of a relation
     * @param relationTypeId , type of relation between two users
     */
    public void createRelation(String fromUserId, String toUserId, String relationTypeId)
    {
        try
        {            
            MongoCollection<RelationsDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.RELATIONS.toString(), RelationsDAO.class);

            RelationInfo fromRelationInfo = new RelationInfo();
            fromRelationInfo.setUserId(toUserId);
            fromRelationInfo.setIsInitiated(PropertyProvider.get("IS_INITIATED_YES"));
            fromRelationInfo.setRelationTypeId(relationTypeId);
            fromRelationInfo.setCreatedOn(utility.getCurrentTime());

            RelationInfo toRelationInfo = new RelationInfo();
            toRelationInfo.setUserId(fromUserId);
            toRelationInfo.setIsInitiated(PropertyProvider.get("IS_INITIATED_NO"));
            toRelationInfo.setRelationTypeId(relationTypeId);
            toRelationInfo.setCreatedOn(utility.getCurrentTime());

            String attrUserId = PropertyProvider.get("USER_ID");
            String attrRelationList = PropertyProvider.get("RELATION_LIST");
            
            BasicDBObject fromFindQuery = (BasicDBObject) QueryBuilder.start(attrUserId).is(fromUserId).get();
            Document fromProjectionQuery = new Document();
            fromProjectionQuery.put(attrRelationList, new Document("$elemMatch", new Document(attrUserId, toUserId)));
            RelationsDAO fromRelationInfoCursor = mongoCollection.find(fromFindQuery).projection(fromProjectionQuery).first();
            
            BasicDBObject toFindQuery = (BasicDBObject) QueryBuilder.start(attrUserId).is(toUserId).get();            
            Document toProjectionQuery = new Document();
            toProjectionQuery.put(attrRelationList, new Document("$elemMatch", new Document(attrUserId, fromUserId)));
            RelationsDAO toRelationInfoCursor = mongoCollection.find(toFindQuery).projection(toProjectionQuery).first();
            
            if(fromRelationInfoCursor != null)
            {
                List fromRelationList = fromRelationInfoCursor.getFriendList();
                int fromRelationListSize = fromRelationList.size();
                for (int i = 0; i < fromRelationListSize; i++) {
                    RelationInfo currentRelationInfo = (RelationInfo)fromRelationList.get(i);
                    if (currentRelationInfo.getUserId().equals(toUserId)) {
                        //duplication request sent
                        if(currentRelationInfo.getRelationTypeId().equals(relationTypeId))
                        {
                            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_RELATION_DUPLICATE_CREATION"));
                        }
                        //relation already exists
                        else
                        {
                            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_RELATION_EXISTS_BEFORE_CREATION"));
                        }
                        return; 
                    }
                }
                //appending a new relation
                mongoCollection.findOneAndUpdate(fromFindQuery, new Document("$push", new Document(attrRelationList, JSON.parse(fromRelationInfo.toString()))));
            }
            else
            {
                //inserting the first relation
                List<RelationInfo> relationList = new ArrayList<>();
                relationList.add(fromRelationInfo);
                RelationsDAO fromRelationDAO = new RelationsDAOBuilder()
                        .setUserId(fromUserId)
                        .setFriendList(relationList)
                        .build();
                mongoCollection.insertOne(fromRelationDAO);
            }
            if(toRelationInfoCursor != null)
            {
                List toRelationList = toRelationInfoCursor.getFriendList();
                int toRelationListSize = toRelationList.size();
                for (int i = 0; i < toRelationListSize; i++) {
                    RelationInfo currentRelationInfo = (RelationInfo)toRelationList.get(i);
                    if (currentRelationInfo.getUserId().equals(fromUserId)) {
                        if(currentRelationInfo.getRelationTypeId().equals(relationTypeId))
                        {
                            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_RELATION_DUPLICATE_CREATION"));
                        }
                        else
                        {
                            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_RELATION_EXISTS_BEFORE_CREATION"));
                        }
                        return; 
                    }
                }
                //appending a new relation
                mongoCollection.findOneAndUpdate(toFindQuery, new Document("$push", new Document(attrRelationList, JSON.parse(toRelationInfo.toString()))));
            }
            else
            {
                //inserting the first relation
                List<RelationInfo> relationList = new ArrayList<>();
                relationList.add(toRelationInfo);
                RelationsDAO toRelationDAO = new RelationsDAOBuilder()
                        .setUserId(toUserId)
                        .setFriendList(relationList)
                        .build();
                mongoCollection.insertOne(toRelationDAO);
            }            
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        }
        catch(Exception ex)
        {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
    }
    
    /**
     * This method will update relation between two users
     * @param fromUserId, from user id of a relation
     * @param toUserId, to user id of a relation
     * @param relationTypeId, relation type id between two users
     */
    public void updateRelation(String fromUserId, String toUserId, String relationTypeId)
    {
        try
        {
            MongoCollection<RelationsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELATIONS.toString(), RelationsDAO.class);

            String attrUserId = PropertyProvider.get("USER_ID");
            String attrRelationList = PropertyProvider.get("RELATION_LIST");
            String attrIsInitiated = PropertyProvider.get("IS_INITIATED");
            String attrRelationTypeId = PropertyProvider.get("RELATION_TYPE_ID");

            Document fromSelectionDocument = new Document();
            fromSelectionDocument.put(attrUserId, fromUserId);
            fromSelectionDocument.put(attrRelationList+"."+attrUserId, toUserId);

            Document toSelectionDocument = new Document();
            toSelectionDocument.put(attrUserId, toUserId);
            toSelectionDocument.put(attrRelationList+"."+attrUserId, fromUserId);

            Document fromUpdatedDocument = new Document();
            fromUpdatedDocument.put(attrRelationList+".$."+attrRelationTypeId, relationTypeId);
            fromUpdatedDocument.put(attrRelationList+".$."+attrIsInitiated, PropertyProvider.get("IS_INITIATED_YES"));

            Document toUpdatedDocument = new Document();
            toUpdatedDocument.put(attrRelationList+".$."+attrRelationTypeId, relationTypeId);
            toUpdatedDocument.put(attrRelationList+".$."+attrIsInitiated, PropertyProvider.get("IS_INITIATED_NO"));

            mongoCollection.findOneAndUpdate(fromSelectionDocument, new Document("$set", fromUpdatedDocument));
            mongoCollection.findOneAndUpdate(toSelectionDocument, new Document("$set", toUpdatedDocument));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        }
        catch(Exception ex)
        {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
    }
    
    /**
     * This method will delete a relation between two users
     * @param fromUserId, from user id of a relation
     * @param toUserId, to user id of a relation
     */
    public void deleteRelation(String fromUserId, String toUserId)
    {
        try
        {
            MongoCollection<RelationsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELATIONS.toString(), RelationsDAO.class);
            
            String attrUserId = PropertyProvider.get("USER_ID");
            String attrRelationList = PropertyProvider.get("RELATION_LIST");

            Document fromSelectionDocument = new Document();
            fromSelectionDocument.put(attrUserId, fromUserId);
            fromSelectionDocument.put(attrRelationList+"."+attrUserId, toUserId);

            Document toSelectionDocument = new Document();
            toSelectionDocument.put(attrUserId, toUserId);
            toSelectionDocument.put(attrRelationList+"."+attrUserId, fromUserId);

            Document fromRemovedDocument = new Document();
            fromRemovedDocument.put(attrUserId, toUserId);

            Document toRemovedDocument = new Document();
            toRemovedDocument.put(attrUserId, fromUserId);

            mongoCollection.findOneAndUpdate(fromSelectionDocument, new Document("$pull", new Document(attrRelationList, fromRemovedDocument)));
            mongoCollection.findOneAndUpdate(toSelectionDocument, new Document("$pull", new Document(attrRelationList, toRemovedDocument)));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        }
        catch(Exception ex)
        {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
    }
    
    /**
     * This method will return relation list of a user
     * @param userId, user id
     * @param relationTypeId, type of relation
     * @param offset, offset
     * @param limit, limit
     * @return List, list of relation info
     */
    public List<RelationInfo> getRelationList(String userId, String relationTypeId, int offset, int limit)
    {
        //right now some dummy data is returned
        List<RelationInfo> relationList = new ArrayList<>();
        RelationInfo relationInfo = new RelationInfo();
        relationInfo.setUserId("1");
        relationInfo.setFristName("a");
        relationInfo.setLastName("b");
        relationList.add(relationInfo);
        return relationList;
    }
    
    /**
     * This method will return relation type of two users
     * @param fromUserId, from user id of a relation
     * @param toUserId, to user id of a relation
     * @return String, relation type id between two users
     */
    public String getRelationType(String fromUserId, String toUserId)
    {
        //right now some dummy data is returned
        return PropertyProvider.get("RELATION_TYPE_FRIEND_ID");
    }
}