/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.services;

import com.shampan.db.collections.VideoCategoryDAO;
import com.shampan.db.collections.VideoDAO;
import com.shampan.db.collections.builder.VideoCategoryDAOBuilder;
import com.shampan.db.collections.builder.VideoDAOBuilder;
import com.shampan.db.collections.fragment.common.Comment;
import com.shampan.db.collections.fragment.common.Like;
import com.shampan.db.collections.fragment.common.UserInfo;
import com.shampan.model.VideoModel;
import org.junit.Test;

/**
 *
 * @author Sampan IT
 */
public class VideoServicesTest {

    VideoModel videoObject = new VideoModel();
    String videoId = "y06eVGno7SWQs1S";
    String categoryId = "1";
    String userId = "l3Leypo632aWWcV";

//    @Test
    public void addVideoCategory() {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName("Alamgir");
        userInfo.setLastName("Kabir");
        userInfo.setUserId(userId);
        VideoCategoryDAO vedioCategory = new VideoCategoryDAOBuilder()
                .setCategoryId("1")
                .setTitle("Al-Qurener Alu")
                .build();

        System.out.println(videoObject.addVideoCategory(vedioCategory.toString()));

    }

//    @Test
    public void getVideoCategories() {
        System.out.println(videoObject.getVideoCategories());

    }

//    @Test
    public void addVideo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName("Alamgir");
        userInfo.setLastName("Kabir");
        userInfo.setUserId(userId);
        VideoDAO videoInfo = new VideoDAOBuilder()
                .setVideoId(videoId)
                .setCategoryId(categoryId)
                .setUserId(userId)
                .setUserInfo(userInfo)
                .setUrl("https://www.youtube.com/watch?v=-27loHyoODs")
                .setCategoryId("1")
                .build();
        System.out.println(videoObject.addVideo(videoInfo.toString()));

    }

    @Test
    public void getVideo() {
        System.out.println(videoObject.getVideo(userId,videoId));

    }
//    @Test
    public void getVideos() {
        System.out.println(videoObject.getVideos(userId));

    }
//    @Test

    public void updateVideo() {
        System.out.println(videoObject.updateVideo(videoId, "https://www.youtube.com/watch?v=bZWFaAYPgiY"));

    }
//    @Test

    public void deleteVideo() {
        System.out.println(videoObject.deleteVideo(videoId));

    }

//    @Test
    public void addVideoLike() {
        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName("Alamgir");
        userInfo.setLastName("Kabir");
        userInfo.setUserId(userId);
        Like likeInfo = new Like();
        likeInfo.setId(videoId);
        likeInfo.setUserInfo(userInfo);
        System.out.println(videoObject.addVideoLike(videoId, likeInfo.toString()));

    }

//        @Test
    public void getVideoLikeList() {
        System.out.println(videoObject.getVideoLikeList(videoId));

    }
//        @Test
    public void deleteVideoLike() {
        String likeId = "1";
        System.out.println(videoObject.deleteVideoLike(videoId,likeId));

    }

//        @Test
//    public void addVideoComment() {
//        UserInfo userInfo = new UserInfo();
//        userInfo.setFirstName("Alamgir");
//        userInfo.setLastName("Kabir");
//        userInfo.setUserId(userId);
//        Comment commentInfo = new Comment();
//        commentInfo.setUserInfo(userInfo);
//        commentInfo.setDescription("i love song");
//        commentInfo.setId(videoId);
//        System.out.println(videoObject.addVideoComment(videoId, commentInfo.toString()));
//
//    }

//            @Test
    public void getVideoComments() {
        System.out.println(videoObject.getVideoComments(videoId));

    }
//            @Test
    public void editVideoComment() {
        String commentId ="1";
        System.out.println(videoObject.editVideoComment(videoId,commentId,"i hate you"));

    }
//            @Test
    public void deleteVideoComment() {
        String commentId = "1";
        System.out.println(videoObject.deleteVideoComment(videoId,commentId));

    }
}
