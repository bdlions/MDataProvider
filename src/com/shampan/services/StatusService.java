/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.services;

import com.shampan.model.StatusModel;
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
    public static String getUserProfileStatuses(String userId,String mappingId, int offset, int limit) {
        return obj.getUserProfileStatuses(userId, mappingId, offset, limit);
    }
    public static String getStatusDetails(String userId,String statusId) {
        return obj.getStatusDetails(userId,statusId);
    }

    public static String deleteStatus(String statusId) {
        String response = obj.deleteStatus(statusId).toString();
        return response;
    }

    public static String updateStatus(String statusId, String statusInfo) {
        String response = obj.updateStatus(statusId, statusInfo).toString();
        return response;
    }

    public static String addStatusLike(String userId,String statusId, String likeInfo) {
        String response = obj.addStatusLike(userId,statusId, likeInfo).toString();
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
}
