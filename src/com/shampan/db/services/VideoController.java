/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.services;

import com.shampan.services.PhotoService;
import com.shampan.services.VideoService;
import io.vertx.ext.web.RoutingContext;

/**
 *
 * @author Sampan-IT
 */
//@RestController
//@RequestMapping("/video")
public class VideoController {

    public static void getVideoCategories(RoutingContext routingContext) {
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(VideoService.getVideoCategories());
    }

    public static void addVideo(RoutingContext routingContext) {
        String videoInfo = routingContext.request().getParam("videoInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(VideoService.addVideo(videoInfo));
    }

    public static void getVideos(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(VideoService.getVideos(userId));
    }

    public static void getVideo(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String videoId = routingContext.request().getParam("videoId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(VideoService.getVideo(userId, videoId));
    }

    public static void updateVideo(RoutingContext routingContext) {
        String videoId = routingContext.request().getParam("videoId");
        String videoInfo = routingContext.request().getParam("videoInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(VideoService.updateVideo(videoId, videoInfo));
    }

    public static void deleteVideo(RoutingContext routingContext) {
        String videoId = routingContext.request().getParam("videoId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(VideoService.deleteVideo(videoId));
    }

    public static void addVideoLike(RoutingContext routingContext) {
        String videoId = routingContext.request().getParam("videoId");
        String likeInfo = routingContext.request().getParam("likeInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(VideoService.addVideoLike(videoId, likeInfo));
    }

    public static void getVideoLikeList(RoutingContext routingContext) {
        String videoId = routingContext.request().getParam("videoId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(VideoService.getVideoLikeList(videoId));
    }

    public static void deleteVideoLike(RoutingContext routingContext) {
        String videoId = routingContext.request().getParam("videoId");
        String likeId = routingContext.request().getParam("likeId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(VideoService.deleteVideoLike(videoId, likeId));
    }

    public static void addVideoComment(RoutingContext routingContext) {
        String videoId = routingContext.request().getParam("videoId");
        String commentInfo = routingContext.request().getParam("commentInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(VideoService.addVideoComment(videoId, commentInfo));
    }

    public static void getVideoComments(RoutingContext routingContext) {
        String videoId = routingContext.request().getParam("videoId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(VideoService.getVideoComments(videoId));
    }

    public static void editVideoComment(RoutingContext routingContext) {
        String videoId = routingContext.request().getParam("videoId");
        String commentId = routingContext.request().getParam("commentId");
        String commentInfo = routingContext.request().getParam("commentInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(VideoService.editVideoComment(videoId, commentId, commentInfo));
    }

}
