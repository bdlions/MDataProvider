package com.shampan.db.services;

import com.google.common.base.Strings;
import com.shampan.services.MessageService;
import com.shampan.services.StatusService;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author nazmul hasan
 */
public class MessageController {

    private static Logger logger = LoggerFactory.getLogger(StatusController.class);

    public static void addMessage(RoutingContext routingContext) {
        String userIdList = routingContext.request().getParam("userIdList");
        String senderId = routingContext.request().getParam("senderId");
        String message = routingContext.request().getParam("message");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(MessageService.addMessage(userIdList, senderId, message));
    }

    public static void addMessageByGroupId(RoutingContext routingContext) {
        String groupId = routingContext.request().getParam("groupId");
        String senderInfo = routingContext.request().getParam("senderInfo");
        String message = routingContext.request().getParam("message");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(MessageService.addMessageByGroupId(groupId, senderInfo, message));
    }

    public static void getMessageSummaryList(RoutingContext routingContext) {
        int offset = 0;
        int limit = 10;
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
                .end(MessageService.getMessageSummaryList(userId, offset, limit));
    }

    public static void getMessageList(RoutingContext routingContext) {
        String groupId = routingContext.request().getParam("groupId");
        int offset = 0;
        int limit = 10;
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
                .end(MessageService.getMessageList(groupId, offset, limit));
    }

}
