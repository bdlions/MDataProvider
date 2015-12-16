/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db.services;

/**
 *
 * @author alamgir
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
/**
 *
 * @author alamgir
 */
public class ServerExecutor extends AbstractVerticle {

    public static void main(String[] args){
        //run Sample java web server
        VertxOptions options = new VertxOptions(); 
        //server execution time
        options.setMaxEventLoopExecuteTime(Long.MAX_VALUE);
        
        Vertx serviceAPIVerticle = Vertx.vertx(options);
        
        serviceAPIVerticle.deployVerticle(new ServerExecutor());
    }
    
    @Override
    public void start() {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        router.route("/").handler((RoutingContext routingContext) -> {
            HttpServerResponse response = routingContext.response();
            response.end("Muslimand DB service.");
        });
        
        router.get("/landingpage/getLandingPageInfo").handler(LandingPageController::getLandingPageInfo);

        server.requestHandler(router::accept).listen(8080);
    }
}
