/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.services;

import com.google.common.base.Strings;
import com.shampan.services.StatusService;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sampan-IT
 */
public class StatusController {

    private static Logger logger = LoggerFactory.getLogger(StatusController.class);

    public static void addStatus(RoutingContext routingContext) {
        String statusInfo = routingContext.request().getParam("statusInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(StatusService.addStatus(statusInfo));

    }

    public static void getStatuses(RoutingContext routingContext) {

        String userId;
        /**
         * Default offset is 0 Default limit is 10 User id is mandatory
         */
        int offset = 0, limit = 0;

        userId = routingContext.request().getParam("userId");
        if (Strings.isNullOrEmpty(userId)) {
            routingContext.response().end("failed");
        }

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
                .end(StatusService.getStatuses(userId, offset, limit));
    }

    public static void getUserProfileStatuses(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String mappingId = routingContext.request().getParam("mappingId");
        /*
         If offset is incorrect then offset will be used as default
         */
        int offset = 0, limit = 0;
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
                .end(StatusService.getUserProfileStatuses(userId, mappingId, offset, limit));

    }
    public static void getPageProfileStatuses(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String mappingId = routingContext.request().getParam("mappingId");
        /*
         If offset is incorrect then offset will be used as default
         */
        int offset = 0, limit = 0;
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
                .end(StatusService.getPageProfileStatuses(userId, mappingId, offset, limit));

    }

    public static void getStatusDetails(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String statusId = routingContext.request().getParam("statusId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(StatusService.getStatusDetails(userId, statusId));

    }

    public static void deleteStatus(RoutingContext routingContext) {
        String statusId = routingContext.request().getParam("statusId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(StatusService.deleteStatus(statusId));

    }

    public static void updateStatus(RoutingContext routingContext) {
        String statusId = routingContext.request().getParam("statusId");
        String statusInfo = routingContext.request().getParam("statusInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(StatusService.updateStatus(statusId, statusInfo));

    }

    public static void addStatusLike(RoutingContext routingContext) {
        String statusId = routingContext.request().getParam("statusId");
        String userId = routingContext.request().getParam("userId");
        String likeInfo = routingContext.request().getParam("likeInfo");
        String statusTypeId = routingContext.request().getParam("statusTypeId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(StatusService.addStatusLike(userId, statusId, likeInfo, statusTypeId));
    }

    public static void addStatusCommentLike(RoutingContext routingContext) {
        String statusId = routingContext.request().getParam("statusId");
        String commentId = routingContext.request().getParam("commentId");
        String likeInfo = routingContext.request().getParam("likeInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(StatusService.addStatusCommentLike(statusId, commentId, likeInfo));

    }

    public static void addStatusComment(RoutingContext routingContext) {
        String referenceUserInfo = routingContext.request().getParam("referenceUserInfo");
        String statusId = routingContext.request().getParam("statusId");
        String statusTypeId = routingContext.request().getParam("statusTypeId");
        String commentInfo = routingContext.request().getParam("commentInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(StatusService.addStatusComment(referenceUserInfo, statusId,statusTypeId, commentInfo));

    }

    public static void shareStatus(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String statusId = routingContext.request().getParam("statusId");
        String refUserInfo = routingContext.request().getParam("refUserInfo");
        String shareInfo = routingContext.request().getParam("shareInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(StatusService.shareStatus(userId, statusId, refUserInfo, shareInfo));

    }

    public static void getStatusLikeList(RoutingContext routingContext) {
        String statusId = routingContext.request().getParam("statusId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(StatusService.getStatusLikeList(statusId));

    }

    public static void getStatusShareList(RoutingContext routingContext) {
        String statusId = routingContext.request().getParam("statusId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(StatusService.getStatusShareList(statusId));

    }

    public static void getStatusComments(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String statusId = routingContext.request().getParam("statusId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(StatusService.getStatusComments(userId, statusId));

    }

    public static void updateStatusComment(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String statusId = routingContext.request().getParam("statusId");
        String description = routingContext.request().getParam("description");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(StatusService.updateStatusComment(userId, statusId, description));

    }

    public static void deleteStatusComment(RoutingContext routingContext) {
        String statusId = routingContext.request().getParam("statusId");
        String commentId = routingContext.request().getParam("commentId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(StatusService.deleteStatusComment(statusId, commentId));

    }

    public static void getStatusCommentLikeList(RoutingContext routingContext) {
        String statusId = routingContext.request().getParam("statusId");
        String commentId = routingContext.request().getParam("commentId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(StatusService.getStatusCommentLikeList(statusId, commentId));

    }

    public static void getRecentActivities(RoutingContext routingContext) {

        String userId;
        /**
         * Default offset is 0 Default limit is 10 User id is mandatory
         */
        int offset = 0, limit = 10;

        userId = routingContext.request().getParam("userId");
        if (Strings.isNullOrEmpty(userId)) {
            routingContext.response().end("failed");
        }

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
                .end(StatusService.getRecentActivities(userId, offset, limit));
    }

}
