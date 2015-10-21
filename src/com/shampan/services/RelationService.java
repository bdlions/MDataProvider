package com.shampan.services;

import com.sampan.response.ResultEvent;
import com.shampan.db.collections.fragment.relation.RelationInfo;
import com.shampan.model.RelationModel;
import com.shampan.util.PropertyProvider;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nazmul hasan
 */
public class RelationService {
    private static RelationModel relationModel = new RelationModel();
    public RelationService()
    {
        
    }
    /**
     * This method will add a friend relation
     * @param fromUserId, from user id of a friend relation
     * @param toUserId, to user id of a friend relation
     * @return ResultEvent, result of the operation
     */
    public static String addFriend(String fromUserId, String toUserId)
    {
        return relationModel.addFriend(fromUserId, toUserId).toString();
    }
    /**
     * This method will add a block relation
     * @param fromUserId, from user id of a block relation
     * @param toUserId, to user id of a block relation
     * @return ResultEvent, result of the operation
     */
    public static String blockNonFriend(String fromUserId, String toUserId)
    {
        return relationModel.blockNonFriend(fromUserId, toUserId).toString();
    }
    /**
     * This method will update block relation
     * @param fromUserId, from user id of a block relation
     * @param toUserId, to user id of a block relation
     * @return ResultEvent, result of the operation
     */
    public static String blockFriend(String fromUserId, String toUserId)
    {
        return relationModel.blockFriend(fromUserId, toUserId).toString();
    }
    /**
     * This method will approve a friend relation
     * @param fromUserId, from user id of a friend relation
     * @param toUserId, to user id of a friend relation
     * @return ResultEvent, result of the operation
     */
    public static String approveFriend(String fromUserId, String toUserId)
    {
        return relationModel.approveFriend(fromUserId, toUserId).toString();
    }
    /**
     * This method will remove friend relation
     * @param fromUserId, from user id of a friend relation
     * @param toUserId, to user id of a friend relation
     * @return ResultEvent, result of the operation
     */
    public static String removeFriendRequest(String fromUserId, String toUserId)
    {
        return relationModel.removeFriendRequest(fromUserId, toUserId).toString();
    }
    /**
     * This method will remove block relation
     * @param fromUserId, from user id of a block relation
     * @param toUserId, to user id of a block relation
     * @return ResultEvent, result of the operation
     */
    public static String unblockFriend(String fromUserId, String toUserId)
    {
        return relationModel.unblockFriend(fromUserId, toUserId).toString();
    }
    /**
     * This method will remove friend relation
     * @param fromUserId, from user id of a friend relation
     * @param toUserId, to user id of a friend relation
     * @return ResultEvent, result of the operation
     */
    public static String removeFriend(String fromUserId, String toUserId)
    {
        return relationModel.removeFriend(fromUserId, toUserId).toString();
    }
    /**
     * This method will return relation list of a user
     * @param userId, user id
     * @param relationTypeId, type of relation
     * @param stringOffset, offset
     * @param stringLimit, limit
     * @return List, list of relation info
     */
    public static String getRelationList(String userId, String relationTypeId, String stringOffset, String stringLimit)
    {
        PropertyProvider.add("com.shampan.properties/relations");
        PropertyProvider.add("com.shampan.properties/response");
        ResultEvent resultEvent = new ResultEvent();
        List<RelationInfo> relationList = new ArrayList<>();
        int offset = 0;
        int limit = 0;
        try
        {
            offset = Integer.parseInt(stringOffset);
        }
        catch(Exception ex)
        {
            resultEvent.setResponseCode(PropertyProvider.get("ERROR_RELATION_INVALID_OFFSET"));
            resultEvent.setResult(relationList);
            return resultEvent.toString();
        }
        try
        {
            limit = Integer.parseInt(stringLimit);
        }
        catch(Exception ex)
        {
            resultEvent.setResponseCode(PropertyProvider.get("ERROR_RELATION_INVALID_LIMIT"));
            resultEvent.setResult(relationList);
            return resultEvent.toString();
        }
        relationList = relationModel.getRelationList(userId, relationTypeId, offset, limit);
        resultEvent.setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        resultEvent.setResult(relationList);
        return resultEvent.toString();
    }
    /**
     * This method will return relation type of two users
     * @param fromUserId, from user id of a relation
     * @param toUserId, to user id of a relation
     * @return String, relation type id between two users
     */
    public static String getRelationType(String fromUserId, String toUserId)
    {
        return relationModel.getRelationType(fromUserId, toUserId);
    }
    
    public static void main(String args[])
    {
        //System.out.println(getRelationList("1", "1", "0", "1"));
    }
}
