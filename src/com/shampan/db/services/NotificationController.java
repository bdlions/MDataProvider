package com.shampan.db.services;

import com.google.common.base.Strings;
import com.shampan.services.MessageService;
import com.shampan.services.NotificationService;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nazmul hasan
 */
//@RequestMapping("/notification")
public class NotificationController {

    private static Logger logger = LoggerFactory.getLogger(StatusController.class);

    public static void getNotificationCounter(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(NotificationService.getNotificationCounter(userId));
    }

    public static void updateStatusGetFriendNotifications(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String offset = routingContext.request().getParam("offset");
        String limit = routingContext.request().getParam("limit");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(NotificationService.updateStatusGetFriendNotifications(userId, offset, limit));
    }

    public static void getFriendNotifications(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String offset = routingContext.request().getParam("offset");
        String limit = routingContext.request().getParam("limit");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(NotificationService.getFriendNotifications(userId, offset, limit));
    }

    public static void deleteFriendNotification(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String friendId = routingContext.request().getParam("friendId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(NotificationService.deleteFriendNotification(userId, friendId));
    }

    public static void updateStatusGetGeneralNotifications(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String statusTypeId = routingContext.request().getParam("statusTypeId");
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
                .end(NotificationService.updateStatusGetGeneralNotifications(userId, statusTypeId, offset, limit));
    }

    public static void getGeneralNotifications(RoutingContext routingContext) {
        String userId ;
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
                .end(NotificationService.getGeneralNotifications(userId, offset, limit));
    }

}
