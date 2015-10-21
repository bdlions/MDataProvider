/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.model;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;
import com.sampan.response.ResultEvent;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.VideoCategoryDAO;
import com.shampan.db.collections.VideoDAO;
import com.shampan.db.collections.builder.VideoCategoryDAOBuilder;
import com.shampan.db.collections.builder.VideoDAOBuilder;
import com.shampan.db.collections.fragment.common.Comment;
import com.shampan.db.collections.fragment.common.Like;
import com.shampan.db.collections.fragment.common.UserInfo;
import com.shampan.util.LogWriter;
import com.shampan.util.PropertyProvider;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.bson.Document;

/**
 *
 * @author Sampan-IT
 */
public class VideoModel {
    //    private Object resultEvent;

    ResultEvent resultEvent = new ResultEvent();

    public VideoModel() {
        PropertyProvider.add("com.shampan.properties/responseStatusCodes");

    }

    /**
     * This method will add video category
     *
     * @param categoryInfo category information
     * @author created by Rashida on 21 October
     */
    public String addVideoCategory(String categoryInfo) {
        MongoCollection<VideoCategoryDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOCATEGORIES.toString(), VideoCategoryDAO.class);
        VideoCategoryDAO category = new VideoCategoryDAOBuilder().build(categoryInfo);
        if (category != null) {
            mongoCollection.insertOne(category);
            resultEvent.setResponseCode(PropertyProvider.get("Created"));
            return resultEvent.toString();
        } else {
            resultEvent.setResponseCode(PropertyProvider.get("BadRequest"));
            return resultEvent.toString();

        }
    }

    /**
     * This method will return video categories
     *
     * @author created by Rashida on 21 October
     */
    public List<VideoCategoryDAO> getVideoCategories() {
        MongoCollection<VideoCategoryDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOCATEGORIES.toString(), VideoCategoryDAO.class);
        MongoCursor<VideoCategoryDAO> cursorCategoryList = mongoCollection.find().iterator();
        List<VideoCategoryDAO> categoryList = IteratorUtils.toList(cursorCategoryList);
        return categoryList;

    }

    /**
     * This method will add video
     *
     * @param videoInfo video information
     * @author created by Rashida on 21 October
     */
    public String addVideo(String videoInfo) {
        MongoCollection<VideoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOS.toString(), VideoDAO.class);
        VideoDAO vedio = new VideoDAOBuilder().build(videoInfo);
        if (vedio != null) {
            mongoCollection.insertOne(vedio);
            resultEvent.setResponseCode(PropertyProvider.get("Created"));
            return resultEvent.toString();
        } else {
            resultEvent.setResponseCode(PropertyProvider.get("BadRequest"));
            return resultEvent.toString();

        }
    }

    /**
     * This method will return a video information
     *
     * @param videoId video Id
     * @author created by Rashida on 21 October
     */
    public String getVideo(String videoId) {
        MongoCollection<VideoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOS.toString(), VideoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("videoId").is(videoId).get();
        VideoDAO videoInfo = mongoCollection.find(selectQuery).first();
        if (videoInfo != null) {
            return videoInfo.toString();
        } else {
            resultEvent.setResponseCode(PropertyProvider.get("Null"));
            return resultEvent.toString();
        }
    }

    /**
     * This method will update a video information
     *
     * @param videoId video Id
     * @author created by Rashida on 21 October
     */
    public String updateVideo(String videoId, String url) {
        MongoCollection<VideoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOS.toString(), VideoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("videoId").is(videoId).get();

        VideoDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("url", url)));
        if (result != null) {
            resultEvent.setResponseCode(PropertyProvider.get("Updated"));
            return resultEvent.toString();
        } else {
            resultEvent.setResponseCode(PropertyProvider.get("Null"));
            return resultEvent.toString();
        }
    }

    /**
     * This method will delete a video
     *
     * @param videoId video Id
     * @author created by Rashida on 21 October
     */
    public String deleteVideo(String videoId) {
        MongoCollection<VideoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOS.toString(), VideoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("videoId").is(videoId).get();
        VideoDAO result = mongoCollection.findOneAndDelete(selectQuery);
        if (result != null) {
            resultEvent.setResponseCode(PropertyProvider.get("Deleted"));
            return resultEvent.toString();
        } else {
            resultEvent.setResponseCode(PropertyProvider.get("BadRequest"));
            return resultEvent.toString();
        }
    }

    /*
     * This method will add a video like, 
     * @param videoId, video id
     * @param likeInfo, like user information
     * @author created by Rashida on 21th September 2015
     */
    public String addVideoLike(String videoId, String likeInfo) {
        MongoCollection<VideoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOS.toString(), VideoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("videoId").is(videoId).get();
        Like videolikeInfo = Like.getLikeInfo(likeInfo);
        if (videolikeInfo != null) {
            VideoDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("like", JSON.parse(videolikeInfo.toString()))));
            resultEvent.setResponseCode(PropertyProvider.get("Created"));
            return resultEvent.toString();
        } else {
            resultEvent.setResponseCode(PropertyProvider.get("BadRequest"));
            return resultEvent.toString();

        }
    }

    /*
     * This method will add a video like, 
     * @param videoId, video id
     * @author created by Rashida on 21th September 2015
     */
    public String getVideoLikeList(String videoId) {
        MongoCollection<VideoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOS.toString(), VideoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("videoId").is(videoId).get();
        Document pQuery = new Document();
        pQuery.put("like", "$all");
        VideoDAO videoLikeList = mongoCollection.find(selectQuery).projection(pQuery).first();
        if (videoLikeList != null) {
            return videoLikeList.toString();
        } else {
            resultEvent.setResponseCode(PropertyProvider.get("Null"));
            return resultEvent.toString();
        }
    }
    /*
     * This method will delete a video like, 
     * @param videoId, video id
     * @author created by Rashida on 21th September 2015
     */

    public String deleteVideoLike(String videoId, String likeId) {
        MongoCollection<VideoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOS.toString(), VideoDAO.class);
        Document sQuery = new Document();
        sQuery.put("videoId", videoId);
        sQuery.put("comment.id", likeId);
        Document pQuery = new Document();
        pQuery.put("id", likeId);
        VideoDAO result = mongoCollection.findOneAndUpdate(sQuery, new Document("$pull", new Document("like", pQuery)));
        if (result != null) {
            resultEvent.setResponseCode(PropertyProvider.get("Deleted"));
            return resultEvent.toString();
        } else {
            resultEvent.setResponseCode(PropertyProvider.get("BadRequest"));
            return resultEvent.toString();
        }
    }

    /*
     * This method will add a video comment, 
     * @param videoId, video id
     * @param commentInfo, like user information and comment info
     * @author created by Rashida on 21th September 2015
     */
    public String addVideoComment(String videoId, String commentInfo) {
        MongoCollection<VideoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOS.toString(), VideoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("videoId").is(videoId).get();
        Comment videoCommentInfo = Comment.getCommentInfo(commentInfo);
        try {
            if (videoCommentInfo != null) {
                VideoDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("comment", JSON.parse(videoCommentInfo.toString()))));
                if (result != null) {
                    resultEvent.setResponseCode(PropertyProvider.get("Created"));
                    return resultEvent.toString();
                }
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error("null value exception");
        }
        resultEvent.setResponseCode(PropertyProvider.get("BadRequest"));
        return resultEvent.toString();
    }

    /*
     * This method will get all comments of a video , 
     * @param videoId, video id
     * @author created by Rashida on 1st Octobar 2015
     */
    public String getVideoComments(String videoId) {
        MongoCollection<VideoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOS.toString(), VideoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("videoId").is(videoId).get();
        Document pQuery = new Document();
        pQuery.put("comment", "$all");
        VideoDAO videoCommentList = mongoCollection.find(selectQuery).projection(pQuery).first();
        if (videoCommentList != null) {
            return videoCommentList.toString();
        } else {
            resultEvent.setResponseCode(PropertyProvider.get("Null"));
            return resultEvent.toString();
        }
    }

    /*
     * This method will add a video comment, 
     * @param videoId, video id
     * @param commentId, comment id
     * @param commentInfo, like user information and comment info
     * @author created by Rashida on 21th September 2015
     */
    public String editVideoComment(String videoId, String commentId, String commentInfo) {
        MongoCollection<VideoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOS.toString(), VideoDAO.class);
        Document selectQuery = new Document();
        selectQuery.put("videoId", videoId);
        selectQuery.put("comment.id", commentId);
        VideoDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("comment.$.description", commentInfo)));
        if (result != null) {
            resultEvent.setResponseCode(PropertyProvider.get("Updated"));
            return resultEvent.toString();
        } else {
            resultEvent.setResponseCode(PropertyProvider.get("BadRequest"));
            return resultEvent.toString();
        }

    }

    /*
     * This method will delete comment, 
     * @param videoId, video id
     * @param commentId, comment id
     * @author created by Rashida on 21th September 2015
     */
    public String deleteVideoComment(String videoId, String commentId) {
        MongoCollection<VideoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOS.toString(), VideoDAO.class);
        Document sQuery = new Document();
        sQuery.put("videoId", videoId);
        sQuery.put("comment.id", commentId);
        Document pQuery = new Document();
        pQuery.put("id", commentId);
        VideoDAO result = mongoCollection.findOneAndUpdate(sQuery, new Document("$pull", new Document("comment", pQuery)));
        if (result != null) {
            resultEvent.setResponseCode(PropertyProvider.get("Deleted"));
            return resultEvent.toString();
        } else {
            resultEvent.setResponseCode(PropertyProvider.get("BadRequest"));
            return resultEvent.toString();
        }
    }

}
