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
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.AlbumDAO;
import com.shampan.db.collections.PhotoCategoryDAO;
import com.shampan.db.collections.PhotoDAO;
import com.shampan.db.collections.builder.AlbumDAOBuilder;
import com.shampan.db.collections.builder.PhotoCategoryBuilder;
import com.shampan.db.collections.builder.PhotoDAOBuilder;
import com.shampan.db.collections.fragment.common.Comment;
import com.shampan.db.collections.fragment.common.Like;
import com.shampan.db.collections.fragment.common.Share;
import com.shampan.db.collections.fragment.common.UserInfo;
import com.shampan.db.collections.fragment.photo.Image;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.bson.Document;

/**
 *
 * @author Sampan-IT
 */
public class PhotoModel {

    public PhotoModel() {

    }

    public String addCategory(String categoryInfo) {
        MongoCollection<PhotoCategoryDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.PHOTOCATEGORIES.toString(), PhotoCategoryDAO.class);
        PhotoCategoryDAO category = new PhotoCategoryBuilder().build(categoryInfo);
        mongoCollection.insertOne(category);
        return "successful";
    }

    public List<PhotoCategoryDAO> getCategories() {
        MongoCollection<PhotoCategoryDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.PHOTOCATEGORIES.toString(), PhotoCategoryDAO.class);
        MongoCursor<PhotoCategoryDAO> cursorCategoryList = mongoCollection.find().iterator();
        List<PhotoCategoryDAO> categoryList = IteratorUtils.toList(cursorCategoryList);
        return categoryList;

    }

    public String createAlbum(String albumInfo) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        AlbumDAO statusInfoObj = new AlbumDAOBuilder().build(albumInfo);
        mongoCollection.insertOne(statusInfoObj);
        return "successful";
    }

    public List<AlbumDAO> getAlbums(String userId) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        MongoCursor<AlbumDAO> cursorAlbumList = mongoCollection.find(selectQuery).iterator();
        List<AlbumDAO> albumList = IteratorUtils.toList(cursorAlbumList);
        return albumList;

    }
    public List<AlbumDAO> getAlbumList(String userId) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        MongoCursor<AlbumDAO> cursorAlbumList = mongoCollection.find(selectQuery).iterator();
        List<AlbumDAO> albumList = IteratorUtils.toList(cursorAlbumList);
        return albumList;

    }

    public String getAlbum(String albumId) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("albumId").is(albumId).get();
        AlbumDAO albumInfo = mongoCollection.find(selectQuery).first();
        return albumInfo.toString();
    }

    public String addAlbumLike(String albumId, String likeInfo) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("albumId").is(albumId).get();
        Like albumlikeInfo = Like.getLikeInfo(likeInfo);
        mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("like", JSON.parse(albumlikeInfo.toString()))));
        return "Succefull";
    }

    public String addAlbumComment(String albumId, String commentInfo) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("albumId").is(albumId).get();
        Comment albumCommentInfo = Comment.getCommentInfo(commentInfo);
        mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("comment", JSON.parse(albumCommentInfo.toString()))));
        return "Succefull";
    }

    public String addPhoto(String photoInfo) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
        PhotoDAO photos = new PhotoDAOBuilder().build(photoInfo);
        mongoCollection.insertOne(photos);
        return "successful";
    }

    public String getPhoto(String photoId) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
        PhotoDAO photoInfo = mongoCollection.find(selectQuery).first();
        return photoInfo.toString();
    }

    public String updatePhoto(String photoId, String image) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
        mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("image", image)));
        return "successful";
    }

    public String deletePhoto(String photoId) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
        mongoCollection.findOneAndDelete(selectQuery);
        return "successful";
    }

    public List<PhotoDAO> getPhotos(String albumId) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("albumId").is(albumId).get();
        MongoCursor<PhotoDAO> cursorStatusInfoList = mongoCollection.find(selectQuery).iterator();
        List<PhotoDAO> statusList = IteratorUtils.toList(cursorStatusInfoList);
        return statusList;

    }

    public static void main(String[] args) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);

        String userId = "100157";
        String albumId = "1";
        String photoId = "1";
        UserInfo userInfo = new UserInfo();
        userInfo.setFristName("Rashida");
        userInfo.setLastName("Sultana");
        userInfo.setUserId(userId);

        UserInfo refUserInfo = new UserInfo();
        refUserInfo.setFristName("Alamgir");
        refUserInfo.setLastName("Kabir");
        refUserInfo.setUserId(userId);

        Image image1 = new Image();
        image1.setImage("Beli.jpg");
//        Image image2 = new Image();
//        image2.setImage("Cheri.jpg");
//        Image image3 = new Image();
//        image3.setImage("kodom.jpg");
//        List<Image> totalImg = new ArrayList<Image>();
//        totalImg.add(image1);
//        totalImg.add(image2);
        Like LikeuserInfo = new Like();
        LikeuserInfo.setUserInfo(refUserInfo);

        Comment albumCommentInfo = new Comment();
        albumCommentInfo.setUserInfo(refUserInfo);
        albumCommentInfo.setDescription("I like your track !!");

        Share shareShareInfo = new Share();
        shareShareInfo.setUserInfo(refUserInfo);
        AlbumDAO userAlbum = new AlbumDAOBuilder()
                .setAlbumId(albumId)
                .setTitle("Flowers Album")
                .setDescription("My fevorate garden at font my home")
                .setDefaultImg("kamini.jpg")
                .setTotalImg(5)
                .setUserInfo(userInfo)
                .build();

        PhotoDAO photoInfo = new PhotoDAOBuilder()
                .setPhotoId("1")
                .setAlbumId(albumId)
                .setCategoryId(1)
                .setImage("kamini.jpg")
                .build();

        PhotoCategoryDAO category = new PhotoCategoryBuilder()
                .setCategoryId("3")
                .setTitle("Art")
                .build();
//        PhotoCategoryDAO statusInfoObj = new PhotoCategoryBuilder().build();
//  PhotoCategoryDAO category = new PhotoCategoryBuilder().build(category.toString());
//        System.out.println(category);
        PhotoModel obj = new PhotoModel();
        System.out.println(obj.getAlbums(userId));
//        System.out.println(obj.getCategories());
//        System.out.println(obj.addCategory(category.toString()));
//        obj.getPhoto(photoId);
//        System.out.println(obj.addPhoto(photoInfo.toString()));
//        System.out.println(obj.getPhoto(photoId));
//        System.out.println(obj.updatePhoto(photoId, "Beli.jpg"));
//        System.out.println(obj.deletePhoto(photoId));
//        System.out.println(obj.getStatuses(albumId));
//        System.out.println(obj.createAlbum(userAlbum.toString()));
//        System.out.println(obj.getAlbum(albumId));
//        System.out.println(obj.addAlbumLike(albumId, LikeuserInfo.toString()));
//        System.out.println(obj.addAlbumComment(albumId, albumCommentInfo.toString()));
//        System.out.println(obj.addImage(albumId, image3.toString()));
    }
}
