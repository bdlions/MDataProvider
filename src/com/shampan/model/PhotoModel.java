package com.shampan.model;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.client.FindIterable;
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
import com.shampan.db.collections.builder.PhotoCategoryDAOBuilder;
import com.shampan.db.collections.builder.PhotoDAOBuilder;
import com.shampan.db.collections.fragment.common.Comment;
import com.shampan.db.collections.fragment.common.Like;
import com.shampan.db.collections.fragment.common.Share;
import com.shampan.db.collections.fragment.common.UserInfo;
import com.shampan.util.LogWriter;
import com.shampan.util.PropertyProvider;
import com.shampan.util.Utility;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections4.IteratorUtils;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sampan-IT
 */
public class PhotoModel {

    Utility utility = new Utility();
    ResultEvent resultEvent = new ResultEvent();
    UserModel userModel = new UserModel();
    NotificationModel notificationModel = new NotificationModel();
    private final Logger logger = LoggerFactory.getLogger(PhotoModel.class);

    public PhotoModel() {
        PropertyProvider.add("com.shampan.properties/responseStatusCodes");
        PropertyProvider.add("com.shampan.properties/response");
        PropertyProvider.add("com.shampan.properties/attributes");
        PropertyProvider.add("com.shampan.properties/photos");
    }

    /**
     * This method will return result event
     *
     * @return ResultEvent, result event
     */
    public ResultEvent getResultEvent() {
        return resultEvent;
    }

    /**
     * This method will set result event
     *
     * @param resultEvent, result event
     */
    public void setResultEvent(ResultEvent resultEvent) {
        this.resultEvent = resultEvent;
    }
    //.........................Start Album module.....................................

    /*
     * This method will create albums categorise, 
     * @param categoryInfo, category information
     * @author created by Rashida on 21th September 2015
     */
    public ResultEvent addCategory(String categoryInfo) {
        try {
            MongoCollection<PhotoCategoryDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.PHOTOCATEGORIES.toString(), PhotoCategoryDAO.class);
            PhotoCategoryDAO category = new PhotoCategoryDAOBuilder().build(categoryInfo);
            mongoCollection.insertOne(category);
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

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
        pQuery.put("albumId", "$all");
        pQuery.put("title", "$all");
        pQuery.put("totalImg", "$all");
        pQuery.put("defaultImg", "$all");
        MongoCursor<AlbumDAO> cursorAlbumList = mongoCollection.find(selectQuery).projection(pQuery).iterator();
        List<AlbumDAO> albumList = IteratorUtils.toList(cursorAlbumList);
        return albumList;

    }

    public AlbumDAO getAlbum(String albumId) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("albumId").is(albumId).get();
        Document pQuery = new Document();
        pQuery.put("albumId", "$all");
        pQuery.put("photoId", "$all");
        pQuery.put("totalImg", "$all");
        AlbumDAO albumInfo = mongoCollection.find(selectQuery).projection(pQuery).first();
        return albumInfo;
    }

    public AlbumDAO getAlbumInfo(String userId, String albumId) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        String attUserId = PropertyProvider.get("USER_ID");
        String attAlbumId = PropertyProvider.get("ALBUM_ID");
        String attPhotoId = PropertyProvider.get("PHOTO_ID");
        String attTotalImg = PropertyProvider.get("TOTAL_IMG");
        String attReferenceId = PropertyProvider.get("REFERENCE_ID");
        Document sQuery = new Document();
        sQuery.put(attUserId, userId);
        sQuery.put(attAlbumId, albumId);
        Document pQuery = new Document();
        pQuery.put(attUserId, "$all");
        pQuery.put(attAlbumId, "$all");
        pQuery.put(attPhotoId, "$all");
        pQuery.put(attTotalImg, "$all");
        pQuery.put(attReferenceId, "$all");
        AlbumDAO albumInfo = mongoCollection.find(sQuery).projection(pQuery).first();
        return albumInfo;
    }

    /*
     * This method will return  a album of a user, 
     * @param albumId,album id,
     * @author created by Rashida on 21th September 2015
     */
    public String getAlbum(String userId, String albumId) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        Document selectDocument = new Document();
        selectDocument.put("userId", userId);
        selectDocument.put("albumId", albumId);
//        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("albumId").is(albumId).get();
        AlbumDAO albumInfo = mongoCollection.find(selectDocument).first();
        JSONObject albumInfoJson = new JSONObject();
        try {
            albumInfoJson.put("albumId", albumInfo.getAlbumId());
            albumInfoJson.put("userId", albumInfo.getUserId());
            albumInfoJson.put("title", albumInfo.getTitle());
            albumInfoJson.put("description", albumInfo.getDescription());
            albumInfoJson.put("totalImg", albumInfo.getTotalImg());
            albumInfoJson.put("defaultImg", albumInfo.getTotalImg());
            if (albumInfo.getLike() != null) {
                int likeSize = albumInfo.getLike().size();
                if (likeSize > 0) {
                    albumInfoJson.put("likeCounter", likeSize);

                }
                int i = 0;
                while (likeSize > 0) {
                    String tempUserId = albumInfo.getLike().get(i).getUserInfo().getUserId();
                    if (tempUserId.equals(userId)) {
                        albumInfoJson.put("likeStatus", "1");
                    }
                    likeSize--;
                    i++;
                }

            }
            if (albumInfo.getComment() != null) {
                int commentSize = albumInfo.getComment().size();
                List<Comment> commentList = new ArrayList();
                if (commentSize >= 1) {
                    Comment lastComment = albumInfo.getComment().get(commentSize - 1);
                    commentList.add(lastComment);
                }
                if (commentSize >= 2) {
                    Comment secondlastComment = albumInfo.getComment().get(commentSize - 2);
                    commentList.add(secondlastComment);
                }
                if (commentSize > 2) {
                    albumInfoJson.put("commentCounter", commentSize - 2);
                }
                albumInfoJson.put("comment", commentList);
            }
            if (albumInfo.getShare() != null) {
                int shareSize = albumInfo.getShare().size();
                if (shareSize > 0) {
                    albumInfoJson.put("shareCounter", shareSize);
                }
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error(npe);
        }

        return albumInfoJson.toString();
    }

    /*
     * This method will create user album, 
     * @param albumInfo,user album information
     * @author created by Rashida on 21th September 2015
     */
    public ResultEvent createAlbum(String albumInfo) {
        try {
            MongoCollection<AlbumDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
            AlbumDAO albumInfoObj = new AlbumDAOBuilder().build(albumInfo);
            if (albumInfoObj != null) {
                mongoCollection.insertOne(albumInfoObj);
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("NULL_POINTER_EXCEPTION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    /*
     * This method will edit user album, 
     * @param albumId, album id
     * @param albumInfo,user album information
     * @author created by Rashida on 21th September 2015
     */
    public ResultEvent editAlbum(String userId, String albumId, String albumInfo) {
        try {
            MongoCollection<AlbumDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);

            Document selectionDocument = new Document();
            selectionDocument.put("userId", userId);
            selectionDocument.put("albumId", albumId);
            AlbumDAO albumInfoObj = new AlbumDAOBuilder().build(albumInfo);
            Document modifiedQuery = new Document();
            modifiedQuery.put("photoId", albumInfoObj.getPhotoId());
            modifiedQuery.put("defaultImg", albumInfoObj.getDefaultImg());
            modifiedQuery.put("totalImg", albumInfoObj.getTotalImg());
            modifiedQuery.put("referenceId", albumInfoObj.getReferenceId());
            AlbumDAO result = mongoCollection.findOneAndUpdate(selectionDocument, new Document("$set", modifiedQuery));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent editAlbumTotalImg(String userId, String albumId, int totalImgInfo) {
        try {
            MongoCollection<AlbumDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
            Document selectionDocument = new Document();
            selectionDocument.put("userId", userId);
            selectionDocument.put("albumId", albumId);
            AlbumDAO result = mongoCollection.findOneAndUpdate(selectionDocument, new Document("$set", new Document("totalImg", totalImgInfo)));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
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
    public ResultEvent addAlbumLike(String albumId, String likeInfo) {
        try {
            MongoCollection<AlbumDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("albumId").is(albumId).get();
            Like albumlikeInfo = Like.getLikeInfo(likeInfo);
            mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("like", JSON.parse(albumlikeInfo.toString()))));
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /*
     * This method will add a photo like , 
     * @param referenceId, reference id
     * @param likeInfo, like user information
     * @author created by Rashida on 21th September 2015
     */
    public ResultEvent addAlbumLikeByReferenceId(String referenceId, String likeInfo) {
        try {
            MongoCollection<AlbumDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
            String attrReferenceId = PropertyProvider.get("REFERENCE_ID");
            String attrLike = PropertyProvider.get("LIKE");
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start(attrReferenceId).is(referenceId).get();
            Like albumlikeInfo = Like.getLikeInfo(likeInfo);
            if (albumlikeInfo != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document(attrLike, JSON.parse(albumlikeInfo.toString()))));
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public String getAlbumLikeList(String albumId) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class);
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("albumId").is(albumId).get();
        Document pQuery = new Document();

        pQuery.put("like", "$all");
        AlbumDAO albumLikeList = mongoCollection.find(selectQuery).projection(pQuery).first();

        return albumLikeList.toString();
    }
    /*
     * This method will add a album comment, 
     * @param albumId, album id
     * @param commentInfo, like user information and comment info
     * @author created by Rashida on 21th September 2015
     */

    public String addAlbumComment(String albumId, String commentInfo) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class
                );
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("albumId").is(albumId).get();
        Comment albumCommentInfo = Comment.getCommentInfo(commentInfo);

        try {
            if (albumCommentInfo != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("comment", JSON.parse(albumCommentInfo.toString()))));
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error("null value exception");
        }

        resultEvent.setResponseCode(
                "100157");
        return resultEvent.toString();
    }

    /*
     * This method will get all comments of a album , 
     * @param albumId, album id
     * @author created by Rashida on 1st Octobar 2015
     */
    public String getAlbumComments(String albumId) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class
                );
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("albumId").is(albumId).get();
        Document pQuery = new Document();

        pQuery.put(
                "comment", "$all");
        AlbumDAO albumCommentList = mongoCollection.find(selectQuery).projection(pQuery).first();

        return albumCommentList.toString();
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
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class
                );
        Document selectQuery = new Document();

        selectQuery.put(
                "albumId", albumId);
        selectQuery.put(
                "comment.id", commentId);
        mongoCollection.findOneAndUpdate(selectQuery,
                new Document("$set", new Document("comment.$.description", commentInfo)));
        resultEvent.setResponseCode(
                "100157");
        return resultEvent.toString();
    }

    public String deleteAlbumComment(String albumId, String commentId) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERALBUMS.toString(), AlbumDAO.class
                );
        resultEvent.setResponseCode(
                "100157");
        return resultEvent.toString();
    }

    //.......................................End Album module.....................
//....................................... Start Photo Module........................
    /*
     * This method will return all photos of a album, 
     * @param userId, user id
     * @author created by Rashida on 21th September 2015
     */
    public List<PhotoDAO> getUserPhotos(String userId, String albumId, int offset, int limit) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class
                );
        Document selectDocument = new Document();
        selectDocument.put("userId", userId);
        selectDocument.put("albumId", albumId);
//        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("albumId").is(albumId).get();
        FindIterable<PhotoDAO> photoList = mongoCollection.find(selectDocument).skip(offset).limit(offset);
        List<PhotoDAO> photos = new ArrayList<>();
        for (PhotoDAO photo : photoList) {
            photos.add(photo);
//            System.out.println(photo);
        }

//        System.out.println(photos);
        return photos;

    }
    /*
     * This method will return all photos of a album, 
     * @param albumId, album id
     * @author created by Rashida on 21th September 2015
     */

    public List<PhotoDAO> getPhotos(String userId, String albumId) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class
                );
        Document selectDocument = new Document();
        selectDocument.put("userId", userId);
        selectDocument.put("albumId", albumId);
//        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("albumId").is(albumId).get();
        Document pQurey = new Document();
        pQurey.put("photoId", "$all");
        pQurey.put("image", "$all");
        MongoCursor<PhotoDAO> cursorStatusInfoList = mongoCollection.find(selectDocument).projection(pQurey).iterator();
        List<PhotoDAO> photoList = IteratorUtils.toList(cursorStatusInfoList);
        return photoList;

    }

    public List<PhotoDAO> getPhotoListByCategory(String albumId, String categoryId, int limit, int offset) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class
                );
        Document selectQuery = new Document();

        selectQuery.put(
                "albumId", albumId);
        selectQuery.put(
                "categoryId", categoryId);
        Document pQurey = new Document();

        pQurey.put(
                "photoId", "$all");
        pQurey.put(
                "image", "$all");
        MongoCursor<PhotoDAO> cursorStatusInfoList = mongoCollection.find(selectQuery).projection(pQurey).iterator();
        List<PhotoDAO> photoList = IteratorUtils.toList(cursorStatusInfoList);
        return photoList;

    }

    public PhotoDAO getPhotoByAlbumId(String albumId, String photoId) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class
                );
        Document neQuery = new Document();

        neQuery.put(
                "photoId", new Document("$ne", photoId));
        PhotoDAO photoInfo = mongoCollection.find(neQuery).first();
        return photoInfo;
    }


    /*
     * This method will return a photo , 
     * @param photoId, photo id
     * @author created by Rashida on 21th September 2015
     */
    public String getPhoto(String userId, String photoId) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class
                );
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
        PhotoDAO photoInfo = mongoCollection.find(selectQuery).first();
        JSONObject photoInfoJson = new JSONObject();

        try {
            String photoUserId = photoInfo.getUserId();
            UserModel userModel = new UserModel();
            photoInfoJson.put("userInfo", userModel.getUserInfo(userId));
            photoInfoJson.put("albumId", photoInfo.getAlbumId());
            photoInfoJson.put("photoId", photoInfo.getPhotoId());
            photoInfoJson.put("referenceId", photoInfo.getReferenceId());
            photoInfoJson.put("description", photoInfo.getDescription());
            photoInfoJson.put("categoryId", photoInfo.getCategoryId());
            photoInfoJson.put("image", photoInfo.getImage());
            photoInfoJson.put("createdOn", photoInfo.getCreatedOn());
            photoInfoJson.put("modifiedOn", photoInfo.getModifiedOn());
            if (photoInfo.getLike() != null) {
                int likeSize = photoInfo.getLike().size();
                if (likeSize > 0) {
                    photoInfoJson.put("likeCounter", likeSize);
                }
                int i = 0;
                while (likeSize > 0) {
                    String tempUserId = photoInfo.getLike().get(i).getUserInfo().getUserId();
                    if (tempUserId.equals(userId)) {
                        photoInfoJson.put("likeStatus", "1");
                    }
                    likeSize--;
                    i++;
                }
            }
            if (photoInfo.getComment() != null) {
                int commentSize = photoInfo.getComment().size();
                List<Comment> commentList = new ArrayList();
                if (commentSize > 0) {
                    Comment lastComment = photoInfo.getComment().get(0);
                    commentList.add(lastComment);
                }
                if (commentSize > 1) {
                    Comment secondlastComment = photoInfo.getComment().get(1);
                    commentList.add(secondlastComment);
                }
                if (commentSize > 2) {
                    photoInfoJson.put("commentCounter", commentSize - 2);
                }
                photoInfoJson.put("comment", commentList);
            }
            if (photoInfo.getShare() != null) {
                int shareSize = photoInfo.getShare().size();
                if (shareSize > 0) {
                    photoInfoJson.put("shareCounter", shareSize);
                }
            }
        } catch (NullPointerException npe) {
            LogWriter.getErrorLog().error(npe);
        }
        return photoInfoJson.toString();
    }

    public String getNextPhoto(String photoId) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class
                );
        Document sortQuery = new Document();

        sortQuery.put("photoId", 1);
        Document sQuery = new Document();

        sQuery.put("photoId", new Document("$gt", photoId));
        Document pQuery = new Document();

        pQuery.put(
                "photoId", "$all");
        FindIterable<PhotoDAO> photoList = mongoCollection.find(sQuery).projection(pQuery).sort(sortQuery).limit(1);
        List<PhotoDAO> photos = new ArrayList<>();
        for (PhotoDAO photo : photoList) {
            photos.add(photo);
            System.out.println(photo);
        }

        System.out.println(photos);
//        System.out.println(mongoCollection.find(sQuery).projection(pQuery).sort(sortQuery).limit(1).toString());
//        System.out.println(photoInfo);

        return "";
    }

    /*
     * This method will add list of photos , 
     * @param photoInfoList, photo list
     * @author created by Rashida on 21th September 2015
     */
    public ResultEvent addPhotos(String userId, String albumId, String photoInfoList) {
        try {
            MongoCollection<PhotoDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
            JSONArray photoArray = new JSONArray(photoInfoList);
            ArrayList<PhotoDAO> photoList = new ArrayList<PhotoDAO>();
            String defaultImg = "";
            String photoId = "";
            String referenceId = "";
            int newTotalImg = photoArray.length();
            if (photoArray != null) {
                PhotoDAO photoInfoObj1 = new PhotoDAOBuilder().build(photoArray.get(0).toString());
                defaultImg = photoInfoObj1.getImage();
                photoId = photoInfoObj1.getPhotoId();
                referenceId = photoInfoObj1.getReferenceId();
                AlbumDAO albumInfo = new AlbumDAO();
                albumInfo.setDefaultImg(defaultImg);
                albumInfo.setTotalImg(newTotalImg);
                albumInfo.setPhotoId(photoId);
                albumInfo.setReferenceId(referenceId);
                AlbumDAO oldAlbumInfo = getAlbumInfo(userId, albumId);
                Boolean refernceId = false;
                Boolean statusUpdate = false;
                if (oldAlbumInfo != null) {
                    if (oldAlbumInfo.getAlbumId().equals(albumId)) {
                        if (oldAlbumInfo.getPhotoId() != null) {
                            statusUpdate = true;
                            int totalImg = newTotalImg + oldAlbumInfo.getTotalImg();
                            if (albumId.equals(PropertyProvider.get("TIMELINE_PHOTOS_ALBUM_ID"))) {
                                refernceId = true;
                            } else if (albumId.equals(PropertyProvider.get("PROFILE_PHOTOS_ALBUM_ID"))) {
                                refernceId = true;
                            } else if (albumId.equals(PropertyProvider.get("COVER_PHOTOS_ALBUM_TITLE"))) {
                                refernceId = true;
                            }
                            editAlbumTotalImg(userId, albumId, totalImg);
                        } else {
                            editAlbum(userId, albumId, albumInfo.toString());
                        }
                    }
                } else {
                    if (albumId.equals(PropertyProvider.get("TIMELINE_PHOTOS_ALBUM_ID"))) {
                        albumInfo.setTitle(PropertyProvider.get("TIMELINE_PHOTOS_ALBUM_TITLE"));
                    } else if (albumId.equals(PropertyProvider.get("PROFILE_PHOTOS_ALBUM_ID"))) {
                        albumInfo.setTitle(PropertyProvider.get("PROFILE_PHOTOS_ALBUM_TITLE"));
                    } else if (albumId.equals(PropertyProvider.get("COVER_PHOTOS_ALBUM_ID"))) {
                        albumInfo.setTitle(PropertyProvider.get("COVER_PHOTOS_ALBUM_TITLE"));
                    }
                    albumInfo.setUserId(userId);
                    albumInfo.setAlbumId(albumId);
                    createAlbum(albumInfo.toString());

                }
                List<String> images = new ArrayList<>();
                for (int i = 0; i < newTotalImg; i++) {
                    PhotoDAO photoInfoObj = new PhotoDAOBuilder().build(photoArray.get(i).toString());
                    photoInfoObj.setUserId(userId);
                    photoInfoObj.setCreatedOn(utility.getCurrentTime());
                    photoInfoObj.setModifiedOn(utility.getCurrentTime());
                    photoInfoObj.setReferenceId(referenceId);
                    photoList.add(photoInfoObj);
                    if (refernceId == false && statusUpdate != false) {
                        images.add(photoInfoObj.getImage());
                    }
                }
                if (refernceId == false && statusUpdate != false) {
                    referenceId = oldAlbumInfo.getReferenceId();
                    StatusModel statusModel = new StatusModel();
                    ResultEvent rEvent = statusModel.updateStatusPhoto(referenceId, images.toString());
                    if (rEvent.getResponseCode().equals(PropertyProvider.get("SUCCESSFUL_OPERATION"))) {
                        this.getResultEvent().setResult(referenceId);
                    }
                }

                mongoCollection.insertMany(photoList);
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            } else {
                this.getResultEvent().setResponseCode(PropertyProvider.get("BadRequest"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /*
     * This method will edit a photo , 
     * @param photoId, photo id
     * @param photoInfo, photo info
     * @author created by Rashida on 21th September 2015
     */
    public String
            editPhoto(String photoId, String photoInfo) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class
                );
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
        PhotoDAO pInfo = new PhotoDAOBuilder().build(photoInfo);
        Document modifiedInfo = new Document();

        modifiedInfo.put(
                "image", pInfo.getImage());
        modifiedInfo.put(
                "description", pInfo.getDescription());
        mongoCollection.findOneAndUpdate(selectQuery,
                new Document("$set", modifiedInfo));
        resultEvent.setResponseCode(
                "100157");
        return resultEvent.toString();
    }
    /*
     * This method will delete a photo , 
     * @param photoId, photo id
     * @author created by Rashida on 21th September 2015
     */

    public String deletePhoto(String userId, String albumId, String photoId) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class
                );
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
        AlbumDAO albumInfo = getAlbum(albumId);
        if (albumInfo
                != null) {
            int totalImg = albumInfo.getTotalImg();
            if (albumInfo.getPhotoId().equals(photoId)) {
                PhotoDAO nextPhotoInfo = getPhotoByAlbumId(albumId, photoId);
                if (nextPhotoInfo != null) {
                    AlbumDAO userAlbum = new AlbumDAOBuilder()
                            .setPhotoId(nextPhotoInfo.getPhotoId())
                            .setDefaultImg(nextPhotoInfo.getImage())
                            .setTotalImg(totalImg - 1)
                            .build();
                    editAlbum(userId, albumId, userAlbum.toString());
                } else {
                    AlbumDAO userAlbum = new AlbumDAOBuilder()
                            .build();
                    editAlbum(userId, albumId, userAlbum.toString());
                }
            } else {
                totalImg = totalImg - 1;
                editAlbumTotalImg(userId, albumId, totalImg);
            }

        }

        mongoCollection.findOneAndDelete(selectQuery);

        resultEvent.setResponseCode(
                "100157");
        return resultEvent.toString();
    }
    /*
     * This method will add a photo like , 
     * @param photoId, photo id
     * @param likeInfo, like user information
     * @author created by Rashida on 21th September 2015
     */

    public ResultEvent addPhotoLike(String photoId, String likeInfo) {
        try {
            MongoCollection<PhotoDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class
                    );
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
            Like photoLikeInfo = Like.getLikeInfo(likeInfo);
            if (photoLikeInfo != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("like", JSON.parse(photoLikeInfo.toString()))));
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public ResultEvent addMPhotoLike(String userId, String photoId, String likeInfo) {
        try {
            MongoCollection<PhotoDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class
                    );
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
            Like photoLikeInfo = Like.getLikeInfo(likeInfo);
            if (photoLikeInfo != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$set", new Document("modifiedOn", utility.getCurrentTime())));
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("like", JSON.parse(photoLikeInfo.toString()))));
                UserInfo userInfo = photoLikeInfo.getUserInfo();
                notificationModel.addGeneralNotificationPhotoLike(userId, photoId, userInfo.toString());
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }
    /*
     * This method will add a photo like , 
     * @param referenceId, reference id
     * @param likeInfo, like user information
     * @author created by Rashida on 21th September 2015
     */

    public ResultEvent addPhotoLikeByReferenceId(String referenceId, String likeInfo) {
        try {
            MongoCollection<PhotoDAO> mongoCollection = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
            String attrReferenceId = PropertyProvider.get("REFERENCE_ID");
            String attrLike = PropertyProvider.get("LIKE");
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start(attrReferenceId).is(referenceId).get();
            Like photoLikeInfo = Like.getLikeInfo(likeInfo);
            if (photoLikeInfo != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document(attrLike, JSON.parse(photoLikeInfo.toString()))));
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    public String getPhotoLikeList(String photoId) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), AlbumDAO.class
                );
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
        Document pQuery = new Document();

        pQuery.put(
                "like", "$all");
        AlbumDAO albumLikeList = mongoCollection.find(selectQuery).projection(pQuery).first();

        return albumLikeList.toString();
    }
    /*
     * This method will delete a photo like , 
     * @param photoId, photo id
     * @param likeId, like id
     * @author created by Rashida on 21th September 2015
     */

    public String deletePhotoLike(String photoId, String likeId) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class
                );
        Document selectQuery = new Document();

        selectQuery.put(
                "photoId", photoId);
        selectQuery.put(
                "like.id", likeId);
        Document projectQuery = new Document();

        projectQuery.put(
                "like.$", "$all");
        System.out.println(mongoCollection.find(selectQuery).projection(projectQuery).first());
        PhotoDAO photoLike = mongoCollection.find(selectQuery).projection(projectQuery).first();

        System.out.println(photoLike.getLike());

//        System.out.println(new Document("$pull", new Document("$like.$",null)));
        mongoCollection.findOneAndUpdate(selectQuery,
                new Document("$pull", new ArrayList<>()));
        resultEvent.setResponseCode(
                "100157");
        return resultEvent.toString();
    }

    /*
     * This method will add a photo comment , 
     * @param photoId, photo id
     * @param commentInfo, comment info
     * @author created by Rashida on 21th September 2015
     */
    public ResultEvent addPhotoComment(String photoId, String commentInfo) {
        try {
            MongoCollection<PhotoDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
            BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
            Comment photoCommentInfo = Comment.getCommentInfo(commentInfo);
            if (photoCommentInfo != null) {
                mongoCollection.findOneAndUpdate(selectQuery, new Document("$push", new Document("comment", JSON.parse(photoCommentInfo.toString()))));
                this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
            }
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;
    }

    /*
     * This method will get all comments of a album , 
     * @param albumId, album id
     * @author created by Rashida on 1st Octobar 2015
     */
    public String getPhotoComments(String photoId) {
        MongoCollection<AlbumDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), AlbumDAO.class
                );
        BasicDBObject selectQuery = (BasicDBObject) QueryBuilder.start("photoId").is(photoId).get();
        Document pQuery = new Document();

        pQuery.put(
                "comment", "$all");
        AlbumDAO albumCommentList = mongoCollection.find(selectQuery).projection(pQuery).first();

        return albumCommentList.toString();
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
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class
                );
        Document selectQuery = new Document();

        selectQuery.put(
                "photoId", photoId);
        selectQuery.put(
                "comment.id", commentId);
        mongoCollection.findOneAndUpdate(selectQuery,
                new Document("$set", new Document("comment.$.description", commentInfo)));
        resultEvent.setResponseCode(
                "100157");
        return resultEvent.toString();
    }

    public String deletePhotoComment(String photoId, String commentId) {
        MongoCollection<PhotoDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class
                );
        return resultEvent.toString();
    }

    public ResultEvent getTimelinePhotos(String userId) {
        try {
            MongoCollection<PhotoDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
            Document selectionDocument = new Document();
            selectionDocument.put("userId", userId);
            selectionDocument.put("albumId", PropertyProvider.get("TIMELINE_PHOTOS_ALBUM_ID"));
            MongoCursor<PhotoDAO> photoList = mongoCollection.find(selectionDocument).iterator();
            List<PhotoDAO> photoInfoList = IteratorUtils.toList(photoList);
            this.getResultEvent().setResult(photoInfoList);
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return this.resultEvent;

    }

    public JSONObject getSliderPhotos(String userId, String referenceId) {
        JSONObject userStatusInfo = new JSONObject();
        try {
            MongoCollection<PhotoDAO> mongoCollection
                    = DBConnection.getInstance().getConnection().getCollection(Collections.ALBUMPHOTOS.toString(), PhotoDAO.class);
            Document selectionDocument = new Document();
            selectionDocument.put("referenceId", referenceId);
            MongoCursor<PhotoDAO> photoList = mongoCollection.find(selectionDocument).iterator();
            List<JSONObject> photoInfoList = getPhotoInfo(userId, photoList);
            userStatusInfo.put("statusInfoList", photoInfoList);
            userStatusInfo.put("userCurrentTime", utility.getCurrentTime());
            this.getResultEvent().setResponseCode(PropertyProvider.get("SUCCESSFUL_OPERATION"));
        } catch (Exception ex) {
            this.getResultEvent().setResponseCode(PropertyProvider.get("ERROR_EXCEPTION"));
        }
        return userStatusInfo;

    }

    /**
     * *
     * this method will return all status needed information
     *
     * @param statusList, status List
     * @author created by Rashida on 9th Nov
     */
    public List<JSONObject> getPhotoInfo(String userId, MongoCursor<PhotoDAO> photoList) {

        int commentLimit = Integer.parseInt(PropertyProvider.get("COMMENT_LIMIT"));
        List<JSONObject> photoInfoList = new ArrayList<JSONObject>();

        while (photoList.hasNext()) {
            JSONObject photoJson = new JSONObject();
            PhotoDAO photoInfo = (PhotoDAO) photoList.next();
            photoJson.put("photoId", photoInfo.getPhotoId());
            photoJson.put("referenceId", photoInfo.getReferenceId());
            photoJson.put("userId", photoInfo.getUserId());
            photoJson.put("userInfo", photoInfo.getUserId());
            photoJson.put("description", photoInfo.getDescription());
            photoJson.put("createdOn", photoInfo.getCreatedOn());
            photoJson.put("image", photoInfo.getImage());
            if (photoInfo.getLike() != null) {
                int likeSize = photoInfo.getLike().size();
                if (likeSize > 0) {
                    photoJson.put("likeCounter", likeSize);
                }
                int i = 0;
                while (likeSize > 0) {
                    String tempUserId = photoInfo.getLike().get(i).getUserInfo().getUserId();
                    if (tempUserId.equals(userId)) {
                        photoJson.put("likeStatus", PropertyProvider.get("YourLikeStatus"));
                    }
                    likeSize--;
                    i++;
                }

            }
            if (photoInfo.getComment() != null) {
                List<JSONObject> commentList = new ArrayList<JSONObject>();
                int commentSize = photoInfo.getComment().size();
                if (commentSize > 0) {
                    int commentLimitIn = 0;
//                    List<Comment> commentList = new ArrayList();
                    for (int j = commentSize; j > 0; j--) {
                        JSONObject commentJson = new JSONObject();
                        Comment comment = photoInfo.getComment().get(j - 1);
                        commentJson.put("commentId", comment.getCommentId());
                        commentJson.put("description", comment.getDescription());
                        commentJson.put("userInfo", comment.getUserInfo());
                        commentJson.put("userGenderId", userModel.getUserGenderInfo(comment.getUserInfo().getUserId()));
                        if (comment.getLike() != null) {
                            int commentLikeSize = comment.getLike().size();
                            if (commentLikeSize > 0) {
                                commentJson.put("commentlikeCounter", commentLikeSize);
                            }
                            int k = 0;
                            while (commentLikeSize > 0) {
                                String tempUserId = comment.getLike().get(k).getUserInfo().getUserId();
                                if (tempUserId.equals(userId)) {
                                    commentJson.put("CommentlikeStatus", PropertyProvider.get("YourLikeStatus"));
                                }
                                commentLikeSize--;
                                k++;
                            }
                        }
                        commentList.add(commentJson);
//                        commentList.add(comment);
                        commentLimitIn = commentLimitIn + 1;
                        if (commentLimitIn != commentLimit) {
                            continue;
                        } else {
                            break;
                        }
                    }
                    if (commentSize > commentLimit) {
                        photoJson.put("commentCounter", commentSize - commentLimit);
                    }
                    photoJson.put("commentList", commentList);
                }
            }
            if (photoInfo.getShare() != null) {
                int shareSize = photoInfo.getShare().size();
                if (shareSize > 0) {
                    photoJson.put("shareCounter", shareSize);
                }
            }

            photoInfoList.add(photoJson);
        }
        return photoInfoList;
    }

}
