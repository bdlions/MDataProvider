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
public class MessageServer extends AbstractVerticle {

    @Override
    public void start() {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        router.route("/").handler((RoutingContext routingContext) -> {
            HttpServerResponse response = routingContext.response();
            response.end("Muslimand DB service.");
        });

        router.route("/message/addMessageByGroupId*").handler(BodyHandler.create());
        router.post("/message/addMessageByGroupId").handler(MessageController::addMessageByGroupId);
        router.route("/message/addMessage*").handler(BodyHandler.create());
        router.post("/message/addMessage").handler(MessageController::addMessage);

        router.route("/message/getMessageSummaryList*").handler(BodyHandler.create());
        router.post("/message/getMessageSummaryList").handler(MessageController::getMessageSummaryList);
        router.route("/message/getMessageList*").handler(BodyHandler.create());
        router.post("/message/getMessageList").handler(MessageController::getMessageList);
//        router.route("/status/addStatusComment*").handler(BodyHandler.create());
//        router.post("/status/addStatusComment").handler(StatusController::addStatusComment);

        server.requestHandler(router::accept).listen(8081);
    }
}
