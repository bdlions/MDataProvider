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

    public static String getStatuses(String userId) {
        JSONObject json = new JSONObject();
        json.put("statusList", obj.getStatuses(userId));
        return json.toString();
    }

    public static String deleteStatus(String statusId) {
        String response = obj.deleteStatus(statusId);
        return response;
    }

    public static String updateStatus(String statusId, String statusInfo) {
        String response = obj.updateStatus(statusId, statusInfo);
        return response;
    }

    public static String updateStatusLike(String statusId, String likeInfo) {
        String response = obj.updateStatusLike(statusId, likeInfo);
        return response;

    }
    public static String addStatusComment(String statusId, String commentInfo) {
        String response = obj.addStatusComment(statusId, commentInfo);
        return response;

    }
    public static String updateStatusShare(String statusId, String shareInfo) {
        String response = obj.updateStatusShare(statusId, shareInfo);
        return response;

    }
}
