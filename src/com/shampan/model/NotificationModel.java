package com.shampan.model;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;
import com.sampan.response.ResultEvent;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.NotificationDAO;
import com.shampan.db.collections.UserDAO;
import com.shampan.db.collections.builder.NotificationDAOBuilder;
import com.shampan.db.collections.fragment.common.UserInfo;
import com.shampan.db.collections.fragment.notification.FriendNotification;
import com.shampan.db.collections.fragment.notification.GeneralNotification;
import com.shampan.util.PropertyProvider;
import com.shampan.util.Utility;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.json.JSONObject;

/**
 *
 * @author nazmul hasan
 */
public class NotificationModel {

    private ResultEvent resultEvent = new ResultEvent();
    Utility utility = new Utility();
    UserModel userModel = new UserModel();

    public NotificationModel() {

        PropertyProvider.add("com.shampan.properties/relations");
        PropertyProvider.add("com.shampan.properties/response");
        PropertyProvider.add("com.shampan.properties/attributes");
        PropertyProvider.add("com.shampan.properties/notification");

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
     * This method will return all notification counter of a user
     *
     * @param userId, user id of a user
     * @return void
     */
    public String getNotificationCounter(String userId) {
        MongoCollection<NotificationDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.NOTIFICATIONS.toString(), NotificationDAO.class);
        String attrUserId = PropertyProvider.get("USER_ID");
        String attrStatusId = PropertyProvider.get("STATUS_ID");
        String statusId = PropertyProvider.get("NOTIFICATION_STATUS_TYPE_UNREAD_ID");
        String attrGeneralNotifications = PropertyProvider.get("GENERAL_NOTIFICATIONS");
        String attrFriendNotifications = PropertyProvider.get("FRIEND_NOTIFICATIONS");
        Document selectionDocument = new Document();
        selectionDocument.put(attrUserId, userId);
        Document projectionDocument = new Document();
        projectionDocument.put(attrGeneralNotifications, "$all");
        projectionDocument.put(attrFriendNotifications, "$all");
        NotificationDAO notificationCursor = mongoCollection.find(selectionDocument).projection(projectionDocument).first();
        int generalCounter = 0;
        int friendCounter = 0;
        if (notificationCursor != null) {
            if (notificationCursor.getGeneralNotifications() != null) {
                int generalNotificationSize = notificationCursor.getGeneralNotifications().size();
                if (generalNotificationSize > 0) {
                    for (int i = 0; i < generalNotificationSize; i++) {
                        if (notificationCursor.getGeneralNotifications().get(i).getStatusId().equals(statusId)) {
                            generalCounter++;
                        }
                    }
                }
            }
            if (notificationCursor.getFriendNotifications() != null) {
                int friendNotificationCounter = notificationCursor.getFriendNotifications().size();
                if (friendNotificationCounter > 0) {
                    for (int j = 0; j < friendNotificationCounter; j++) {
                        if (notificationCursor.getFriendNotifications().get(j).getStatusId().equals(statusId)) {
                            friendCounter++;
                        }
                    }
                }
            }
        }
        JSONObject resultedNotifications = new JSONObject();
        JSONObject userInitiation = new JSONObject();
        userInitiation.put("friend", friendCounter);
        userInitiation.put("message", "5");
        userInitiation.put("general", generalCounter);
        userInitiation.put("userCurrentTimeStamp", utility.getCurrentTime());
        userInitiation.put("genderId", userModel.getUserGenderInfo(userId));
        resultedNotifications.put("userInitiationInfo", userInitiation.toString());

        return resultedNotifications.toString();
    }

    public ResultEvent addFriendPendingNotification(String userId, String friendId) {
        String typeId = PropertyProvider.get("NOTIFICATION_TYPE_PENDING_REQUEST");
        addFriendNotification(userId, friendId, typeId);
        return this.resultEvent;

    }

    public ResultEvent addFriendAcceptNotification(String userId, String friendId) {
        String typeId = PropertyProvider.get("NOTIFICATION_TYPE_ACCEPT_REQUEST");
        addFriendNotification(userId, friendId, typeId);
        return this.resultEvent;

    }

    /**
     * This method will add friend relation notification
     *
     * @param userId, user id of a friend relation who initiate request
     * @param friendId, friend id of a friend relation who get request
     * @return void
     */
    public void addFriendNotification(String userId, String friendId, String type) {
        try {
            MongoCollection<NotificationDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.NOTIFICATIONS.toString(), NotificationDAO.class);
            String attrUserId = PropertyProvider.get("USER_ID");
            String attrFriendNotifications = PropertyProvider.get("FRIEND_NOTIFICATIONS");
            String statusId = PropertyProvider.get("NOTIFICATION_STATUS_TYPE_UNREAD_ID");
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            FriendNotification friendNotification = new FriendNotification();
            friendNotification.setStatusId(statusId);
            friendNotification.setUserInfo(userInfo);
            friendNotification.setTypeId(type);
            BasicDBObject findQuery = (BasicDBObject) QueryBuilder.start(attrUserId).is(friendId).get();
            Document fromProjectionQuery = new Document();
            fromProjectionQuery.put(attrFriendNotifications, "$all");
            NotificationDAO friendNotificationCursor = mongoCollection.find(findQuery).projection(fromProjectionQuery).first();
            if (friendNotificationCursor != null) {
                mongoCollection.findOneAndUpdate(findQuery, new Document("$push", new Document(attrFriendNotifications, JSON.parse(friendNotification.toString()))));
            } else {
                List<FriendNotification> notificationList = new ArrayList<>();
                notificationList.add(friendNotification);
                NotificationDAO notificationDAO = new NotificationDAOBuilder()
                        .setUserId(friendId)
                        .setFriendNotifications(notificationList)
                        .build();
                mongoCollection.insertOne(notificationDAO);
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
    }

    /**
     * This method will return a user pending friend list
     *
     * @param userId, user id of a user
     * @return NotificationDAO
     */
    public NotificationDAO getPendingFriendList(String userId) {
        MongoCollection<NotificationDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.NOTIFICATIONS.toString(), NotificationDAO.class);
        String attrUserId = PropertyProvider.get("USER_ID");
        String attrFriendNotifications = PropertyProvider.get("FRIEND_NOTIFICATIONS");
        Document selectionDocument = new Document();
        selectionDocument.put(attrUserId, userId);
        Document projectionDocument = new Document();
        projectionDocument.put(attrFriendNotifications, "$all");
        NotificationDAO pendingFriendList = mongoCollection.find(selectionDocument).projection(projectionDocument).first();
        return pendingFriendList;
    }

    /**
     * This method will update status type unread to read status type
     *
     * @param userId, user id of a user
     * @return void
     */
    public void updateStatusFriendNotifications(String userId) {
        try {
            MongoCollection<NotificationDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.NOTIFICATIONS.toString(), NotificationDAO.class);
            String attrUserId = PropertyProvider.get("USER_ID");
            String attrFriendNotifications = PropertyProvider.get("FRIEND_NOTIFICATIONS");
            String unReadStatusId = PropertyProvider.get("NOTIFICATION_STATUS_TYPE_UNREAD_ID");
            String readStatusId = PropertyProvider.get("NOTIFICATION_STATUS_TYPE_READ_ID");
            String typeId = PropertyProvider.get("NOTIFICATION_TYPE_ACCEPT_REQUEST");
            Document selectionDocument = new Document();
            selectionDocument.put(attrUserId, userId);
            Document removeDocument = new Document();
            removeDocument.put("typeId", typeId);
            Document selectRemoveDocument = new Document();
            selectRemoveDocument.put("userId", userId);
            selectRemoveDocument.put(attrFriendNotifications + ".typeId", typeId);
            NotificationDAO friendNotification = getPendingFriendList(userId);
            List<FriendNotification> friendNotifications = new ArrayList<>();
            if (friendNotification.getFriendNotifications() != null) {
                int pendingfriendSize = friendNotification.getFriendNotifications().size();
                for (int i = 0; i < pendingfriendSize; i++) {
                    if (friendNotification.getFriendNotifications().get(i).getStatusId().equals(unReadStatusId)) {
                        friendNotification.getFriendNotifications().get(i).setStatusId(readStatusId);
                        if (! friendNotification.getFriendNotifications().get(i).getTypeId().equals(typeId)) {
                            FriendNotification notification = new FriendNotification();
                            notification = friendNotification.getFriendNotifications().get(i);
                            friendNotifications.add(notification);
                        }
                    }
                }
            }
            mongoCollection.findOneAndUpdate(selectionDocument, new Document("$set", new Document(attrFriendNotifications, JSON.parse(friendNotifications.toString()))));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
    }

    /**
     * This method will return pending friend list of a user
     *
     * @param userId, user id of a user
     * @return userInfo
     */
    public List<FriendNotification> getFriendNotifications(String userId) {
        MongoCollection<NotificationDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.NOTIFICATIONS.toString(), NotificationDAO.class);
        String attrUserId = PropertyProvider.get("USER_ID");
        Document selectionDocument = new Document();
        selectionDocument.put(attrUserId, userId);
        NotificationDAO friendNotificationCursor = getPendingFriendList(userId);
        List<FriendNotification> friendNotifications = new ArrayList<>();
        List<UserInfo> userList = new ArrayList<>();
        List<String> userIdList = new ArrayList<>();
        if (friendNotificationCursor != null) {
            if (friendNotificationCursor.getFriendNotifications() != null) {
                int friendNotificationSize = friendNotificationCursor.getFriendNotifications().size();
                if (friendNotificationSize > 0) {
                    for (int i = 0; i < friendNotificationSize; i++) {
                        FriendNotification notification = new FriendNotification();
                        notification = friendNotificationCursor.getFriendNotifications().get(i);
                        friendNotifications.add(notification);
                        userIdList.add(friendNotificationCursor.getFriendNotifications().get(i).getUserInfo().getUserId());
                    }
                }
            }
        }
        if (userIdList.size() > 0) {
            List<UserDAO> userInfo = userModel.getUserInfoList(userIdList.toString());
            if (userInfo != null) {
                for (int j = 0; j < userInfo.size(); j++) {
                    if (friendNotifications.get(j) != null) {
                        UserInfo userInfo1 = new UserInfo();
                        userInfo1.setUserId(userInfo.get(j).getUserId());
                        userInfo1.setFirstName(userInfo.get(j).getFirstName());
                        userInfo1.setLastName(userInfo.get(j).getLastName());
                        friendNotifications.get(j).setUserInfo(userInfo1);
                    }
                }
            }
        }
        return friendNotifications;
    }

    /**
     * This method will remove friend relation notification when accept,delete
     * or cancel request
     *
     * @param userId, user id of a friend relation who do the action
     * @param friendId, friend id of a friend relation who related to this
     * request
     * @return void
     */
    public void deleteFriendNotification(String userId, String friendId) {
        try {
            MongoCollection<NotificationDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.NOTIFICATIONS.toString(), NotificationDAO.class);
            String attrUserId = PropertyProvider.get("USER_ID");
            String attrFriendNotifications = PropertyProvider.get("FRIEND_NOTIFICATIONS");
            String attrUserInfo = PropertyProvider.get("USER_INFO");
            Document selectionDocumentForUser = new Document();
            selectionDocumentForUser.put(attrUserId, userId);
            selectionDocumentForUser.put(attrFriendNotifications + "." + attrUserInfo + "." + attrUserId, friendId);
            Document selectionDocumentForFriend = new Document();
            selectionDocumentForFriend.put(attrUserId, friendId);
            selectionDocumentForFriend.put(attrFriendNotifications + "." + attrUserInfo + "." + attrUserId, userId);
            List<Document> orSelectionDocument = new ArrayList<Document>();
            orSelectionDocument.add(selectionDocumentForUser);
            orSelectionDocument.add(selectionDocumentForFriend);
            Document selectDocument = new Document();
            selectDocument.put("$or", orSelectionDocument);

            Document removedDocumentForUser = new Document();
            removedDocumentForUser.put(attrUserInfo + "." + attrUserId, friendId);
            Document removedDocumentForFriend = new Document();
            removedDocumentForFriend.put(attrUserInfo + "." + attrUserId, userId);
            List<Document> orRemoveDocument = new ArrayList<Document>();
            orRemoveDocument.add(removedDocumentForUser);
            orRemoveDocument.add(removedDocumentForFriend);
            Document removedDocument = new Document();
            removedDocument.put("$or", orRemoveDocument);
            mongoCollection.findOneAndUpdate(selectDocument, new Document("$pull", new Document(attrFriendNotifications, removedDocument)));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }

    }

    /**
     * This method will add status comment notification
     *
     * @param userId, user id of a friend relation who initiate status
     * @param referenceId, reference id is the status id
     * @param userInfomation, userInfomation of the user for which notification
     * is added
     * @return void
     */
    public void addGeneralNotificationStatusComment(String referenceUserInfo, String referenceId, String userInfomation) {
        try {
            MongoCollection<NotificationDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.NOTIFICATIONS.toString(), NotificationDAO.class);

            String attrUserId = PropertyProvider.get("USER_ID");
            String attrReferenceId = PropertyProvider.get("REFERENCE_ID");
            String attrGeneralNotifications = PropertyProvider.get("GENERAL_NOTIFICATIONS");
            String attrUserList = PropertyProvider.get("USER_LIST");
            String attrStatusId = PropertyProvider.get("STATUS_ID");
            String attrTypeId = PropertyProvider.get("TYPE_ID");
            String unReadStatusId = PropertyProvider.get("NOTIFICATION_STATUS_TYPE_UNREAD_ID");

            String readStatusId = PropertyProvider.get("NOTIFICATION_STATUS_TYPE_READ_ID");
            String typeId = PropertyProvider.get("NOTIFICATION_TYPE_POST_COMMENT");
            UserInfo refUserInfo = UserInfo.getUserInformation(referenceUserInfo);
            UserInfo userInfo = UserInfo.getUserInformation(userInfomation);
            if (userInfo != null && refUserInfo != null) {
                String userId = refUserInfo.getUserId();
                Document userSelectionDocument = new Document();
                userSelectionDocument.put(attrUserId, userId);
                Document userprojectionDocument = new Document();
                userprojectionDocument.put(attrUserId, "$all");

                NotificationDAO userNotificationCursor = mongoCollection.find(userSelectionDocument).projection(userprojectionDocument).first();

                if (userNotificationCursor != null) {
                    //check has a notification for unique status and type
                    Document selectionDocument = new Document();
                    selectionDocument.put(attrUserId, userId);
                    selectionDocument.put(attrGeneralNotifications + "." + attrReferenceId, referenceId);
                    selectionDocument.put(attrGeneralNotifications + "." + attrTypeId, typeId);

                    Document projectionSelectionDocument = new Document();
                    projectionSelectionDocument.put(attrReferenceId, referenceId);
                    projectionSelectionDocument.put(attrTypeId, typeId);
                    Document projectionQuery = new Document();
                    projectionQuery.put(attrGeneralNotifications, new Document("$elemMatch", projectionSelectionDocument));

                    NotificationDAO generalNotificationCursor = mongoCollection.find(selectionDocument).projection(projectionQuery).first();
                    if (generalNotificationCursor != null) {
                        List<UserInfo> userListInfo = generalNotificationCursor.getGeneralNotifications().get(0).getUserList();
                        int userSize = generalNotificationCursor.getGeneralNotifications().get(0).getUserList().size();
                        //add user to user list without request user that already in userList
                        System.out.println(userSize);
                        if (userSize > 0) {
                            // add or update status for commented users 
                            for (int i = 0; i < userSize; i++) {
                                List<UserInfo> userList = new ArrayList<>();
                                for (int j = 0; j < userSize; j++) {
                                    if (!userListInfo.get(j).getUserId().equals(userInfo.getUserId())) {
                                        userList.add(userListInfo.get(j));
                                    }
                                }
                                // add request user 
                                userList.add(userInfo);

                                // checking for the commented user has a notification document 
                                Document commentedUserSelectionDocument = new Document();
                                commentedUserSelectionDocument.put(attrUserId, userList.get(i).getUserId());
                                Document commentedUserprojectionDocument = new Document();
                                commentedUserprojectionDocument.put(attrUserId, "$all");
                                NotificationDAO commentedUserNotificationCursor = mongoCollection.find(commentedUserSelectionDocument).projection(commentedUserprojectionDocument).first();
                                if (commentedUserNotificationCursor != null) {
                                    //check has a notification for unique status and type
                                    Document commentedSelectionDocument = new Document();
                                    commentedSelectionDocument.put(attrUserId, userList.get(i).getUserId());
                                    commentedSelectionDocument.put(attrGeneralNotifications + "." + attrReferenceId, referenceId);
                                    commentedSelectionDocument.put(attrGeneralNotifications + "." + attrTypeId, typeId);

                                    Document commentedProjectionSelectionDocument = new Document();
                                    commentedProjectionSelectionDocument.put(attrReferenceId, referenceId);
                                    commentedProjectionSelectionDocument.put(attrTypeId, typeId);
                                    Document commentedProjectionQuery = new Document();
                                    commentedProjectionQuery.put(attrGeneralNotifications, new Document("$elemMatch", commentedProjectionSelectionDocument));
                                    NotificationDAO commentedgeneralNotificationCursor = mongoCollection.find(commentedSelectionDocument).projection(commentedProjectionSelectionDocument).first();
                                    if (commentedgeneralNotificationCursor != null && !userId.equals(userList.get(i).getUserId())) {

                                        Document commentedUserUpdateDocument = new Document();
                                        if (!userList.get(i).getUserId().equals(userInfo.getUserId())) {
                                            commentedUserUpdateDocument.put(attrGeneralNotifications + ".$." + attrStatusId, unReadStatusId);
                                        }
                                        commentedUserUpdateDocument.put(attrGeneralNotifications + ".$." + "modifiedOn", utility.getCurrentTime());
                                        commentedUserUpdateDocument.put(attrGeneralNotifications + ".$." + attrUserList, JSON.parse(userList.toString()));
                                        mongoCollection.findOneAndUpdate(commentedSelectionDocument, new Document("$set", commentedUserUpdateDocument));

                                    } else if (!userId.equals(userList.get(i).getUserId())) {
                                        GeneralNotification generalNotification = new GeneralNotification();
                                        if (!userList.get(i).getUserId().equals(userInfo.getUserId())) {
                                            generalNotification.setStatusId(unReadStatusId);
                                        }
                                        generalNotification.setTypeId(typeId);
                                        generalNotification.setCreatedOn(utility.getCurrentTime());
                                        generalNotification.setModifiedOn(utility.getCurrentTime());
                                        generalNotification.setReferenceId(referenceId);
                                        generalNotification.setReferenceUserInfo(refUserInfo);
                                        generalNotification.setUserList(userList);
                                        mongoCollection.findOneAndUpdate(userSelectionDocument, new Document("$push", new Document("generalNotifications", JSON.parse(generalNotification.toString()))));

                                    }

                                } else if (!userId.equals(userList.get(i).getUserId())) {
                                    //if the user enter notificaton colection first time
                                    GeneralNotification generalNotification = new GeneralNotification();
                                    generalNotification.setStatusId(unReadStatusId);
                                    generalNotification.setTypeId(typeId);
                                    generalNotification.setReferenceId(referenceId);
                                    generalNotification.setReferenceUserInfo(refUserInfo);
                                    generalNotification.setCreatedOn(utility.getCurrentTime());
                                    generalNotification.setModifiedOn(utility.getCurrentTime());
                                    generalNotification.setUserList(userList);
                                    List<GeneralNotification> notificationList = new ArrayList<>();
                                    notificationList.add(generalNotification);
                                    NotificationDAO notificationDAO = new NotificationDAOBuilder()
                                            .setUserId(userList.get(i).getUserId())
                                            .setGeneralNotifications(notificationList)
                                            .build();
                                    mongoCollection.insertOne(notificationDAO);

                                }
                            }

                            //notification who  post the status 
                            //entry for the user if this satus notification  exsist 
                            List<UserInfo> userList = new ArrayList<>();
                            for (int k = 0; k < userSize; k++) {
                                if (!userListInfo.get(k).getUserId().equals(userInfo.getUserId())) {
                                    userList.add(userListInfo.get(k));
                                }
                            }
                            userList.add(userInfo);

                            Document updateDocument = new Document();
                            if (!userId.equals(userInfo.getUserId())) {
                                updateDocument.put(attrGeneralNotifications + ".$." + attrStatusId, unReadStatusId);
                            }
                            updateDocument.put(attrGeneralNotifications + ".$." + "modifiedOn", (utility.getCurrentTime()));
                            updateDocument.put(attrGeneralNotifications + ".$." + attrUserList, JSON.parse(userList.toString()));
                            mongoCollection.findOneAndUpdate(selectionDocument, new Document("$set", updateDocument));
                        }
                    } else if (!userId.equals(userInfo.getUserId())) {

                        //First entry for this status and type
                        List<UserInfo> userList = new ArrayList<>();
                        userList.add(userInfo);
                        GeneralNotification generalNotification = new GeneralNotification();
                        generalNotification.setStatusId(unReadStatusId);
                        generalNotification.setTypeId(typeId);
                        generalNotification.setCreatedOn(utility.getCurrentTime());
                        generalNotification.setModifiedOn(utility.getCurrentTime());
                        generalNotification.setReferenceId(referenceId);
                        generalNotification.setReferenceUserInfo(refUserInfo);
                        generalNotification.setUserList(userList);
                        mongoCollection.findOneAndUpdate(userSelectionDocument, new Document("$push", new Document("generalNotifications", JSON.parse(generalNotification.toString()))));

                    }

                } else if (!userId.equals(userInfo.getUserId())) {

                    //if the user enter notificaton colection first time
                    List<UserInfo> userList = new ArrayList<>();
                    userList.add(userInfo);
                    GeneralNotification generalNotification = new GeneralNotification();
                    generalNotification.setStatusId(unReadStatusId);
                    generalNotification.setTypeId(typeId);
                    generalNotification.setCreatedOn(utility.getCurrentTime());
                    generalNotification.setModifiedOn(utility.getCurrentTime());
                    generalNotification.setReferenceId(referenceId);
                    generalNotification.setReferenceUserInfo(refUserInfo);
                    generalNotification.getReferenceUserInfo();
                    generalNotification.setUserList(userList);
                    List<GeneralNotification> notificationList = new ArrayList<>();
                    notificationList.add(generalNotification);
                    NotificationDAO notificationDAO = new NotificationDAOBuilder()
                            .setUserId(userId)
                            .setGeneralNotifications(notificationList)
                            .build();
                    mongoCollection.insertOne(notificationDAO);

                }

                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }

    }

    /**
     * This method will add status like notification
     *
     * @param userId, user id of a friend relation who initiate status
     * @param referenceId, reference id is the status id
     * @param userInfomation, userInfomation of the user for which notification
     * is added
     * @return void
     */
    public ResultEvent addGeneralNotificationStatusLike(String userId, String referenceId, String userInfomation) {
        String typeId = PropertyProvider.get("NOTIFICATION_TYPE_POST_LIKE");
        addGeneralNotificationStatusLikeOrShare(userId, referenceId, typeId, userInfomation);
        return this.resultEvent;

    }

    /**
     * This method will add status share notification
     *
     * @param userId, user id of a friend relation who initiate status
     * @param referenceId, reference id is the status id
     * @param userInfomation, userInfomation of the user for which notification
     * is added
     * @return void
     */
    public ResultEvent addGeneralNotificationStatusShare(String userId, String referenceId, String userInfomation) {
        String typeId = PropertyProvider.get("NOTIFICATION_TYPE_POST_SHARE");
        addGeneralNotificationStatusLikeOrShare(userId, referenceId, typeId, userInfomation);
        return this.resultEvent;
    }

    /**
     * This method will add status like or share notification
     *
     * @param userId, user id of a friend relation who initiate status
     * @param referenceId, reference id is the status id
     * @param typeId, type id is the like or share id or others
     * @param userInfomation, userInfomation of the user for which notification
     * is added
     * @return void
     */
    public void addGeneralNotificationStatusLikeOrShare(String userId, String referenceId, String typeId, String userInfomation) {
        try {
            MongoCollection<NotificationDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.NOTIFICATIONS.toString(), NotificationDAO.class);

            String attrUserId = PropertyProvider.get("USER_ID");
            String attrReferenceId = PropertyProvider.get("REFERENCE_ID");
            String attrGeneralNotifications = PropertyProvider.get("GENERAL_NOTIFICATIONS");
            String attrUserList = PropertyProvider.get("USER_LIST");
            String attrStatusId = PropertyProvider.get("STATUS_ID");
            String attrTypeId = PropertyProvider.get("TYPE_ID");
            String unReadStatusId = PropertyProvider.get("NOTIFICATION_STATUS_TYPE_UNREAD_ID");
            String readStatusId = PropertyProvider.get("NOTIFICATION_STATUS_TYPE_READ_ID");
            UserInfo userInfo = UserInfo.getUserInformation(userInfomation);
            if (userInfo != null) {
                //check has a notification document for this user 
                Document userSelectionDocument = new Document();
                userSelectionDocument.put(attrUserId, userId);
                Document userprojectionDocument = new Document();
                userprojectionDocument.put(attrUserId, "$all");
                NotificationDAO userNotificationCursor = mongoCollection.find(userSelectionDocument).projection(userprojectionDocument).first();
                if (userNotificationCursor != null) {
                    //check has a notification for unique status and type
                    Document selectionDocument = new Document();
                    selectionDocument.put(attrUserId, userId);
                    selectionDocument.put(attrGeneralNotifications + "." + attrReferenceId, referenceId);
                    selectionDocument.put(attrGeneralNotifications + "." + attrTypeId, typeId);
                    Document projectionSelectionDocument = new Document();
                    projectionSelectionDocument.put(attrReferenceId, referenceId);
                    projectionSelectionDocument.put(attrTypeId, typeId);
                    Document projectionQuery = new Document();
                    projectionQuery.put(attrGeneralNotifications, new Document("$elemMatch", projectionSelectionDocument));

                    NotificationDAO generalNotificationCursor = mongoCollection.find(selectionDocument).projection(projectionQuery).first();
                    //If  has a notification for this status and type
                    if (generalNotificationCursor != null) {
                        Document updateDocument = new Document();
                        if (!userId.equals(userInfo.getUserId())) {
                            updateDocument.put("$set", new Document(attrGeneralNotifications + ".$." + attrStatusId, unReadStatusId));
                        }
                        updateDocument.put("$set", new Document(attrGeneralNotifications + ".$.modifiedOn", utility.getCurrentTime()));
                        updateDocument.put("$push", new Document(attrGeneralNotifications + ".$." + attrUserList, JSON.parse(userInfo.toString())));
                        mongoCollection.findOneAndUpdate(selectionDocument, updateDocument);
                    } else {
                        //First entry for this status and type
                        List<UserInfo> userList = new ArrayList<>();
                        userList.add(userInfo);
                        GeneralNotification generalNotification = new GeneralNotification();
                        if (!userId.equals(userInfo.getUserId())) {
                            generalNotification.setStatusId(unReadStatusId);
                        }
                        generalNotification.setTypeId(typeId);
                        generalNotification.setReferenceId(referenceId);
                        generalNotification.setUserList(userList);
                        generalNotification.setModifiedOn(utility.getCurrentTime());
                        mongoCollection.findOneAndUpdate(userSelectionDocument, new Document("$push", new Document("generalNotifications", JSON.parse(generalNotification.toString()))));
                    }

                } else {
                    //notification first entry for this user
                    List<UserInfo> userList = new ArrayList<>();
                    userList.add(userInfo);
                    GeneralNotification generalNotification = new GeneralNotification();
                    if (!userId.equals(userInfo.getUserId())) {
                        generalNotification.setStatusId(unReadStatusId);
                    } else {
                        generalNotification.setStatusId(readStatusId);
                    }
                    generalNotification.setTypeId(typeId);
                    generalNotification.setReferenceId(referenceId);
                    generalNotification.setCreatedOn(utility.getCurrentTime());
                    generalNotification.setModifiedOn(utility.getCurrentTime());
                    generalNotification.setUserList(userList);
                    List<GeneralNotification> notificationList = new ArrayList<>();
                    notificationList.add(generalNotification);
                    NotificationDAO notificationDAO = new NotificationDAOBuilder()
                            .setUserId(userId)
                            .setGeneralNotifications(notificationList)
                            .build();
                    mongoCollection.insertOne(notificationDAO);

                }
            }
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));

        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }

    }

    public void updateStatusGeneralNotifications(String userId, String StatusTypeId) {
        MongoCollection<NotificationDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.NOTIFICATIONS.toString(), NotificationDAO.class);

        String attrUserId = PropertyProvider.get("USER_ID");
        String attrGeneralNotifications = PropertyProvider.get("GENERAL_NOTIFICATIONS");
        String attrUserList = PropertyProvider.get("USER_LIST");
        String attrStatusId = PropertyProvider.get("STATUS_ID");
        String attrTypeId = PropertyProvider.get("TYPE_ID");
        String readStatusId = PropertyProvider.get("NOTIFICATION_STATUS_TYPE_READ_ID");
        String unReadStatusId = PropertyProvider.get("NOTIFICATION_STATUS_TYPE_UNREAD_ID");
        String likeTypeId = PropertyProvider.get("NOTIFICATION_TYPE_POST_LIKE");
        String shareTypeId = PropertyProvider.get("NOTIFICATION_TYPE_POST_SHARE");
        String commentTypeId = PropertyProvider.get("NOTIFICATION_TYPE_POST_COMMENT");
        Document selectionDocumentForLike = new Document();
        selectionDocumentForLike.put(attrUserId, userId);
        selectionDocumentForLike.put(attrGeneralNotifications + "." + attrStatusId, unReadStatusId);
        selectionDocumentForLike.put(attrGeneralNotifications + "." + attrTypeId, likeTypeId);
        Document selectionDocumentForShare = new Document();
        selectionDocumentForShare.put(attrUserId, userId);
        selectionDocumentForShare.put(attrGeneralNotifications + "." + attrStatusId, unReadStatusId);
        selectionDocumentForShare.put(attrGeneralNotifications + "." + attrTypeId, shareTypeId);
        Document selectionDocumentForComment = new Document();
        selectionDocumentForComment.put(attrUserId, userId);
        selectionDocumentForComment.put(attrGeneralNotifications + "." + attrStatusId, unReadStatusId);
        selectionDocumentForComment.put(attrGeneralNotifications + "." + attrTypeId, commentTypeId);
        Document updateDocument = new Document();
        updateDocument.put("$set", new Document(attrGeneralNotifications + ".$." + attrStatusId, readStatusId));
        mongoCollection.findOneAndUpdate(selectionDocumentForLike, updateDocument);
        mongoCollection.findOneAndUpdate(selectionDocumentForShare, updateDocument);
        mongoCollection.findOneAndUpdate(selectionDocumentForComment, updateDocument);
    }

    public void testMongoSql(String userId) {
        MongoCollection<NotificationDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.NOTIFICATIONS.toString(), NotificationDAO.class);
        String attrUserId = PropertyProvider.get("USER_ID");
        Document sortQuery = new Document();
        sortQuery.put("userId", -1);
        Document selectionDocument = new Document();
        selectionDocument.put("$orderby", sortQuery);
        MongoCursor generalNotificationCursor1 = mongoCollection.find().sort(sortQuery).iterator();

    }

    public NotificationDAO getGeneralNotifications(String userId, int offset, int limit) {
        MongoCollection<NotificationDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.NOTIFICATIONS.toString(), NotificationDAO.class);
        String attrUserId = PropertyProvider.get("USER_ID");
        String attrGeneralNotifications = PropertyProvider.get("GENERAL_NOTIFICATIONS");
        Document selectionDocument = new Document();
        selectionDocument.put(attrUserId, userId);
        Document projectionDocument = new Document();
        projectionDocument.put(attrGeneralNotifications, "$all");
        NotificationDAO generalNotificationCursor = mongoCollection.find(selectionDocument).projection(projectionDocument).first();
        return generalNotificationCursor;
    }
}
