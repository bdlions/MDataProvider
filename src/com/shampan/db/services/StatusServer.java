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
public class StatusServer extends AbstractVerticle {

    @Override
    public void start() {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        router.route("/").handler((RoutingContext routingContext) -> {
            HttpServerResponse response = routingContext.response();
            response.end("Muslimand DB service.");
        });
        router.route("/status/addStatusComment*").handler(BodyHandler.create());
        router.post("/status/addStatusComment").handler(StatusController::addStatusComment);
        router.route("/status/deleteStatusComment*").handler(BodyHandler.create());
        router.post("/status/deleteStatusComment").handler(StatusController::deleteStatusComment);
        router.route("/status/addStatus*").handler(BodyHandler.create());
        router.post("/status/addStatus").handler(StatusController::addStatus);
        router.route("/status/getStatuses*").handler(BodyHandler.create());
        router.post("/status/getStatuses").handler(StatusController::getStatuses);
        router.route("/status/getUserProfileStatuses*").handler(BodyHandler.create());
        router.post("/status/getUserProfileStatuses").handler(StatusController::getUserProfileStatuses);
        router.route("/status/getStatusDetails*").handler(BodyHandler.create());
        router.post("/status/getStatusDetails").handler(StatusController::getStatusDetails);
        router.route("/status/deleteStatus*").handler(BodyHandler.create());
        router.post("/status/deleteStatus").handler(StatusController::deleteStatus);
        router.route("/status/updateStatus*").handler(BodyHandler.create());
        router.post("/status/updateStatus").handler(StatusController::updateStatus);
        router.route("/status/addStatusLike*").handler(BodyHandler.create());
        router.post("/status/addStatusLike").handler(StatusController::addStatusLike);
        router.route("/status/addStatusCommentLike*").handler(BodyHandler.create());
        router.post("/status/addStatusCommentLike").handler(StatusController::addStatusCommentLike);

        router.route("/status/shareStatus*").handler(BodyHandler.create());
        router.post("/status/shareStatus").handler(StatusController::shareStatus);
        router.route("/status/getStatusLikeList*").handler(BodyHandler.create());
        router.post("/status/getStatusLikeList").handler(StatusController::getStatusLikeList);
        router.route("/status/getStatusShareList*").handler(BodyHandler.create());
        router.post("/status/getStatusShareList").handler(StatusController::getStatusShareList);
        router.route("/status/getStatusComments*").handler(BodyHandler.create());
        router.post("/status/getStatusComments").handler(StatusController::getStatusComments);
        router.route("/status/updateStatusComment*").handler(BodyHandler.create());
        router.post("/status/updateStatusComment").handler(StatusController::updateStatusComment);

        router.route("/status/getStatusCommentLikeList*").handler(BodyHandler.create());
        router.post("/status/getStatusCommentLikeList").handler(StatusController::getStatusCommentLikeList);
        router.route("/status/getRecentActivities*").handler(BodyHandler.create());
        router.post("/status/getRecentActivities").handler(StatusController::getRecentActivities);

        server.requestHandler(router::accept).listen(8084);
    }
}
