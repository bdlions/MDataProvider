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

    public static String createAlbum(String albumInfo) {
        String response = photoObject.createAlbum(albumInfo);
        return response;
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

    public static String addAlbumLike(String albumId, String likeInfo) {
        String response = photoObject.addAlbumLike(albumId, likeInfo);
        return response;
    }

    public static String addAlbumComment(String albumId, String commentInfo) {
        String response = photoObject.addAlbumComment(albumId, commentInfo);
        return response;
    }

    public static String addPhoto(String photoInfo) {
        String response = photoObject.addPhoto(photoInfo);
        return response;
    }

    public static String getPhoto(String photoId) {
        String response = photoObject.getPhoto(photoId);
        return response;
    }

    public static String updatePhoto(String photoId, String image) {
        String response = photoObject.updatePhoto(photoId, image);
        return response;
    }

    public static String deletePhoto(String photoId) {
        String response = photoObject.deletePhoto(photoId);
        return response;
    }

    public static String getPhotos(String albumId) {
        JSONObject photos = new JSONObject();
        photos.put("categoryList", photoObject.getPhotos(albumId));
        return photos.toString();
    }

}
