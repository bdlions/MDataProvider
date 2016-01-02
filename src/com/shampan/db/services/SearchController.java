/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.services;

import com.shampan.services.SearchService;
import io.vertx.ext.web.RoutingContext;

/**
 *
 * @author Sampan-IT
 */
public class SearchController {

    public static void getUsers(RoutingContext routingContext) {
        String searchValue = routingContext.request().getParam("searchValue");
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(SearchService.getUsers(searchValue));
    }

}
