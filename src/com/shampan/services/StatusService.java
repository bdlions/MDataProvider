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
        String response = obj.addStatus(statusInfo);
        return response;
    }

    public static String getStatuses(String userId, int offset, int limit) {
        return obj.getStatuses(userId, offset, limit).toString();
    }

    public static String deleteStatus(String statusId) {
        String response = obj.deleteStatus(statusId);
        return response;
    }

    public static String updateStatus(String statusId, String statusInfo) {
        String response = obj.updateStatus(statusId, statusInfo);
        return response;
    }

    public static String addStatusLike(String statusId, String likeInfo) {
        String response = obj.addStatusLike(statusId, likeInfo);
        return response;

    }

    public static String addStatusComment(String statusId, String commentInfo) {
        String response = obj.addStatusComment(statusId, commentInfo);
        return response;

    }

    public static String shareStatus(String statusId, String refUserInfo, String shareInfo) {
        String response = obj.shareStatus(statusId, refUserInfo, shareInfo);
        return response;

    }
    public static String getStatusLikeList(String statusId) {
        String response = obj.getStatusLikeList(statusId);
        return response;

    }
    public static String getStatusComments(String statusId) {
        String response = obj.getStatusComments(statusId);
        return response;

    }
}
