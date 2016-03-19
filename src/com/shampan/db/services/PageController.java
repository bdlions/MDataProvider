/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.services;

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
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.getPageInfo(pageId));
    }
    public static void addPageLike(RoutingContext routingContext) {
        String pageId = routingContext.request().getParam("pageId");
        String memberInfo = routingContext.request().getParam("memberInfo");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(PageService.addPageLike(pageId, memberInfo));
    }

}
