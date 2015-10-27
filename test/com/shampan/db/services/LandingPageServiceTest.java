package com.shampan.db.services;

import com.mongodb.client.MongoCollection;
import com.shampan.db.Collections;
import com.shampan.db.DBConnection;
import com.shampan.db.collections.CountriesDAO;
import com.shampan.db.collections.PhotoCategoryDAO;
import com.shampan.db.collections.ReligionsDAO;
import com.shampan.db.collections.UserDAO;
import com.shampan.db.collections.VideoCategoryDAO;
import com.shampan.db.collections.builder.ReligionsDAOBuilder;
import com.shampan.db.collections.builder.CountriesDAOBuilder;
import com.shampan.db.collections.builder.PhotoCategoryBuilder;
import com.shampan.db.collections.builder.UserDAOBuilder;
import com.shampan.db.collections.builder.VideoCategoryDAOBuilder;
import com.shampan.db.collections.fragment.common.UserInfo;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Sampan-IT
 */
public class LandingPageServiceTest {

//    public static void main(String[] args) {
//        LandingPageServiceTest.storeCountries();
//    }
    @Test
    public void storeReligions() {
        DBConnection.getInstance().getConnection();
        MongoCollection<ReligionsDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.RELIGIONS.toString(), ReligionsDAO.class);
        
        ReligionsDAO religionDAO1 = new ReligionsDAOBuilder()
                .setReligionId("1")
                .setTitle("Islam")
                .setOrder("1")
                .build();
        
        ReligionsDAO religionDAO2 = new ReligionsDAOBuilder()
                .setReligionId("2")
                .setTitle("Hindu")
                .setOrder("2")
                .build();
        
        ReligionsDAO religionDAO3 = new ReligionsDAOBuilder()
                .setReligionId("3")
                .setTitle("Christianity")
                .setOrder("3")
                .build();
        
        ReligionsDAO religionDAO4 = new ReligionsDAOBuilder()
                .setReligionId("4")
                .setTitle("Buddhism")
                .setOrder("4")
                .build();
        List<ReligionsDAO> religions = new ArrayList<>();
        religions.add(religionDAO1);
        religions.add(religionDAO2);
        religions.add(religionDAO3);
        religions.add(religionDAO4);
        mongoCollection.insertMany(religions);
    }
    
    @Test
    
    public void storeCountries() {
        DBConnection.getInstance().getConnection();
        MongoCollection<CountriesDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.COUNTRIES.toString(), CountriesDAO.class);
        
        CountriesDAO countryDAO1 = new CountriesDAOBuilder()
                .setCode("BD")
                .setTitle("Bangladesh")
                .setOrder("1")
                .build();
        CountriesDAO countryDAO2 = new CountriesDAOBuilder()
                .setCode("AU")
                .setTitle("Australia")
                .setOrder("2")
                .build();
        CountriesDAO countryDAO3 = new CountriesDAOBuilder()
                .setCode("ED")
                .setTitle("England")
                .setOrder("3")
                .build();
        List<CountriesDAO> countries = new ArrayList<>();
        countries.add(countryDAO1);
        countries.add(countryDAO2);
        countries.add(countryDAO3);
        mongoCollection.insertMany(countries);
        
    }
    
    @Test
    public void photoCategories() {
        MongoCollection<PhotoCategoryDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.PHOTOCATEGORIES.toString(), PhotoCategoryDAO.class);
        PhotoCategoryDAO category1 = new PhotoCategoryBuilder()
                .setCategoryId("1")
                .setTitle("Flowers")
                .build();
        PhotoCategoryDAO category2 = new PhotoCategoryBuilder()
                .setCategoryId("3")
                .setTitle("Robot")
                .build();
        PhotoCategoryDAO category3 = new PhotoCategoryBuilder()
                .setCategoryId("3")
                .setTitle("Art")
                .build();
        
        List<PhotoCategoryDAO> photoCategories = new ArrayList<>();
        photoCategories.add(category1);
        photoCategories.add(category2);
        photoCategories.add(category3);
        mongoCollection.insertMany(photoCategories);
        
    }
    
    @Test
    public void videoCategories() {
        MongoCollection<VideoCategoryDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.VIDEOCATEGORIES.toString(), VideoCategoryDAO.class);
        
        VideoCategoryDAO vedioCategory1 = new VideoCategoryDAOBuilder()
                .setCategoryId("1")
                .setTitle("Science")
                .build();
        VideoCategoryDAO vedioCategory2 = new VideoCategoryDAOBuilder()
                .setCategoryId("2")
                .setTitle("Al-Qurener Alu")
                .build();
        VideoCategoryDAO vedioCategory3 = new VideoCategoryDAOBuilder()
                .setCategoryId("3")
                .setTitle("Flower")
                .build();
        
        List<VideoCategoryDAO> videoCategories = new ArrayList<>();
        videoCategories.add(vedioCategory1);
        videoCategories.add(vedioCategory2);
        videoCategories.add(vedioCategory3);
        mongoCollection.insertMany(videoCategories);
    }
    
    @Test
    public void addUser() {
        MongoCollection<UserDAO> mongoCollection
                = DBConnection.getInstance().getConnection().getCollection(Collections.USERS.toString(), UserDAO.class);
        UserDAO userInfo1 = new UserDAOBuilder()
                .setFirstName("Nazmul")
                .setLastName("Hasan")
                .setUserId("1")
                .build();
        UserDAO userInfo2 = new UserDAOBuilder()
                .setFirstName("Nazrul")
                .setLastName("Islam")
                .setUserId("2")
                .build();
        UserDAO userInfo3 = new UserDAOBuilder()
                .setFirstName("Alamgir")
                .setLastName("Kabir")
                .setUserId("3")
                .build();
        UserDAO userInfo4 = new UserDAOBuilder()
                .setFirstName("Rashida")
                .setLastName("Sultana")
                .setUserId("4")
                .build();
        UserDAO userInfo5 = new UserDAOBuilder()
                .setFirstName("Keya")
                .setLastName("Moni")
                .setUserId("5")
                .build();
        UserDAO userInfo6 = new UserDAOBuilder()
                .setFirstName("Shemin")
                .setLastName("Haque")
                .setUserId("6")
                .build();
        List<UserDAO> users = new ArrayList<>();
        users.add(userInfo1);
        users.add(userInfo2);
        users.add(userInfo3);
        users.add(userInfo4);
        users.add(userInfo5);
        users.add(userInfo6);
        mongoCollection.insertMany(users);
    }
    
}
