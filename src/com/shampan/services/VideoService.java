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

    public static String getCategories() {
        JSONObject categories = new JSONObject();
        categories.put("categoryList", vedioObj.getCategories());
        return categories.toString();
    }

    public static String addVideo(String videoInfo) {
        String response = vedioObj.addVideo(videoInfo);
        return response;
    }

    public static String getVideo(String videoId) {
        String response = vedioObj.getVideo(videoId);
        return response;
    }

    public static String deleteVideo(String videoId) {
        String response = vedioObj.deleteVideo(videoId);
        return response;
    }

}
