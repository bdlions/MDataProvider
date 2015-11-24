package com.shampan.services;

import com.shampan.model.NotificationModel;

/**
 *
 * @author nazmul hasan
 */
public class NotificationService {

    private static NotificationModel notificationModel = new NotificationModel();

    public NotificationService() {

    }

    /**
     * This method will return notification counter of all categories
     *
     * @return String, result event with notification counter values
     */
    public static String getNotificationCounter(String userId) {
        return notificationModel.getNotificationCounter(userId);
    }

    /**
     * This method will add a notification after sending friend request
     *
     * @param userId, user id who will be notified
     * @param friendId, user id who will send friend request
     * @return resultEvent, result event
     */
    public static String addFriendNotification(String userId, String friendId) {
        String resultEvent = "";
        return resultEvent;
    }

    /**
     * This method will update status type to read and return friend
     * notification list
     *
     * @param userId, user id
     * @param statusTypeId, status type id
     * @param offset, offset
     * @param limit, limit
     * @return String, friend notification list
     */
    public static String updateStatusGetFriendNotifications(String userId, String offset, String limit) {
        String notification = notificationModel.getFriendNotifications(userId).toString();
        notificationModel.updateStatusFriendNotifications(userId);
        return notification;
    }

    /**
     * This method will return friend notification list
     *
     * @param userId, user id
     * @param offset, offset
     * @param limit, limit
     * @return String, friend notification list
     */
    public static String getFriendNotifications(String userId, String offset, String limit) {
        return notificationModel.getFriendNotifications(userId).toString();
    }

    /**
     * This method will remove friend notification
     *
     * @param userId, user id
     * @param friendId, friend id of the user
     * @return String, result event
     */
    public static String deleteFriendNotification(String userId, String friendId) {
        String resultEvent = "";
        return resultEvent;
    }

    /**
     * This method will store general notification once a status is commented
     *
     * @param notificationData, notification data
     * @return String, result event
     */
    public static String addGeneralNotificationStatusComment(String notificationData) {
        String resultEvent = "";
        return resultEvent;
    }

    /**
     * This method will store general notification once a status is liked
     *
     * @param notificationData, notification data
     * @return String, result event
     */
    public static String addGeneralNotificationStatusLike(String notificationData) {
        String resultEvent = "";
        return resultEvent;
    }

    /**
     * This method will store general notification once a status is shared
     *
     * @param notificationData, notification data
     * @return String, result event
     */
    public static String addGeneralNotificationStatusShare(String notificationData) {
        String resultEvent = "";
        return resultEvent;
    }

    /**
     * This method will update general notification status type to read and
     * return general notification list
     *
     * @param userId, user id
     * @param statusTypeId, status type id
     * @param offset, offset
     * @param limit, limit
     * @return String, general notification list
     */
    public static String updateStatusGetGeneralNotifications(String userId, String statusTypeId, int offset, int limit) {
        notificationModel.updateStatusGeneralNotifications(userId, statusTypeId);
        return notificationModel.getGeneralNotifications(userId, offset, limit).toString();
    }

    /**
     * This method will return general notification list
     *
     * @param userId, user id
     * @param offset, offset
     * @param limit, limit
     * @return String, general notification list
     */
    public static String getGeneralNotifications(String userId, int offset, int limit) {
        return notificationModel.getGeneralNotifications(userId, offset, limit).toString();
    }

}
