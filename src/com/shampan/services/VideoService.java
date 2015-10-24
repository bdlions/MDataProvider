/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.services;

import com.shampan.model.VideoModel;
import org.json.JSONObject;

/**
 *
 * @author Sampan-IT
 */
public class VideoService {

    private static VideoModel vedioObj = new VideoModel();

    public static String getVideoCategories() {
        JSONObject categories = new JSONObject();
        categories.put("categoryList", vedioObj.getVideoCategories());
        return categories.toString();
    }

    public static String addVideo(String videoInfo) {
        String response = vedioObj.addVideo(videoInfo);
        return response;
    }

    public static String getVideos(String userId) {
        JSONObject videos = new JSONObject();
        videos.put("videoList", vedioObj.getVideos(userId));
        return videos.toString();
    }

    public static String getVideo(String userId, String videoId) {
        String response = vedioObj.getVideo(userId,videoId);
        return response;
    }

    public static String updateVideo(String videoId, String videoInfo) {
        String response = vedioObj.updateVideo(videoId, videoInfo);
        return response;
    }

    public static String deleteVideo(String videoId) {
        String response = vedioObj.deleteVideo(videoId);
        return response;
    }

    public static String addVideoLike(String videoId, String likeInfo) {
        String response = vedioObj.addVideoLike(videoId, likeInfo);
        return response;
    }

    public static String getVideoLikeList(String videoId) {
        String response = vedioObj.getVideoLikeList(videoId);
        return response;
    }

    public static String deleteVideoLike(String videoId, String likeId) {
        String response = vedioObj.deleteVideoLike(videoId, likeId);
        return response;
    }

    public static String addVideoComment(String videoId, String commentInfo) {
        String response = vedioObj.addVideoComment(videoId, commentInfo);
        return response;
    }

    public static String getVideoComments(String videoId) {
        String response = vedioObj.getVideoComments(videoId);
        return response;
    }

    public static String editVideoComment(String videoId, String commentId, String commentInfo) {
        String response = vedioObj.editVideoComment(videoId, commentId, commentInfo);
        return response;
    }

    public static String deleteVideoComment(String videoId, String commentId) {
        String response = vedioObj.deleteVideoComment(videoId, commentId);
        return response;
    }

}
