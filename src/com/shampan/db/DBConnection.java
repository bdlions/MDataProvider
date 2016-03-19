/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shampan.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.shampan.db.codec.AlbumCodec;
import com.shampan.db.codec.BasicProfileCodec;
import com.shampan.db.codec.CharacterCodec;
import com.shampan.db.codec.CountriesCodec;
import com.shampan.db.codec.LoginAttemptCodec;
import com.shampan.db.codec.MessageCodec;
import com.shampan.db.codec.MessageDetailsCodec;
import com.shampan.db.codec.NotificationCodec;
import com.shampan.db.codec.PageCategoryCodec;
import com.shampan.db.codec.PageCodec;
import com.shampan.db.codec.PageMemeberCodec;
import com.shampan.db.codec.PageSubCategoryCodec;
import com.shampan.db.codec.PhotoCategoryCodec;
import com.shampan.db.codec.PhotoCodec;
import com.shampan.db.codec.RelationsCodec;
import com.shampan.db.codec.ReligionsCodec;
import com.shampan.db.codec.SocialNetworkingCodec;
import com.shampan.db.codec.StatusCodec;
import com.shampan.db.codec.UserCodec;
import com.shampan.db.codec.VideoCategoryCodec;
import com.shampan.db.codec.VideoCodec;
import com.shampan.util.PropertyProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

/**
 *
 * @author Alamgir Kabir
 */
public class DBConnection {

    private static DBConnection instance = null;
    private static MongoDatabase connection = null;

    private String dbUserName;
    private String dbPassword;
    private String dbName;
    private String dbHost;
    private int dbPort;

    private DBConnection() {
        //singleton class for the database connection
        if (instance == null) {
            /**
             * Reading form the property file
             */
            PropertyProvider.add("database");

            /**
             * All setting up the host, port, username and password
             */
            setDbHost(PropertyProvider.get("host"));
            setDbUserName(PropertyProvider.get("username"));
            setDbPassword(PropertyProvider.get("password"));
            setDbPort(Integer.parseInt(PropertyProvider.get("port")));
            setDbName(PropertyProvider.get("dbname"));

            UserCodec userCodec = new UserCodec();
            BasicProfileCodec basicProfileCodec = new BasicProfileCodec();
            CountriesCodec countriesCodec = new CountriesCodec();
            ReligionsCodec religionCodec = new ReligionsCodec();
            RelationsCodec relationsCodec = new RelationsCodec();
            AlbumCodec albumCodec = new AlbumCodec();
            PhotoCodec photoCodec = new PhotoCodec();
            StatusCodec statusCodec = new StatusCodec();
            VideoCategoryCodec videoCategoryCodec = new VideoCategoryCodec();
            VideoCodec videoCodec = new VideoCodec();
            PhotoCategoryCodec photoCategoryCodec = new PhotoCategoryCodec();
            NotificationCodec notificationCodec = new NotificationCodec();
            MessageCodec messageCodec = new MessageCodec();
            MessageDetailsCodec messageDetailsCodec = new MessageDetailsCodec();
            CharacterCodec characterCodec = new CharacterCodec();
            SocialNetworkingCodec socialNetworkingCodec = new SocialNetworkingCodec();
            LoginAttemptCodec loginAttemptCodec = new LoginAttemptCodec();
            PageCategoryCodec pageCategoryCodec = new PageCategoryCodec();
            PageSubCategoryCodec pageSubCategoryCodec = new PageSubCategoryCodec();
            PageCodec pageCodec = new PageCodec();
            PageMemeberCodec pageMemeberCodec = new PageMemeberCodec();
            CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                    MongoClient.getDefaultCodecRegistry(),
                    CodecRegistries.fromCodecs(userCodec),
                    CodecRegistries.fromCodecs(basicProfileCodec),
                    CodecRegistries.fromCodecs(countriesCodec),
                    CodecRegistries.fromCodecs(religionCodec),
                    CodecRegistries.fromCodecs(relationsCodec),
                    CodecRegistries.fromCodecs(statusCodec),
                    CodecRegistries.fromCodecs(albumCodec),
                    CodecRegistries.fromCodecs(photoCodec),
                    CodecRegistries.fromCodecs(photoCategoryCodec),
                    CodecRegistries.fromCodecs(videoCategoryCodec),
                    CodecRegistries.fromCodecs(videoCodec),
                    CodecRegistries.fromCodecs(notificationCodec),
                    CodecRegistries.fromCodecs(messageCodec),
                    CodecRegistries.fromCodecs(messageDetailsCodec),
                    CodecRegistries.fromCodecs(loginAttemptCodec),
                    CodecRegistries.fromCodecs(characterCodec),
                    CodecRegistries.fromCodecs(socialNetworkingCodec),
                    CodecRegistries.fromCodecs(pageCategoryCodec),
                    CodecRegistries.fromCodecs(pageSubCategoryCodec),
                    CodecRegistries.fromCodecs(pageCodec),
                    CodecRegistries.fromCodecs(pageMemeberCodec)
                    
            );

            MongoClientOptions options = MongoClientOptions.builder().codecRegistry(codecRegistry).build();

            ServerAddress serverAddress = new ServerAddress(dbHost, dbPort);
            MongoClient mongoClient = new MongoClient(serverAddress, options);

            connection = mongoClient.getDatabase(dbName);

        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public MongoDatabase getConnection() {
        return connection;
    }

    private void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    private void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    private void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    private void setDbPort(int dbPort) {
        this.dbPort = dbPort;
    }

    private void setDbName(String dbName) {
        this.dbName = dbName;
    }

}
