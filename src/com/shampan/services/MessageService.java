package com.shampan.services;

import com.shampan.model.MessageModel;

/**
 *
 * @author nazmul hasan
 */
public class MessageService {

    private static MessageModel messageModel = new MessageModel();

    public MessageService() {

    }

    /**
     * This method will add a message
     *
     * @return String
     */
    public static String addMessage(String userIdList, String senderId, String message) {
        return messageModel.addMessage(userIdList, senderId, message).toString();
    }

    /**
     * This method will return message summary list of a user
     *
     * @param userId, user id
     * @param offset, offset
     * @param limit, limit
     * @return String
     */
    public static String getMessageSummaryList(String userId, int offset, int limit) {
        return messageModel.getMessageSummaryList(userId, offset, limit).toString();
    }

    /**
     * This method will return message list of a group
     *
     * @param groupId, group id
     * @param offset, offset
     * @param limit, limit
     * @return String
     */
    public static String getMessageList(String groupId, int offset, int limit) {
        return messageModel.getMessageList(groupId, offset, limit).toString();
    }
}
