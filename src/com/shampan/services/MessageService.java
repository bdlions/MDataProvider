package com.shampan.services;

import com.shampan.model.MessageModel;

/**
 *
 * @author nazmul hasan
 */
public class MessageService {
    private static MessageModel messageModel = new MessageModel();
    public MessageService()
    {
    
    }
    
    /**
     * This method will add a message
     * @return String
     */
    public static String addMessage()
    {
        messageModel.addMessage();
        return "";
    }
    
    /**
     * This method will return message summary list of a user
     * @param userId, user id
     * @param offset, offset
     * @param limit, limit
     * @return String
     */
    public static String getMessageSummaryList(String userId, String offset, String limit)
    {
        messageModel.getMessageList(userId, offset, limit);
        return "";
    }
    
    /**
     * This method will return message list of a group
     * @param groupId, group id
     * @param offset, offset
     * @param limit, limit
     * @return String
     */
    public static String getMessageList(String groupId, String offset, String limit)
    {
        messageModel.getMessageList(groupId, offset, limit);
        return "";
    }
}
