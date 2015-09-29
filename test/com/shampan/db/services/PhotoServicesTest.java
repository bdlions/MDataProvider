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
    String albumId = "2";
    String photoId = "3";

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
        System.out.println(photoObject.getAlbum(albumId));

    }
//  @Test

    public void createAlbum() {
        UserInfo userInfo = new UserInfo();
        userInfo.setFristName("Rashida");
        userInfo.setLastName("Sultana");
        userInfo.setUserId(userId);
        AlbumDAO userAlbum = new AlbumDAOBuilder()
                .setUserId(userId)
                .setAlbumId("2")
                .setTitle("Flowers Album")
                .setDescription("My fevorate garden at font my home")
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
        System.out.println(photoObject.editAlbum("s1MsYK50VELd34h", userAlbum.toString()));

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
        refUserInfo.setUserId(userId);
        Like likeuserInfo = new Like();
        likeuserInfo.setUserInfo(refUserInfo);
        System.out.println(photoObject.addAlbumLike(albumId, likeuserInfo.toString()));

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

//    @Test
    public void addPhotos() {
        PhotoDAO userPhoto1 = new PhotoDAOBuilder()
                .setPhotoId("1")
                .setAlbumId(albumId)
                .setCategoryId(userId)
                .setImage("hasnehena.jpg")
                .setDescription("i dislike it very much")
                .build();
        PhotoDAO userPhoto2 = new PhotoDAOBuilder()
                .setPhotoId("2")
                .setAlbumId(albumId)
                .setCategoryId(userId)
                .setImage("kamini.jpg")
                .setDescription("drygtytyutu")
                .build();
        List<PhotoDAO> photoList = new ArrayList<PhotoDAO>();
        photoList.add(userPhoto1);
        photoList.add(userPhoto2);
        System.out.println(photoObject.addPhotos(photoList.toString()));

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
    public void deletePhoto() {
        System.out.println(photoObject.deletePhoto(photoId));

    }

//    @Test
    public void getUserPhotos() {
        String albumId = "2";
        int limit = 5;
        int offset = 0;
        JSONObject photos = new JSONObject();
        photos.put("photoList", photoObject.getUserPhotos(userId, offset, limit));
        System.out.println(photos);

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
