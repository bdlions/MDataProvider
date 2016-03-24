/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.services;

import com.shampan.model.PhotoModel;
import com.shampan.model.StatusModel;
import com.shampan.util.PropertyProvider;
import org.json.JSONObject;

/**
 *
 * @author Sampan-IT
 */
public class StatusService {

    private static StatusModel obj = new StatusModel();

    public static String addStatus(String statusInfo) {
        String response = obj.addStatus(statusInfo).toString();
        return response;
    }

    public static String getStatuses(String userId, int offset, int limit) {
        return obj.getStatuses(userId, offset, limit);
    }
 
    public static String getUserProfileStatuses(String userId, String mappingId, int offset, int limit) {
        return obj.getUserProfileStatuses(userId, mappingId, offset, limit);
    }
    public static String getPageProfileStatuses(String userId, String mappingId, int offset, int limit) {
        return obj.getPageProfileStatuses(userId, mappingId, offset, limit);
    }

    public static String getStatusDetails(String userId, String statusId) {
        return obj.getStatusDetails(userId, statusId);
    }

    public static String deleteStatus(String statusId) {
        String response = obj.deleteStatus(statusId).toString();
        return response;
    }

    public static String updateStatus(String statusId, String statusInfo) {
        String response = obj.updateStatus(statusId, statusInfo).toString();
        return response;
    }

    public static String addStatusLike(String userId, String statusId, String likeInfo, String statusTypeId) {
        PropertyProvider.add("com.shampan.properties/status");
        PhotoModel photoObj = new PhotoModel();
        String abc = PropertyProvider.get("STATUS_TYPE_ID_CHANGE_PROFILE_PICTURE");
        if (statusTypeId.endsWith(PropertyProvider.get("STATUS_TYPE_ID_CHANGE_PROFILE_PICTURE"))|| statusTypeId .endsWith(PropertyProvider.get("STATUS_TYPE_ID_CHANGE_COVER_PICTURE")) || statusTypeId.endsWith(PropertyProvider.get("STATUS_TYPE_ID_POST_STATUS_BY_USER_AT_HIS_PROFILE_WITH_PHOTO")) || statusTypeId .endsWith(PropertyProvider.get("STATUS_TYPE_ID_POST_STATUS_BY_USER_AT_FRIEND_PROFILE_WITH_PHOTO"))) {
            photoObj.addPhotoLikeByReferenceId(statusId, likeInfo).toString();
        } else if (statusTypeId.endsWith(PropertyProvider.get("STATUS_TYPE_ID_ADD_ALBUM_PHOTOS"))) {
            photoObj.addAlbumLikeByReferenceId(statusId, likeInfo);
        }
        String response = obj.addStatusLike(userId, statusId, likeInfo).toString();
        return response;

    }

    public static String addStatusCommentLike(String statusId, String commentId, String likeInfo) {
        String response = obj.addStatusCommentLike(statusId, commentId, likeInfo).toString();
        return response;

    }

    public static String addStatusComment(String referenceUserInfo, String statusId, String commentInfo) {
        String response = obj.addStatusComment(referenceUserInfo, statusId, commentInfo).toString();
        return response;

    }

    public static String shareStatus(String userId, String statusId, String refUserInfo, String shareInfo) {
        String response = obj.shareStatus(userId, statusId, refUserInfo, shareInfo).toString();
        return response;

    }

    public static String getStatusLikeList(String statusId) {
        String response = obj.getStatusLikeList(statusId).toString();
        return response;

    }

    public static String getStatusShareList(String statusId) {
        String response = obj.getStatusShareList(statusId).toString();
        return response;

    }

    public static String getStatusComments(String userId, String statusId) {
        String response = obj.getStatusComments(userId, statusId).toString();
        return response;

    }

    public static String updateStatusComment(String statusId, String commentId, String description) {
        String response = obj.updateStatusComment(statusId, commentId, description).toString();
        return response;

    }

    public static String deleteStatusComment(String statusId, String commentId) {
        String response = obj.deleteStatusComment(statusId, commentId).toString();
        return response;

    }

    public static String getStatusCommentLikeList(String statusId, String commentId) {
        String response = obj.getStatusCommentLikeList(statusId, commentId).toString();
        return response;

    }

    public static String getRecentActivities(String userId, int offset, int limit) {
        String response = obj.getRecentActivities(userId, offset, limit).toString();
        return response;

    }
}
