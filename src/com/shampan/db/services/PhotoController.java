/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.services;

import com.google.common.base.Strings;
import com.shampan.services.MessageService;
import com.shampan.services.PhotoService;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sampan-IT
 */
//@RequestMapping("/photo")
public class PhotoController {

    private static Logger logger = LoggerFactory.getLogger(StatusController.class);

    public static void getCategories(RoutingContext routingContext) {
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.getCategories());
    }

    public static void getCategoriesAndAlbums(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.getCategoriesAndAlbums(userId));
    }

    public static void getAlbums(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.getAlbums(userId));
    }

    public static void getAlbum(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String albumId = routingContext.request().getParam("albumId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.getAlbum(userId, albumId));
    }

    public static void getAlbumInfo(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String albumId = routingContext.request().getParam("albumId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.getAlbumInfo(userId, albumId));
    }

    public static void createAlbum(RoutingContext routingContext) {
        String albumInfo = routingContext.request().getParam("albumInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.createAlbum(albumInfo));
    }

    public static void editAlbum(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String albumId = routingContext.request().getParam("albumId");
        String albumInfo = routingContext.request().getParam("albumInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.editAlbum(userId, albumId, albumInfo));
    }

    public static void getAlbumComments(RoutingContext routingContext) {
        String albumId = routingContext.request().getParam("albumId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.getAlbumComments(albumId));
    }

    public static void deleteAlbum(RoutingContext routingContext) {
        String albumId = routingContext.request().getParam("albumId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.deleteAlbum(albumId));
    }

    public static void addAlbumLike(RoutingContext routingContext) {
        String mappingId = routingContext.request().getParam("mappingId");
        String albumId = routingContext.request().getParam("albumId");
        String referenceId = routingContext.request().getParam("referenceId");
        String likeInfo = routingContext.request().getParam("likeInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.addAlbumLike(mappingId, albumId, referenceId,likeInfo));
    }

    public static void getAlbumLikeList(RoutingContext routingContext) {
        String albumId = routingContext.request().getParam("albumId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.getAlbumLikeList(albumId));
    }

    public static void addAlbumComment(RoutingContext routingContext) {
        String albumId = routingContext.request().getParam("albumId");
        String commentInfo = routingContext.request().getParam("commentInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.addAlbumComment(albumId, commentInfo));
    }

    public static void editAlbumComment(RoutingContext routingContext) {
        String albumId = routingContext.request().getParam("albumId");
        String commentId = routingContext.request().getParam("commentId");
        String commentInfo = routingContext.request().getParam("commentInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.editAlbumComment(albumId, commentId, commentInfo));
    }

    public static void deleteAlbumComment(RoutingContext routingContext) {
        String albumId = routingContext.request().getParam("albumId");
        String commentId = routingContext.request().getParam("commentId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.deleteAlbumComment(albumId, commentId));
    }

    public static void getPhotos(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String albumId = routingContext.request().getParam("albumId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.getPhotos(userId, albumId));
    }

    public static void getUserPhotos(RoutingContext routingContext) {
        /**
         * Default offset is 0 Default limit is 10 User id is mandatory
         */
        int offset = 0, limit = 10;
        String userId = routingContext.request().getParam("userId");
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
                .end(PhotoService.getUserPhotos(userId, offset, limit));
    }

    public static void getPhoto(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String photoId = routingContext.request().getParam("photoId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.getPhoto(userId, photoId));
    }

    public static void getPhotoListByCategory(RoutingContext routingContext) {
        String albumId = routingContext.request().getParam("albumId");
        String categoryId = routingContext.request().getParam("categoryId");
        /**
         * Default offset is 0 Default limit is 10 User id is mandatory
         */
        int offset = 0, limit = 10;
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
                .end(PhotoService.getPhotoListByCategory(albumId, categoryId, limit, offset));
    }

    public static void addPhotos(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String albumId = routingContext.request().getParam("albumId");
        String photoList = routingContext.request().getParam("photoList");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.addPhotos(userId, albumId, photoList));
    }

    public static void editPhoto(RoutingContext routingContext) {
        String photoId = routingContext.request().getParam("photoId");
        String photoInfo = routingContext.request().getParam("photoInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.editPhoto(photoId, photoInfo));
    }

    public static void getPhotoComments(RoutingContext routingContext) {
        String photoId = routingContext.request().getParam("photoId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.getPhotoComments(photoId));
    }

    public static void deletePhoto(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String albumId = routingContext.request().getParam("albumId");
        String photoId = routingContext.request().getParam("photoId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.deletePhoto(userId, albumId, photoId));
    }

    public static void addPhotoLike(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String photoId = routingContext.request().getParam("photoId");
        String referenceId = routingContext.request().getParam("referenceId");
        String likeInfo = routingContext.request().getParam("likeInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.addPhotoLike(userId, photoId, referenceId, likeInfo));
    }

    public static void deletePhotoLike(RoutingContext routingContext) {
        String photoId = routingContext.request().getParam("photoId");
        String likeId = routingContext.request().getParam("likeId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.deletePhotoLike(photoId, likeId));
    }

    public static void getPhotoLikeList(RoutingContext routingContext) {
        String photoId = routingContext.request().getParam("photoId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.getPhotoLikeList(photoId));
    }

    public static void addPhotoComment(RoutingContext routingContext) {
        String photoId = routingContext.request().getParam("photoId");
        String referenceId = routingContext.request().getParam("referenceId");
        String commentInfo = routingContext.request().getParam("commentInfo");
        String referenceInfo = routingContext.request().getParam("referenceInfo");
        String statusTypeId = routingContext.request().getParam("statusTypeId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.addPhotoComment(photoId, referenceId, commentInfo, referenceInfo, statusTypeId));
    }

    public static void editPhotoComment(RoutingContext routingContext) {
        String photoId = routingContext.request().getParam("photoId");
        String commentId = routingContext.request().getParam("commentId");
        String commentInfo = routingContext.request().getParam("commentInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.editPhotoComment(photoId, commentId, commentInfo));
    }

    public static void deletePhotoComment(RoutingContext routingContext) {
        String photoId = routingContext.request().getParam("photoId");
        String commentId = routingContext.request().getParam("commentId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.deletePhotoComment(photoId, commentId));
    }

    public static void getTimelinePhotos(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.getTimelinePhotos(userId));
    }

    public static void getSliderPhotos(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String referenceId = routingContext.request().getParam("referenceId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.getSliderPhotos(userId, referenceId));
    }

    public static void addMPhotoLike(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String photoId = routingContext.request().getParam("photoId");
        String likeInfo = routingContext.request().getParam("likeInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.addMPhotoLike(userId, photoId,  likeInfo));
    }
    public static void getSliderAlbum(RoutingContext routingContext) {
        String mappingId = routingContext.request().getParam("mappingId");
        String albumId = routingContext.request().getParam("albumId");
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PhotoService.getSliderAlbum(mappingId, albumId, userId));
    }

}
