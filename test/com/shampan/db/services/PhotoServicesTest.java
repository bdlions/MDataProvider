package com.shampan.db.services;

import com.mongodb.util.JSON;
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
import com.shampan.model.PhotoModel;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

/**
 *
 * @author Sampan-IT
 */
public class PhotoServicesTest {

    PhotoModel photoObject = new PhotoModel();
    String userId = "eQeOwhE7hrUkCyP";
    String albumId = "qAuUBxk4ZqjDiAS";
    String photoId = "65sKxJtOFfExN65";
    String categoryId = "eQeOwhE7hrUkCyP";

//    @Test
    public void addCategory() {
        PhotoCategoryDAO category = new PhotoCategoryBuilder()
                .setCategoryId("3")
                .setTitle("Art")
                .build();
        photoObject.addCategory(category.toString());

    }

//  @Test
    public void getCategories() {
        System.out.println(photoObject.getCategories());

    }

    //    @Test
    public void getAlbums() {
        System.out.println(photoObject.getAlbums(userId));

    }

//    @Test
    public void getAlbum() {
        System.out.println(photoObject.getAlbum(userId,albumId));

    }
//  @Test

    public void createAlbum() {
        UserInfo userInfo = new UserInfo();
        userInfo.setFristName("Rashida");
        userInfo.setLastName("Sultana");
        userInfo.setUserId(userId);
        AlbumDAO userAlbum = new AlbumDAOBuilder()
                .setUserId(userId)
                .setAlbumId(albumId)
                .setTitle("Flowers Album")
                .setDescription("My fevorate garden at font my home")
                .setPhotoId(photoId)
                .setDefaultImg("Cheri.jpg")
                .setTotalImg(6)
                .setUserInfo(userInfo)
                .build();
        System.out.println(photoObject.createAlbum(userAlbum.toString()));

    }

//    @Test
    public void editAlbum() {
        UserInfo userInfo = new UserInfo();
        userInfo.setFristName("Rashida");
        userInfo.setLastName("Sultana");
        userInfo.setUserId(userId);
        AlbumDAO userAlbum = new AlbumDAOBuilder()
                .setUserId(userId)
                .setAlbumId("2")
                .setTitle("Flowers Albumhgfhgfj")
                .setDescription("My jyhjuyk")
                .setDefaultImg("Kamini.jpg")
                .setTotalImg(6)
                .setUserInfo(userInfo)
                .build();
        System.out.println(photoObject.editAlbum(albumId, userAlbum.toString()));

    }
    

//    @Test
    public void editAlbumTotalImg() {
        int totalImgInfo = 100 ;
        System.out.println(photoObject.editAlbumTotalImg(albumId,totalImgInfo));

    }
//    @Test
    public void deleteAlbum() {
        System.out.println(photoObject.deleteAlbum(albumId));

    }

//    @Test
    public void addAlbumLike() {
        UserInfo refUserInfo = new UserInfo();
        refUserInfo.setFristName("Alamgir");
        refUserInfo.setLastName("Kabir");
        refUserInfo.setUserId("2");
        Like likeuserInfo = new Like();
        likeuserInfo.setUserInfo(refUserInfo);
        System.out.println(photoObject.addAlbumLike(albumId, likeuserInfo.toString()));

    }
//    @Test
    public void getAlbumLikeList() {
        System.out.println(photoObject.getAlbumLikeList(albumId));

    }

//    @Test
    public void addAlbumComment() {
        UserInfo refUserInfo = new UserInfo();
        refUserInfo.setFristName("Alamgir");
        refUserInfo.setLastName("Kabir");
        refUserInfo.setUserId(userId);
        Comment albumCommentInfo = new Comment();
        albumCommentInfo.setId("1");
        albumCommentInfo.setUserInfo(refUserInfo);
        albumCommentInfo.setDescription("I like your track !");
        System.out.println(photoObject.addAlbumComment(albumId, albumCommentInfo.toString()));

    }

//    @Test
    public void editAlbumComment() {
        String commentId = "1";
        System.out.println(photoObject.editAlbumComment(albumId, commentId, "I dislike your track !"));

    }
//
//    @Test
    public void addPhotos() {
        PhotoDAO userPhoto1 = new PhotoDAOBuilder()
                .setPhotoId("3")
                .setAlbumId(albumId)
                .setCategoryId(userId)
                .setImage("hasnehena.jpg")
                .setDescription("i dislike it very much")
                .build();
        PhotoDAO userPhoto2 = new PhotoDAOBuilder()
                .setPhotoId("4")
                .setAlbumId(albumId)
                .setCategoryId(userId)
                .setImage("kamini.jpg")
                .setDescription("drygtytyutu")
                .build();
        List<PhotoDAO> photoList = new ArrayList<PhotoDAO>();
        photoList.add(userPhoto1);
        photoList.add(userPhoto2);
        photoObject.addPhotos("zVrKQGJaL24c1mW",photoList.toString());

    }

//    @Test
    public void editPhoto() {
        PhotoDAO userPhoto2 = new PhotoDAOBuilder()
                .setImage("hasnehena.jpg")
                .setDescription("edited description")
                .build();
        System.out.println(photoObject.editPhoto(photoId, userPhoto2.toString()));

    }

//    @Test
    public void getPhotos() {
        System.out.println(photoObject.getPhotos(albumId));

    }
//    @Test
    public void getPhoto() {
        System.out.println(photoObject.getPhoto(userId,photoId));

    }
    
//    @Test
    public void getNextPhoto() {
        System.out.println(photoObject.getNextPhoto("2"));

    }

//    @Test
    public void deletePhoto() {
        System.out.println(photoObject.deletePhoto(albumId,photoId));

    }

//    @Test
    public void getUserPhotos() {
        String albumId = "zVrKQGJaL24c1mW";
        int limit = 5;
        int offset = 0;
        JSONObject photos = new JSONObject();
        photos.put("photoList", photoObject.getUserPhotos(albumId, offset, limit));
        System.out.println(photos);

    }
    
//    @Test
    public void getPhotoListByCategory() {
        int limit = 5;
        int offset = 0;
        JSONObject photos = new JSONObject();
//        photos.put("photoList", );
        System.out.println(photoObject.getPhotoListByCategory(albumId,categoryId, offset, limit));

    }

//    @Test
    public void addPhotoLike() {
        UserInfo refUserInfo = new UserInfo();
        refUserInfo.setFristName("Alamgir");
        refUserInfo.setLastName("Kabir");
        refUserInfo.setUserId(userId);
        Like likeuserInfo = new Like();
        likeuserInfo.setId("2");
        likeuserInfo.setUserInfo(refUserInfo);
        System.out.println(photoObject.addPhotoLike(photoId, likeuserInfo.toString()));

    }

//    @Test
    public void addPhotoComment() {
        UserInfo refUserInfo = new UserInfo();
        refUserInfo.setFristName("Alamgir");
        refUserInfo.setLastName("Kabir");
        refUserInfo.setUserId(userId);
        Comment photoCommentInfo = new Comment();
        photoCommentInfo.setId("1");
        photoCommentInfo.setUserInfo(refUserInfo);
        photoCommentInfo.setDescription("I like your track !");
        System.out.println(photoObject.addPhotoComment(photoId, photoCommentInfo.toString()));

    }

//    @Test
    public void editPhotoComment() {
        String commentId = "2";
        System.out.println(photoObject.editPhotoComment(photoId, commentId, "I dislike your track !"));

    }

//    @Test
    public void deletePhotoLike() {
        String photoId = "1";
        String likeId = "1";
        System.out.println(photoObject.deletePhotoLike(photoId, likeId));

    }

}
