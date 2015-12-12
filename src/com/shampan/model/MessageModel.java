package com.shampan.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;
import com.sampan.response.ResultEvent;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.MessageDAO;
import com.shampan.db.collections.MessageDetailsDAO;
import com.shampan.db.collections.NotificationDAO;
import com.shampan.db.collections.UserDAO;
import com.shampan.db.collections.builder.MessageDAOBuilder;
import com.shampan.db.collections.builder.MessageDetailsDAOBuilder;
import com.shampan.db.collections.fragment.common.UserInfo;
import com.shampan.db.collections.fragment.message.Messages;
import com.shampan.util.PropertyProvider;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author nazmul hasan
 */
public class MessageModel {

    private ResultEvent resultEvent = new ResultEvent();
    UserModel userModel = new UserModel();

    public MessageModel() {

    }

    /**
     * This method will return result event
     *
     * @return ResultEvent, result event
     */
    public ResultEvent getResultEvent() {
        return resultEvent;
    }

    /**
     * This method will set result event
     *
     * @param resultEvent, result event
     */
    public void setResultEvent(ResultEvent resultEvent) {
        this.resultEvent = resultEvent;
    }

    /**
     * This method will add a message
     */
    public String addMessage(String userIdList, String senderId, String message) {
        try {
            MongoCollection<MessageDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.MESSAGES.toString(), MessageDAO.class);

            JSONArray userIds = new JSONArray(userIdList);
            int userIdsSize = userIds.length();
            List<UserDAO> userListInfo = userModel.getUserInfoList(userIdList);
            UserInfo senderUserInfo = new UserInfo();
            if (userIdsSize > 0) {
                String groupId = "_";
                List<UserInfo> userList = new ArrayList<>();
                for (int i = 0; i < userIdsSize; i++) {
                    groupId = groupId + userIds.get(i) + "_";
                    for (int j = 0; j < userListInfo.size(); j++) {
                        if (userIds.get(i).equals(userListInfo.get(j).getUserId())) {
                            UserInfo userInfo = new UserInfo();
                            userInfo.setUserId(userListInfo.get(j).getUserId());
                            userInfo.setFirstName(userListInfo.get(j).getFirstName());
                            userInfo.setLastName(userListInfo.get(j).getLastName());
                            userInfo.setGenderId(userListInfo.get(j).getGender().getGenderId());
                            userList.add(userInfo);
                        }

                        if (senderId.equals(userListInfo.get(j).getUserId())) {
                            senderUserInfo.setUserId(senderId);
                            senderUserInfo.setFirstName(userListInfo.get(j).getFirstName());
                            senderUserInfo.setLastName(userListInfo.get(j).getLastName());
                            senderUserInfo.setGenderId(userListInfo.get(j).getGender().getGenderId());

                        }

                    }

                }
                Messages userMessage = new Messages();
                userMessage.setMessage(message);
                userMessage.setSenderInfo(senderUserInfo);

                Document selectDocument = new Document();
                selectDocument.put("groupId", groupId);
                MessageDAO messageCursor = mongoCollection.find(selectDocument).first();
                if (messageCursor != null) {
                    updateMessageSummaryInfo(selectDocument, message);
                    updateItemMessageList(groupId, userMessage);
                } else {
                    createMessageSummaryInfo(groupId, userList, message);
                    createItemMessageList(groupId, userMessage);
                }
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent.toString();
    }

    public String addMessageByGroupId(String groupId, String senderUserInfo, String message) {
        try {
            MongoCollection<MessageDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.MESSAGES.toString(), MessageDAO.class);
            UserInfo senderInfo = UserInfo.getUserInformation(senderUserInfo);
            Messages userMessage = new Messages();
            userMessage.setMessage(message);
            userMessage.setSenderInfo(senderInfo);
            Document selectDocument = new Document();
            selectDocument.put("groupId", groupId);
            MessageDAO messageCursor = mongoCollection.find(selectDocument).first();
            updateMessageSummaryInfo(selectDocument, message);
            updateItemMessageList(groupId, userMessage);
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent.toString();
    }

    public String getMessageHistory(String groupId) {
        MongoCollection<MessageDetailsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.MESSAGESDETAILS.toString(), MessageDetailsDAO.class);

        Document selectDocument = new Document();
        selectDocument.put("groupId", groupId);
        MessageDetailsDAO messageSummery = mongoCollection.find(selectDocument).first();
        return messageSummery.toString();
    }

    /**
     * This method will create a new message summary info
     */
    public void createMessageSummaryInfo(String groupId, List<UserInfo> userList, String message) {
        try {
            MongoCollection<MessageDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.MESSAGES.toString(), MessageDAO.class);
            MessageDAO messageInfo = new MessageDAOBuilder()
                    .setGroupId(groupId)
                    .setUserList(userList)
                    .setLatestMessage(message)
                    .build();
            mongoCollection.insertOne(messageInfo);
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
    }

    /**
     * This method will update message summary info
     */
    public void updateMessageSummaryInfo(Document selectDocument, String message) {
        try {
            MongoCollection<MessageDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.MESSAGES.toString(), MessageDAO.class);
            Document updatedDocument = new Document();
            updatedDocument.put("latestMessage", message);
            mongoCollection.findOneAndUpdate(selectDocument, new Document("$set", updatedDocument));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
    }

    /**
     * This method will create a new message list with an item
     */
    public void createItemMessageList(String groupId, Messages userMessage) {
        try {
            MongoCollection<MessageDetailsDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.MESSAGESDETAILS.toString(), MessageDetailsDAO.class);
            List<Messages> messageList = new ArrayList<>();
            messageList.add(userMessage);
            MessageDetailsDAO messageInfoDAO = new MessageDetailsDAOBuilder()
                    .setGroupId(groupId)
                    .setMessages(messageList)
                    .build();
            mongoCollection.insertOne(messageInfoDAO);
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
    }

    /**
     * This method will update message list of a group
     */
    public void updateItemMessageList(String groupId, Messages userMessage) {
        try {
            MongoCollection<MessageDetailsDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.MESSAGESDETAILS.toString(), MessageDetailsDAO.class);

            Document selectDocument = new Document();
            selectDocument.put("groupId", groupId);

            mongoCollection.findOneAndUpdate(selectDocument, new Document("$push", new Document("messages", JSON.parse(userMessage.toString()))));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
    }

    /**
     * This method will return message summary list of a user
     *
     * @param userId, user id
     * @param offset, offset
     * @param limit, limit
     */
    public JSONObject getMessageSummaryList(String userId, int offset, int limit) {
        MongoCollection<MessageDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.MESSAGES.toString(), MessageDAO.class);
        Document regexDocument = new Document();
        regexDocument.put("$regex", userId);
        Document selectDocument = new Document();
        selectDocument.put("groupId", regexDocument);
        MongoCursor<MessageDAO> messageCursor = mongoCollection.find(selectDocument).skip(offset).limit(limit).iterator();
        List<MessageDAO> messageSummeryList = IteratorUtils.toList(messageCursor);
        MessageDetailsDAO recentMessageInfo = getMessageList(messageSummeryList.get(0).getGroupId(), offset, limit);
        JSONObject messageInfo = new JSONObject();
        messageInfo.put("messageSummeryList", messageSummeryList);
        messageInfo.put("recentMessageInfo",recentMessageInfo);
        return messageInfo;

    }

    /**
     * This method will return message list of a group
     *
     * @param groupId, id of a group
     * @param offset, offset
     * @param limit, limit
     */
    public MessageDetailsDAO getMessageList(String groupId, int offset, int limit) {
        MongoCollection<MessageDetailsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.MESSAGESDETAILS.toString(), MessageDetailsDAO.class);
        Document selectDocument = new Document();
        selectDocument.put("groupId", groupId);
        Document sortDocument = new Document();
        Document projectionDocument = new Document();
        projectionDocument.put("messages", "$all");
        MessageDetailsDAO messageDetailsCursor = mongoCollection.find(selectDocument).first();
        return messageDetailsCursor;
    }
}
