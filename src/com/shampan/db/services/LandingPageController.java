package com.shampan.db.services;

import com.shampan.services.LandingPage;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

/**
 *
 * @author nazmul hasan
 */
public class LandingPageController {

    public static void getLandingPageInfo(RoutingContext routingContext) {
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(LandingPage.getLandingPageInfo());
    }

    public static void userRegistration(RoutingContext routingContext) {
        String registrationInfo = routingContext.request().getParam("registrationInfo");
        String userBasicInfo = routingContext.request().getParam("userBasicInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(LandingPage.userRegistration(registrationInfo, userBasicInfo));
    }
    public static void getUSerInfoByEmail(RoutingContext routingContext) {
        String email = routingContext.request().getParam("email");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(LandingPage.getUSerInfoByEmail(email));
    }
    public static void getUserInfoByUserId(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(LandingPage.getUserInfoByUserId(userId));
    }
    public static void activeRegistration(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String activationCode = routingContext.request().getParam("activationCode");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(LandingPage.activeRegistration(userId, activationCode));
    }
    public static void deactivateRegistration(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String activationCode = routingContext.request().getParam("activationCode");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(LandingPage.deactivateRegistration(userId, activationCode));
    }
    public static void updateLastLogin(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(LandingPage.updateLastLogin(userId));
    }
    public static void hashPasswordDB(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(LandingPage.hashPasswordDB(userId));
    }
    public static void rememberUser(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        String salt = routingContext.request().getParam("salt");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(LandingPage.rememberUser(userId,salt));
    }
    public static void getAttemptsNum(RoutingContext routingContext) {
        String userId = routingContext.request().getParam("userId");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(LandingPage.getAttemptsNum(userId));
    }
    public static void clearLoginAttempts(RoutingContext routingContext) {
        String email = routingContext.request().getParam("email");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(LandingPage.clearLoginAttempts(email));
    }
    public static void increaseLoginAttempt(RoutingContext routingContext) {
        String loginAttemptInfo = routingContext.request().getParam("loginAttemptInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(LandingPage.increaseLoginAttempt(loginAttemptInfo));
    }
}
