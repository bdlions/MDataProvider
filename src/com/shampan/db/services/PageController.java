/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.services;

import com.google.common.base.Strings;
import com.shampan.services.PageService;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sampan IT
 */
public class PageController {

    private static Logger logger = LoggerFactory.getLogger(StatusController.class);

    public static void getCategorySubCategory(RoutingContext routingContext) {
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.getCategorySubCategory());
    }

    public static void getSubCategories(RoutingContext routingContext) {
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.getSubCategories());
    }

    public static void addPage(RoutingContext routingContext) {
        String pageInfo = routingContext.request().getParam("pageInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.addPage(pageInfo));
    }

    public static void updatePage(RoutingContext routingContext) {
        String pageInfo = routingContext.request().getParam("pageInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.updatePage(pageInfo));
    }

    public static void getPageInfo(RoutingContext routingContext) {
        String pageId = routingContext.request().getParam("pageId");
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.getPageInfo(pageId, userId));
    }

    public static void addPageLike(RoutingContext routingContext) {
        String pageId = routingContext.request().getParam("pageId");
        String memberInfo = routingContext.request().getParam("memberInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.addPageLike(pageId, memberInfo));
    }

    public static void addPhotos(RoutingContext routingContext) {
        String pageId = routingContext.request().getParam("pageId");
        String albumId = routingContext.request().getParam("albumId");
        String photoList = routingContext.request().getParam("photoList");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.addPhotos(pageId, albumId, photoList));
    }

    public static void getInviteFriendList(RoutingContext routingContext) {
        String pageId = routingContext.request().getParam("pageId");
        String userId = routingContext.request().getParam("userId");
        int offset = 0, limit = 0;
        /*
         If offset is incorrect then offset will be used as default
         */
        if (!Strings.isNullOrEmpty(routingContext.request().getParam("offset"))) {
            try {
                offset = Integer.parseInt(routingContext.request().getParam("offset"));
            } catch (NumberFormatException nfe) {
                logger.debug(nfe.getMessage());
            }
        }

        /*
         If limit is incorrect then limit will be used as default
         */
        if (!Strings.isNullOrEmpty(routingContext.request().getParam("limit"))) {
            try {
                limit = Integer.parseInt(routingContext.request().getParam("limit"));
            } catch (NumberFormatException nfe) {
                logger.debug(nfe.getMessage());
            }
        }
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.getInviteFriendList(pageId, userId, offset, limit));

    }

    public static void inviteMember(RoutingContext routingContext) {
        String pageId = routingContext.request().getParam("pageId");
        String memberInfo = routingContext.request().getParam("memberInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.inviteMember(pageId, memberInfo));
    }

    public static void joinPageMamberShip(RoutingContext routingContext) {
        String pageId = routingContext.request().getParam("pageId");
        String memberInfo = routingContext.request().getParam("memberInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.joinPageMamberShip(pageId, memberInfo));
    }

    public static void leavePageMemberShip(RoutingContext routingContext) {
        String pageId = routingContext.request().getParam("pageId");
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.leavePageMemberShip(pageId, userId));
    }

    public static void getSliderPhotos(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String referenceId = routingContext.request().getParam("referenceId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.getSliderPhotos(userId, referenceId));
    }

    public static void addPhotoLike(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String photoId = routingContext.request().getParam("photoId");
        String referenceId = routingContext.request().getParam("referenceId");
        String likeInfo = routingContext.request().getParam("likeInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.addPhotoLike(userId, photoId, referenceId, likeInfo));
    }

    public static void addMPhotoLike(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String photoId = routingContext.request().getParam("photoId");
        String likeInfo = routingContext.request().getParam("likeInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.addMPhotoLike(userId, photoId, likeInfo));
    }

    public static void getTimelinePhotos(RoutingContext routingContext) {
        String pageId = routingContext.request().getParam("pageId");
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.getTimelinePhotos(pageId, userId));
    }

    public static void getAlbums(RoutingContext routingContext) {
        String pageId = routingContext.request().getParam("pageId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.getAlbums(pageId));
    }

    public static void getSliderAlbum(RoutingContext routingContext) {
        String mappingId = routingContext.request().getParam("mappingId");
        String albumId = routingContext.request().getParam("albumId");
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.getSliderAlbum(mappingId, albumId, userId));
    }

    public static void getPhotos(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String mappingId = routingContext.request().getParam("mappingId");
        String albumId = routingContext.request().getParam("albumId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.getPhotos(userId,mappingId, albumId));
    }
    
       public static void addPhotoComment(RoutingContext routingContext) {
        String photoId = routingContext.request().getParam("photoId");
        String referenceId = routingContext.request().getParam("referenceId");
        String commentInfo = routingContext.request().getParam("commentInfo");
        String referenceInfo = routingContext.request().getParam("referenceInfo");
        String statusTypeId = routingContext.request().getParam("statusTypeId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.addPhotoComment(photoId, referenceId, commentInfo, referenceInfo, statusTypeId));
    }

}
