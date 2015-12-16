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
}
