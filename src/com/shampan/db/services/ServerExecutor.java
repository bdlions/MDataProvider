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

    public static void main(String[] args) {
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
        /*
         LandingPageController
         */
        router.get("/landingpage/getLandingPageInfo").handler(LandingPageController::getLandingPageInfo);
        
        router.route("/userRegistration").handler(BodyHandler.create());
        router.post("/userRegistration").handler(LandingPageController::userRegistration);
        router.route("/getUSerInfoByEmail").handler(BodyHandler.create());
        router.post("/getUSerInfoByEmail").handler(LandingPageController::getUSerInfoByEmail);
        router.route("/getUserInfoByUserId").handler(BodyHandler.create());
        router.post("/getUserInfoByUserId").handler(LandingPageController::getUserInfoByUserId);
        router.route("/activeRegistration").handler(BodyHandler.create());
        router.post("/activeRegistration").handler(LandingPageController::activeRegistration);
        router.route("/deactivateRegistration").handler(BodyHandler.create());
        router.post("/deactivateRegistration").handler(LandingPageController::deactivateRegistration);
        router.route("/updateLastLogin").handler(BodyHandler.create());
        router.post("/updateLastLogin").handler(LandingPageController::updateLastLogin);
        router.route("/hashPasswordDB").handler(BodyHandler.create());
        router.post("/hashPasswordDB").handler(LandingPageController::hashPasswordDB);
   
        router.route("/rememberUser").handler(BodyHandler.create());
        router.post("/rememberUser").handler(LandingPageController::rememberUser);
        router.route("/getAttemptsNum").handler(BodyHandler.create());
        router.post("/getAttemptsNum").handler(LandingPageController::getAttemptsNum);
       
        router.route("/clearLoginAttempts").handler(BodyHandler.create());
        router.post("/clearLoginAttempts").handler(LandingPageController::clearLoginAttempts);
        router.route("/increaseLoginAttempt").handler(BodyHandler.create());
        router.post("/increaseLoginAttempt").handler(LandingPageController::increaseLoginAttempt);
        /*
         /*
         SearchController
         */
        router.route("/search/getUsers").handler(BodyHandler.create());
        router.post("/search/getUsers").handler(SearchController::getUsers);
        /*
         StatusController
         */

        router.route("/status/addStatus").handler(BodyHandler.create());
        router.post("/status/addStatus").handler(StatusController::addStatus);
        router.route("/status/getStatuses").handler(BodyHandler.create());
        router.post("/status/getStatuses").handler(StatusController::getStatuses);
        router.route("/status/getUserProfileStatuses").handler(BodyHandler.create());
        router.post("/status/getUserProfileStatuses").handler(StatusController::getUserProfileStatuses);
        router.route("/status/getStatusDetails").handler(BodyHandler.create());
        router.post("/status/getStatusDetails").handler(StatusController::getStatusDetails);
        router.route("/status/deleteStatus").handler(BodyHandler.create());
        router.post("/status/deleteStatus").handler(StatusController::deleteStatus);
        router.route("/status/updateStatus").handler(BodyHandler.create());
        router.post("/status/updateStatus").handler(StatusController::updateStatus);
        router.route("/status/addStatusLike").handler(BodyHandler.create());
        router.post("/status/addStatusLike").handler(StatusController::addStatusLike);
        router.route("/status/addStatusCommentLike").handler(BodyHandler.create());
        router.post("/status/addStatusCommentLike").handler(StatusController::addStatusCommentLike);
        router.route("/status/addStatusComment").handler(BodyHandler.create());
        router.post("/status/addStatusComment").handler(StatusController::addStatusComment);
        router.route("/status/shareStatus").handler(BodyHandler.create());
        router.post("/status/shareStatus").handler(StatusController::shareStatus);
        router.route("/status/getStatusLikeList").handler(BodyHandler.create());
        router.post("/status/getStatusLikeList").handler(StatusController::getStatusLikeList);
        router.route("/status/getStatusShareList").handler(BodyHandler.create());
        router.post("/status/getStatusShareList").handler(StatusController::getStatusShareList);
        router.route("/status/getStatusComments").handler(BodyHandler.create());
        router.post("/status/getStatusComments").handler(StatusController::getStatusComments);
        router.route("/status/updateStatusComment").handler(BodyHandler.create());
        router.post("/status/updateStatusComment").handler(StatusController::updateStatusComment);
        router.route("/status/deleteStatusComment").handler(BodyHandler.create());
        router.post("/status/deleteStatusComment").handler(StatusController::deleteStatusComment);
        router.route("/status/getStatusCommentLikeList").handler(BodyHandler.create());
        router.post("/status/getStatusCommentLikeList").handler(StatusController::getStatusCommentLikeList);
        router.route("/status/getRecentActivities").handler(BodyHandler.create());
        router.post("/status/getRecentActivities").handler(StatusController::getRecentActivities);

        /*
         BasicProfileController
         */
        router.route("/basicProfile/getWorksEducation").handler(BodyHandler.create());
        router.post("/basicProfile/getWorksEducation").handler(BasicProfileController::getWorksEducation);
        router.route("/basicProfile/addWorkPlace").handler(BodyHandler.create());
        router.post("/basicProfile/addWorkPlace").handler(BasicProfileController::addWorkPlace);
        router.route("/basicProfile/addProfessionalSkill").handler(BodyHandler.create());
        router.post("/basicProfile/addProfessionalSkill").handler(BasicProfileController::addPSkill);
        router.route("/basicProfile/addUniversity").handler(BodyHandler.create());
        router.post("/basicProfile/addUniversity").handler(BasicProfileController::addUniversity);
        router.route("/basicProfile/addCollege").handler(BodyHandler.create());
        router.post("/basicProfile/addCollege").handler(BasicProfileController::addCollege);
        router.route("/basicProfile/addSchool").handler(BodyHandler.create());
        router.post("/basicProfile/addSchool").handler(BasicProfileController::addSchool);
        router.route("/basicProfile/editWorkPlace").handler(BodyHandler.create());
        router.post("/basicProfile/editWorkPlace").handler(BasicProfileController::editWorkPlace);
        router.route("/basicProfile/editProfessionalSkill").handler(BodyHandler.create());
        router.post("/basicProfile/editProfessionalSkill").handler(BasicProfileController::editProfessionalSkill);
        router.route("/basicProfile/editUniversity").handler(BodyHandler.create());
        router.post("/basicProfile/editUniversity").handler(BasicProfileController::editUniversity);
        router.route("/basicProfile/editCollege").handler(BodyHandler.create());
        router.post("/basicProfile/editCollege").handler(BasicProfileController::editCollege);
        router.route("/basicProfile/editSchool").handler(BodyHandler.create());
        router.post("/basicProfile/editSchool").handler(BasicProfileController::editSchool);
        router.route("/basicProfile/deleteWrokPlace").handler(BodyHandler.create());
        router.post("/basicProfile/deleteWrokPlace").handler(BasicProfileController::deleteWrokPlace);
        router.route("/basicProfile/deletePSkill").handler(BodyHandler.create());
        router.post("/basicProfile/deletePSkill").handler(BasicProfileController::deletePSkill);
        router.route("/basicProfile/deleteUniversity").handler(BodyHandler.create());
        router.post("/basicProfile/deleteUniversity").handler(BasicProfileController::deleteUniversity);
        router.route("/basicProfile/deleteCollege").handler(BodyHandler.create());
        router.post("/basicProfile/deleteCollege").handler(BasicProfileController::deleteCollege);
        router.route("/basicProfile/deleteSchool").handler(BodyHandler.create());
        router.post("/basicProfile/deleteSchool").handler(BasicProfileController::deleteSchool);
        router.route("/basicProfile/getOverview").handler(BodyHandler.create());
        router.post("/basicProfile/getOverview").handler(BasicProfileController::getOverview);
        router.route("/basicProfile/getCityTown").handler(BodyHandler.create());
        router.post("/basicProfile/getCityTown").handler(BasicProfileController::getCityTown);
        router.route("/basicProfile/getFamilyRelation").handler(BodyHandler.create());
        router.post("/basicProfile/getFamilyRelation").handler(BasicProfileController::getFamilyRelation);
        router.route("/basicProfile/getContactBasicInfo").handler(BodyHandler.create());
        router.post("/basicProfile/getContactBasicInfo").handler(BasicProfileController::getContactBasicInfo);
        router.route("/basicProfile/addCurrentCity").handler(BodyHandler.create());
        router.post("/basicProfile/addCurrentCity").handler(BasicProfileController::addCurrentCity);
        router.route("/basicProfile/editCurrentCity").handler(BodyHandler.create());
        router.post("/basicProfile/editCurrentCity").handler(BasicProfileController::editCurrentCity);
        router.route("/basicProfile/deleteCurrentCity").handler(BodyHandler.create());
        router.post("/basicProfile/deleteCurrentCity").handler(BasicProfileController::deleteCurrentCity);
        router.route("/basicProfile/addHomeTown").handler(BodyHandler.create());
        router.post("/basicProfile/addHomeTown").handler(BasicProfileController::addHomeTown);
        router.route("/basicProfile/editHomeTown").handler(BodyHandler.create());
        router.post("/basicProfile/editHomeTown").handler(BasicProfileController::editHomeTown);
        router.route("/basicProfile/deleteHomeTown").handler(BodyHandler.create());
        router.post("/basicProfile/deleteHomeTown").handler(BasicProfileController::deleteHomeTown);
        router.route("/basicProfile/addRelationshipStatus").handler(BodyHandler.create());
        router.post("/basicProfile/addRelationshipStatus").handler(BasicProfileController::addRelationshipStatus);
        router.route("/basicProfile/addMobilePhone").handler(BodyHandler.create());
        router.post("/basicProfile/addMobilePhone").handler(BasicProfileController::addMobilePhone);
        router.route("/basicProfile/editMobilePhone").handler(BodyHandler.create());
        router.post("/basicProfile/editMobilePhone").handler(BasicProfileController::editMobilePhone);
        router.route("/basicProfile/deleteMobilePhone").handler(BodyHandler.create());
        router.post("/basicProfile/deleteMobilePhone").handler(BasicProfileController::deleteMobilePhone);
        router.route("/basicProfile/addAddress").handler(BodyHandler.create());
        router.post("/basicProfile/addAddress").handler(BasicProfileController::addAddress);
        router.route("/basicProfile/editAddress").handler(BodyHandler.create());
        router.post("/basicProfile/editAddress").handler(BasicProfileController::editAddress);
        router.route("/basicProfile/deleteAddress").handler(BodyHandler.create());
        router.post("/basicProfile/deleteAddress").handler(BasicProfileController::deleteAddress);
        router.route("/basicProfile/addWebsite").handler(BodyHandler.create());
        router.post("/basicProfile/addWebsite").handler(BasicProfileController::addWebsite);
        router.route("/basicProfile/editWebsite").handler(BodyHandler.create());
        router.post("/basicProfile/editWebsite").handler(BasicProfileController::editWebsite);
        router.route("/basicProfile/deleteWebsite").handler(BodyHandler.create());
        router.post("/basicProfile/deleteWebsite").handler(BasicProfileController::deleteWebsite);
        router.route("/basicProfile/addEmail").handler(BodyHandler.create());
        router.post("/basicProfile/addEmail").handler(BasicProfileController::addEmail);
        router.route("/basicProfile/editEmail").handler(BodyHandler.create());
        router.post("/basicProfile/editEmail").handler(BasicProfileController::editEmail);
        router.route("/basicProfile/deleteEmail").handler(BodyHandler.create());
        router.post("/basicProfile/deleteEmail").handler(BasicProfileController::deleteEmail);
        router.route("/basicProfile/getAboutFQuote").handler(BodyHandler.create());
        router.post("/basicProfile/getAboutFQuote").handler(BasicProfileController::getAboutFQuote);
        router.route("/basicProfile/addAbout").handler(BodyHandler.create());
        router.post("/basicProfile/addAbout").handler(BasicProfileController::addAbout);
        router.route("/basicProfile/addFQuote").handler(BodyHandler.create());
        router.post("/basicProfile/addFQuote").handler(BasicProfileController::addFQuote);

        /*
         RelationController
         */
        router.route("/relation/addFriend").handler(BodyHandler.create());
        router.post("/relation/addFriend").handler(RelationController::addFriend);
        router.route("/relation/blockNonFriend").handler(BodyHandler.create());
        router.post("/relation/blockNonFriend").handler(RelationController::blockNonFriend);
        router.route("/relation/blockFriend").handler(BodyHandler.create());
        router.post("/relation/blockFriend").handler(RelationController::blockFriend);
        router.route("/relation/approveFriend").handler(BodyHandler.create());
        router.post("/relation/approveFriend").handler(RelationController::approveFriend);
        router.route("/relation/removeFriendRequest").handler(BodyHandler.create());
        router.post("/relation/removeFriendRequest").handler(RelationController::removeFriendRequest);
        router.route("/relation/unblockFriend").handler(BodyHandler.create());
        router.post("/relation/unblockFriend").handler(RelationController::unblockFriend);
        router.route("/relation/removeFriend").handler(BodyHandler.create());
        router.post("/relation/removeFriend").handler(RelationController::removeFriend);
        router.route("/relation/getRelationList").handler(BodyHandler.create());
        router.post("/relation/getRelationList").handler(RelationController::getRelationList);
        router.route("/relation/getRelationInfo").handler(BodyHandler.create());
        router.post("/relation/getRelationInfo").handler(RelationController::getRelationInfo);
        router.route("/relation/getUserGenderInfo").handler(BodyHandler.create());
        router.post("/relation/getUserGenderInfo").handler(RelationController::getUserGenderInfo);
        /*
         MessageController
         */
        router.route("/message/addMessage").handler(BodyHandler.create());
        router.post("/message/addMessage").handler(MessageController::addMessage);
        router.route("/message/addMessageByGroupId").handler(BodyHandler.create());
        router.post("/message/addMessageByGroupId").handler(MessageController::addMessageByGroupId);
        router.route("/message/getupdateStatusGetFriendNotificationsMessageSummaryList").handler(BodyHandler.create());
        router.post("/message/getMessageSummaryList").handler(MessageController::getMessageSummaryList);
        router.route("/message/getMessageList").handler(BodyHandler.create());
        router.post("/message/getMessageList").handler(MessageController::getMessageList);


        /*
         NotificationController
         */
        router.route("/notification/getNotificationCounter").handler(BodyHandler.create());
        router.post("/notification/getNotificationCounter").handler(NotificationController::getNotificationCounter);
        router.route("/notification/updateStatusGetFriendNotifications").handler(BodyHandler.create());
        router.post("/notification/updateStatusGetFriendNotifications").handler(NotificationController::updateStatusGetFriendNotifications);
        router.route("/notification/getFriendNotifications").handler(BodyHandler.create());
        router.post("/notification/getFriendNotifications").handler(NotificationController::getFriendNotifications);
        router.route("/notification/deleteFriendNotification").handler(BodyHandler.create());
        router.post("/notification/deleteFriendNotification").handler(NotificationController::deleteFriendNotification);
        router.route("/notification/updateStatusGetGeneralNotifications").handler(BodyHandler.create());
        router.post("/notification/updateStatusGetGeneralNotifications").handler(NotificationController::updateStatusGetGeneralNotifications);
        router.route("/notification/getGeneralNotifications").handler(BodyHandler.create());
        router.post("/notification/getGeneralNotifications").handler(NotificationController::getGeneralNotifications);

        /*
         PhotoController
         */
        router.get("/photo/getCategories").handler(PhotoController::getCategories);
        router.route("/photo/getCategoriesAndAlbums").handler(BodyHandler.create());
        router.post("/photo/getCategoriesAndAlbums").handler(PhotoController::getCategoriesAndAlbums);
        router.route("/photo/getAlbums").handler(BodyHandler.create());
        router.post("/photo/getAlbums").handler(PhotoController::getAlbums);
        router.route("/photo/getAlbum").handler(BodyHandler.create());
        router.post("/photo/getAlbum").handler(PhotoController::getAlbum);
        router.route("/photo/getAlbumInfo").handler(BodyHandler.create());
        router.post("/photo/getAlbumInfo").handler(PhotoController::getAlbumInfo);
        router.route("/photo/createAlbum").handler(BodyHandler.create());
        router.post("/photo/createAlbum").handler(PhotoController::createAlbum);
        router.route("/photo/editAlbum").handler(BodyHandler.create());
        router.post("/photo/editAlbum").handler(PhotoController::editAlbum);
        router.route("/photo/getAlbumComments").handler(BodyHandler.create());
        router.post("/photo/getAlbumComments").handler(PhotoController::getAlbumComments);
        router.route("/photo/deleteAlbum").handler(BodyHandler.create());
        router.post("/photo/deleteAlbum").handler(PhotoController::deleteAlbum);
        router.route("/photo/addAlbumLike").handler(BodyHandler.create());
        router.post("/photo/addAlbumLike").handler(PhotoController::addAlbumLike);
        router.route("/photo/getAlbumLikeList").handler(BodyHandler.create());
        router.post("/photo/getAlbumLikeList").handler(PhotoController::getAlbumLikeList);
        router.route("/photo/addAlbumComment").handler(BodyHandler.create());
        router.post("/photo/addAlbumComment").handler(PhotoController::addAlbumComment);
        router.route("/photo/editAlbumComment").handler(BodyHandler.create());
        router.post("/photo/editAlbumComment").handler(PhotoController::editAlbumComment);
        router.route("/photo/deleteAlbumComment").handler(BodyHandler.create());
        router.post("/photo/deleteAlbumComment").handler(PhotoController::deleteAlbumComment);
        router.route("/photo/getPhotos").handler(BodyHandler.create());
        router.post("/photo/getPhotos").handler(PhotoController::getPhotos);
        router.route("/photo/getUserPhotos").handler(BodyHandler.create());
        router.post("/photo/getUserPhotos").handler(PhotoController::getUserPhotos);
        router.route("/photo/getPhoto").handler(BodyHandler.create());
        router.post("/photo/getPhoto").handler(PhotoController::getPhoto);
        router.route("/photo/getPhotoListByCategory").handler(BodyHandler.create());
        router.post("/photo/getPhotoListByCategory").handler(PhotoController::getPhotoListByCategory);
        router.route("/photo/addPhotos").handler(BodyHandler.create());
        router.post("/photo/addPhotos").handler(PhotoController::addPhotos);
        router.route("/photo/editPhoto").handler(BodyHandler.create());
        router.post("/photo/editPhoto").handler(PhotoController::editPhoto);
        router.route("/photo/getPhotoComments").handler(BodyHandler.create());
        router.post("/photo/getPhotoComments").handler(PhotoController::getPhotoComments);
        router.route("/photo/deletePhoto").handler(BodyHandler.create());
        router.post("/photo/deletePhoto").handler(PhotoController::deletePhoto);
        router.route("/photo/addPhotoLike").handler(BodyHandler.create());
        router.post("/photo/addPhotoLike").handler(PhotoController::addPhotoLike);
        router.route("/photo/deletePhotoLike").handler(BodyHandler.create());
        router.post("/photo/deletePhotoLike").handler(PhotoController::deletePhotoLike);
        router.route("/photo/getPhotoLikeList").handler(BodyHandler.create());
        router.post("/photo/getPhotoLikeList").handler(PhotoController::getPhotoLikeList);
        router.route("/photo/addPhotoComment").handler(BodyHandler.create());
        router.post("/photo/addPhotoComment").handler(PhotoController::addPhotoComment);
        router.route("/photo/editPhotoComment").handler(BodyHandler.create());
        router.post("/photo/editPhotoComment").handler(PhotoController::editPhotoComment);
        router.route("/photo/deletePhotoComment").handler(BodyHandler.create());
        router.post("/photo/deletePhotoComment").handler(PhotoController::deletePhotoComment);


        /*
         VideoController
         */
        router.get("/video/getVideoCategories").handler(VideoController::getVideoCategories);
        router.route("/video/addVideo").handler(BodyHandler.create());
        router.post("/video/addVideo").handler(VideoController::addVideo);
        router.route("/video/getVideos").handler(BodyHandler.create());
        router.post("/video/getVideos").handler(VideoController::getVideos);
        router.route("/video/getVideo").handler(BodyHandler.create());
        router.post("/video/getVideo").handler(VideoController::getVideo);
        router.route("/video/updateVideo").handler(BodyHandler.create());
        router.post("/video/updateVideo").handler(VideoController::updateVideo);
        router.route("/video/deleteVideo").handler(BodyHandler.create());
        router.post("/video/deleteVideo").handler(VideoController::deleteVideo);
        router.route("/video/addVideoLike").handler(BodyHandler.create());
        router.post("/video/addVideoLike").handler(VideoController::addVideoLike);
        router.route("/video/getVideoLikeList").handler(BodyHandler.create());
        router.post("/video/getVideoLikeList").handler(VideoController::getVideoLikeList);
        router.route("/video/deleteVideoLike").handler(BodyHandler.create());
        router.post("/video/deleteVideoLike").handler(VideoController::deleteVideoLike);
        router.route("/video/addVideoComment").handler(BodyHandler.create());
        router.post("/video/addVideoComment").handler(VideoController::addVideoComment);
        router.route("/video/getVideoComments").handler(BodyHandler.create());
        router.post("/video/getVideoComments").handler(VideoController::getVideoComments);
        router.route("/video/editVideoComment").handler(BodyHandler.create());
        router.post("/video/editVideoComment").handler(VideoController::editVideoComment);
        
        
        /*
         pageController
         */
        router.get("/page/getCategorySubCategory").handler(PageController::getCategorySubCategory);
        router.route("/page/addPage").handler(BodyHandler.create());
        router.post("/page/addPage").handler(PageController::addPage);
        router.route("/page/UpdatePage").handler(BodyHandler.create());
        router.post("/page/UpdatePage").handler(PageController::updatePage);
        router.route("/page/getPageInfo").handler(BodyHandler.create());
        router.post("/page/getPageInfo").handler(PageController::getPageInfo);
        router.route("/page/addPageLike").handler(BodyHandler.create());
        router.post("/page/addPageLike").handler(PageController::addPageLike);
        
        server.requestHandler(router::accept).listen(8080);
    }
}
