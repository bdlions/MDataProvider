/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.services;

import com.shampan.db.collections.PhotoDAO;
import com.shampan.model.PhotoModel;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Sampan-IT
 */
public class PhotoService {

    private static PhotoModel photoObject = new PhotoModel();

    public static String getCategories() {
        JSONObject categories = new JSONObject();
        categories.put("categoryList", photoObject.getCategories());
        return categories.toString();
    }

    public static String getCategoriesAndAlbums(String userId) {
        JSONObject list = new JSONObject();
        list.put("categoryList", photoObject.getCategories());
        list.put("albumList", photoObject.getAlbumList(userId));
        return list.toString();
    }

    public static String getAlbums(String userId) {
        JSONObject albums = new JSONObject();
        albums.put("albumList", photoObject.getAlbums(userId));
        return albums.toString();
    }

    public static String getAlbum(String albumId) {
        String response = photoObject.getAlbum(albumId);
        return response;
    }

    public static String createAlbum(String albumInfo) {
        String response = photoObject.createAlbum(albumInfo);
        return response;
    }

    public static String editAlbum(String albumId, String albumInfo) {
        String response = photoObject.editAlbum(albumId, albumInfo);
        return response;
    }

    public static String deleteAlbum(String albumId) {
        String response = photoObject.deleteAlbum(albumId);
        return response;
    }

    public static String addAlbumLike(String albumId, String likeInfo) {
        String response = photoObject.addAlbumLike(albumId, likeInfo);
        return response;
    }
    public static String getAlbumLikeList(String albumId) {
        String response = photoObject.getAlbumLikeList(albumId);
        return response;
    }

    public static String addAlbumComment(String albumId, String commentInfo) {
        String response = photoObject.addAlbumComment(albumId, commentInfo);
        return response;
    }

    public static String editAlbumComment(String albumId, String commentId, String commentInfo) {
        String response = photoObject.editAlbumComment(albumId, commentId, commentInfo);
        return response;
    }

    public static String deleteAlbumComment(String albumId, String commentId) {
        String response = photoObject.deleteAlbumComment(albumId, commentId);
        return response;
    }

    public static String getUserPhotos(String userId, int offset, int limit) {
        JSONObject photos = new JSONObject();
        photos.put("photoList", photoObject.getUserPhotos(userId, offset, limit));
        return photos.toString();
    }

    public static String getPhotos(String albumId) {
        JSONObject photos = new JSONObject();
        photos.put("albumInfo", photoObject.getAlbum(albumId));
        photos.put("photoList", photoObject.getPhotos(albumId));
        return photos.toString();
    }

    public static String getPhoto(String photoId) {
        String response = photoObject.getPhoto(photoId);
        return response;
    }

    public static String addPhotos(String photoInfo) {
        String response = photoObject.addPhotos(photoInfo);
        return response;
    }

    public static String editPhoto(String photoId, String image) {
        String response = photoObject.editPhoto(photoId, image);
        return response;
    }

    public static String deletePhoto(String photoId) {
        String response = photoObject.deletePhoto(photoId);
        return response;
    }

    public static String addPhotoLike(String photoId, String likeInfo) {
        String response = photoObject.addPhotoLike(photoId, likeInfo);
        return response;
    }

    public static String deletePhotoLike(String photoId, String likeId) {
        String response = photoObject.deletePhotoLike(photoId, likeId);
        return response;
    }

    public static String addPhotoComment(String photoId, String commentInfo) {
        String response = photoObject.addPhotoComment(photoId, commentInfo);
        return response;
    }

    public static String editPhotoComment(String photoId, String commentId, String commentInfo) {
        String response = photoObject.editPhotoComment(photoId, commentId, commentInfo);
        return response;
    }

    public static String deletePhotoComment(String photoId, String commentId) {
        String response = photoObject.deletePhotoComment(photoId, commentId);
        return response;
    }

}
