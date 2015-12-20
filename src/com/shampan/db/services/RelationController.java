package com.shampan.db.services;

import com.google.common.base.Strings;
import com.shampan.services.RelationService;
import com.shampan.services.StatusService;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nazmul hasan
 */
public class RelationController {

    private static Logger logger = LoggerFactory.getLogger(StatusController.class);

    public static void addFriend(RoutingContext routingContext) {
        String fromUserId = routingContext.request().getParam("fromUserId");
        String toUserId = routingContext.request().getParam("toUserId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(RelationService.addFriend(fromUserId, toUserId));
    }

    public static void blockNonFriend(RoutingContext routingContext) {
        String fromUserId = routingContext.request().getParam("fromUserId");
        String toUserId = routingContext.request().getParam("toUserId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(RelationService.blockNonFriend(fromUserId, toUserId));
    }

    public static void blockFriend(RoutingContext routingContext) {
        String fromUserId = routingContext.request().getParam("fromUserId");
        String toUserId = routingContext.request().getParam("toUserId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(RelationService.blockFriend(fromUserId, toUserId));
    }

    public static void approveFriend(RoutingContext routingContext) {
        String fromUserId = routingContext.request().getParam("fromUserId");
        String toUserId = routingContext.request().getParam("toUserId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(RelationService.approveFriend(fromUserId, toUserId));
    }

    public static void removeFriendRequest(RoutingContext routingContext) {
        String fromUserId = routingContext.request().getParam("fromUserId");
        String toUserId = routingContext.request().getParam("toUserId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(RelationService.removeFriendRequest(fromUserId, toUserId));
    }

    public static void unblockFriend(RoutingContext routingContext) {
        String fromUserId = routingContext.request().getParam("fromUserId");
        String toUserId = routingContext.request().getParam("toUserId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(RelationService.unblockFriend(fromUserId, toUserId));
    }

    public static void removeFriend(RoutingContext routingContext) {
        String fromUserId = routingContext.request().getParam("fromUserId");
        String toUserId = routingContext.request().getParam("toUserId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(RelationService.removeFriend(fromUserId, toUserId));
    }

    public static void getRelationList(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String relationTypeId = routingContext.request().getParam("relationTypeId");
        String offset = routingContext.request().getParam("offset");
        String limit = routingContext.request().getParam("limit");

        String toUserId = routingContext.request().getParam("toUserId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(RelationService.getRelationList(userId, relationTypeId, offset, limit));
    }

    public static void getRelationInfo(RoutingContext routingContext) {
        String fromUserId = routingContext.request().getParam("fromUserId");
        String toUserId = routingContext.request().getParam("toUserId");

        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(RelationService.getRelationInfo(fromUserId, toUserId));

    }

    public static void getUserGenderInfo(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(RelationService.getUserGenderInfo(userId));

    }
}
