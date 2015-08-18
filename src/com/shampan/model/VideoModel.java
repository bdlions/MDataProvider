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
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.VideoCategoryDAO;
import com.shampan.db.collections.VideoDAO;
import com.shampan.db.collections.builder.VideoCategoryDAOBuilder;
import com.shampan.db.collections.builder.VideoDAOBuilder;
import com.shampan.db.collections.fragment.common.UserInfo;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.bson.Document;

/**
 *
 * @author Sampan-IT
 */
public class VideoModel {

    public VideoModel() {

    }

    public String addCategory(String categoryInfo) {
        MongoCollection<VideoCategoryDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOCATEGORIES.toString(), VideoCategoryDAO.class);
        VideoCategoryDAO category = new VideoCategoryDAOBuilder().build(categoryInfo);
        mongoCollection.insertOne(category);
        return "successful";
    }

    public List<VideoCategoryDAO> getCategories() {
        MongoCollection<VideoCategoryDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOCATEGORIES.toString(), VideoCategoryDAO.class);
        MongoCursor<VideoCategoryDAO> cursorCategoryList = mongoCollection.find().iterator();
        List<VideoCategoryDAO> categoryList = IteratorUtils.toList(cursorCategoryList);
        return categoryList;

    }

    public String addVideo(String videoInfo) {
        MongoCollection<VideoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOS.toString(), VideoDAO.class);
        VideoDAO vedio = new VideoDAOBuilder().build(videoInfo);
        mongoCollection.insertOne(vedio);
        return "successful";
    }

    public String getVideo(String videoId) {
        MongoCollection<VideoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOS.toString(), VideoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("videoId").is(videoId).get();
        VideoDAO photoInfo = mongoCollection.find(selectQuery).first();
        return photoInfo.toString();
    }

    public String updateVideo(String videoId, String url) {
        MongoCollection<VideoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOS.toString(), VideoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("videoId").is(videoId).get();
        mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("url", url)));
        return "successful";
    }

    public String deleteVideo(String videoId) {
        MongoCollection<VideoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOS.toString(), VideoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("videoId").is(videoId).get();
        mongoCollection.findOneAndDelete(selectQuery);
        return "successful";
    }

    public static void main(String[] args) {
        String userId = "100157";
        String videoId = "2";
        UserInfo userInfo = new UserInfo();
        userInfo.setFristName("Alamgir");
        userInfo.setLastName("Kabir");
        userInfo.setUserId(userId);
        VideoCategoryDAO vedioCategory = new VideoCategoryDAOBuilder()
                .setCategoryId("3")
                .setTitle("Islamic History")
                .build();
        VideoDAO videoInfo = new VideoDAOBuilder()
                .setVideoId(videoId)
                .setUserId(userId)
                .setUserInfo(userInfo)
                .setUrl("https://www.youtube.com/watch?v=-27loHyoODs")
                .setCategoryId("1")
                .build();
//        System.out.println(videoInfo.toString());

        VideoModel obj = new VideoModel();
        System.out.println(obj.deleteVideo(videoId));
//        System.out.println(obj.getVideo(videoId));
//        System.out.println(obj.addVideo(videoInfo.toString()));
//        System.out.println(obj.addCategory(vedioCategory.toString()));
//        System.out.println(obj.getCategories());
    }
}
