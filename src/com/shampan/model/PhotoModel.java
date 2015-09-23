/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.model;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;
import com.sampan.response.ResultEvent;
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
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Sampan-IT
 */
public class PhotoModel {

    ResultEvent resultEvent = new ResultEvent();

    public PhotoModel() {

    }
    //.........................Start Album module.....................................

    /*
     * This method will create albums categorise, 
     * @param categoryInfo, category information
     * @author created by Rashida on 21th September 2015
     */
    public String addCategory(String categoryInfo) {
        MongoCollection<PhotoCategoryDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.PHOTOCATEGORIES.toString(), PhotoCategoryDAO.class);
        PhotoCategoryDAO category = new PhotoCategoryBuilder().build(categoryInfo);
        mongoCollection.insertOne(category);
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    /*
     * This method will return all albums categorises, 
     * @author created by Rashida on 21th September 2015
     */
    public List<PhotoCategoryDAO> getCategories() {
        MongoCollection<PhotoCategoryDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.PHOTOCATEGORIES.toString(), PhotoCategoryDAO.class);
        MongoCursor<PhotoCategoryDAO> cursorCategoryList = mongoCollection.find().iterator();
        List<PhotoCategoryDAO> categoryList = IteratorUtils.toList(cursorCategoryList);
        return categoryList;

    }
    /*
     * This method will return all albums  list of a user, 
     * @param userId user id 
     * @author created by Rashida on 21th September 2015
     */

    public List<AlbumDAO> getAlbumList(String userId) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("albumId", "$all");
        pQuery.put("title", "$all");
        MongoCursor<AlbumDAO> cursorAlbumList = mongoCollection.find(selectQuery).projection(pQuery).iterator();
        List<AlbumDAO> albumList = IteratorUtils.toList(cursorAlbumList);
        return albumList;

    }

    /*
     * This method will return all albums of a user, 
     * @param userId,user id,
     * @author created by Rashida on 21th September 2015
     */
    public List<AlbumDAO> getAlbums(String userId) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("userId").is(userId).get();
        Document pQuery = new Document();
        pQuery.put("albumId","$all");
        pQuery.put("title","$all");
        pQuery.put("totalImg","$all");
        pQuery.put("defaultImg","$all");
        MongoCursor<AlbumDAO> cursorAlbumList = mongoCollection.find(selectQuery).projection(pQuery).iterator();
        List<AlbumDAO> albumList = IteratorUtils.toList(cursorAlbumList);
        return albumList;

    }
    /*
     * This method will return  a album of a user, 
     * @param albumId,album id,
     * @author created by Rashida on 21th September 2015
     */

    public String getAlbum(String albumId) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("albumId").is(albumId).get();
        AlbumDAO albumInfo = mongoCollection.find(selectQuery).first();
        return albumInfo.toString();
    }

    /*
     * This method will create user album, 
     * @param albumInfo,user album information
     * @author created by Rashida on 21th September 2015
     */
    public String createAlbum(String albumInfo) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        AlbumDAO statusInfoObj = new AlbumDAOBuilder().build(albumInfo);
        mongoCollection.insertOne(statusInfoObj);
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    /*
     * This method will edit user album, 
     * @param albumId, album id
     * @param albumInfo,user album information
     * @author created by Rashida on 21th September 2015
     */
    public String editAlbum(String albumId, String albumInfo) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("albumId").is(albumId).get();
        AlbumDAO albumInfoObj = new AlbumDAOBuilder().build(albumInfo);
        System.out.println(albumInfoObj);
        Document modifiedQuery = new Document();
        modifiedQuery.put("title", albumInfoObj.getTitle());
        modifiedQuery.put("description", albumInfoObj.getDescription());
        modifiedQuery.put("totalImg", albumInfoObj.getTotalImg());
        AlbumDAO result = mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", modifiedQuery));
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }
    /*
     * This method will delete user album, 
     * @param albumId, album id
     * @author created by Rashida on 21th September 2015
     */

    public String deleteAlbum(String albumId) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("albumId").is(albumId).get();
        AlbumDAO albumInfo = mongoCollection.findOneAndDelete(selectQuery);
        return albumInfo.toString();
    }

    /*
     * This method will add a album like, 
     * @param albumId, album id
     * @param likeInfo, like user information
     * @author created by Rashida on 21th September 2015
     */
    public String addAlbumLike(String albumId, String likeInfo) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("albumId").is(albumId).get();
        Like albumlikeInfo = Like.getLikeInfo(likeInfo);
        mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("like", JSON.parse(albumlikeInfo.toString()))));
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }
    /*
     * This method will add a album comment, 
     * @param albumId, album id
     * @param commentInfo, like user information and comment info
     * @author created by Rashida on 21th September 2015
     */

    public String addAlbumComment(String albumId, String commentInfo) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("albumId").is(albumId).get();
        Comment albumCommentInfo = Comment.getCommentInfo(commentInfo);
        mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("comment", JSON.parse(albumCommentInfo.toString()))));
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    /*
     * This method will add a album comment, 
     * @param albumId, album id
     * @param commentId, comment id
     * @param commentInfo, like user information and comment info
     * @author created by Rashida on 21th September 2015
     */
    public String editAlbumComment(String albumId, String commentId, String commentInfo) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        Document selectQuery = new Document();
        selectQuery.put("albumId", albumId);
        selectQuery.put("comment.id", commentId);
        mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("comment.$.description", commentInfo)));
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    public String deleteAlbumComment(String albumId, String commentId) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    //.......................................End Album module.....................
    /*
     * This method will return all photos of a album, 
     * @param albumId, album id
     * @author created by Rashida on 21th September 2015
     */
    public List<PhotoDAO> getPhotos(String albumId) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("albumId").is(albumId).get();
        MongoCursor<PhotoDAO> cursorStatusInfoList = mongoCollection.find(selectQuery).iterator();
        List<PhotoDAO> statusList = IteratorUtils.toList(cursorStatusInfoList);
        return statusList;

    }
    /*
     * This method will return a photo , 
     * @param photoId, photo id
     * @author created by Rashida on 21th September 2015
     */

    public String getPhoto(String photoId) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
        PhotoDAO photoInfo = mongoCollection.find(selectQuery).first();
        return photoInfo.toString();
    }

    /*
     * This method will add list of photos , 
     * @param photoInfoList, photo list
     * @author created by Rashida on 21th September 2015
     */
    public String addPhotos(String photoInfoList) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
        JSONArray photoArray = new JSONArray(photoInfoList);
        ArrayList<PhotoDAO> photoList = new ArrayList<PhotoDAO>();
        if (photoArray != null) {
            for (int i = 0; i < photoArray.length(); i++) {
                PhotoDAO photoInfoObj = new PhotoDAOBuilder().build(photoArray.get(i).toString());
                photoList.add(photoInfoObj);
            }
        }
        mongoCollection.insertMany(photoList);
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    /*
     * This method will edit a photo , 
     * @param photoId, photo id
     * @param photoInfo, photo info
     * @author created by Rashida on 21th September 2015
     */
    public String editPhoto(String photoId, String photoInfo) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
        PhotoDAO pInfo = new PhotoDAOBuilder().build(photoInfo);
        Document modifiedInfo = new Document();
        modifiedInfo.put("image", pInfo.getImage());
        modifiedInfo.put("description", pInfo.getDescription());
        mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", modifiedInfo));
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }
    /*
     * This method will delete a photo , 
     * @param photoId, photo id
     * @author created by Rashida on 21th September 2015
     */

    public String deletePhoto(String photoId) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
        mongoCollection.findOneAndDelete(selectQuery);
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }
    /*
     * This method will add a photo like , 
     * @param photoId, photo id
     * @param likeInfo, like user information
     * @author created by Rashida on 21th September 2015
     */

    public String addPhotoLike(String photoId, String likeInfo) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
        Like photoLikeInfo = Like.getLikeInfo(likeInfo);
        mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("like", JSON.parse(photoLikeInfo.toString()))));
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    /*
     * This method will delete a photo like , 
     * @param photoId, photo id
     * @param likeId, like id
     * @author created by Rashida on 21th September 2015
     */
    public String deletePhotoLike(String photoId, String likeId) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
        Document selectQuery = new Document();
        selectQuery.put("photoId", photoId);
        selectQuery.put("like.id", likeId);
        Document projectQuery = new Document();
        projectQuery.put("like.$", "$all");
        System.out.println(mongoCollection.find(selectQuery).projection(projectQuery).first());
        PhotoDAO photoLike = mongoCollection.find(selectQuery).projection(projectQuery).first();
        System.out.println(photoLike.getLike());

//        System.out.println(new Document("$pull", new Document("$like.$",null)));
        mongoCollection.findOneAndUpdate(selectQuery, new Document("$pull", new ArrayList<>()));
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    /*
     * This method will add a photo comment , 
     * @param photoId, photo id
     * @param commentInfo, comment info
     * @author created by Rashida on 21th September 2015
     */
    public String addPhotoComment(String photoId, String commentInfo) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
        Comment photoCommentInfo = Comment.getCommentInfo(commentInfo);
        mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("comment", JSON.parse(photoCommentInfo.toString()))));
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    /*
     * This method will edit a photo comment , 
     * @param photoId, photo id
     * @param commentId, comment id
     * @param commentInfo, commentInfo
     * @author created by Rashida on 21th September 2015
     */
    public String editPhotoComment(String photoId, String commentId, String commentInfo) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
        Document selectQuery = new Document();
        selectQuery.put("photoId", photoId);
        selectQuery.put("comment.id", commentId);
        mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("comment.$.description", commentInfo)));
        resultEvent.setResponseCode("100157");
        return resultEvent.toString();
    }

    public String deletePhotoComment(String photoId, String commentId) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
        return resultEvent.toString();
    }

}
